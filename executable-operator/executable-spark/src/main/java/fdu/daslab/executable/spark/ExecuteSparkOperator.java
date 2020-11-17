
package fdu.daslab.executable.spark;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;
import fdu.daslab.executable.basic.model.*;
import fdu.daslab.executable.basic.utils.ArgsUtil;
import fdu.daslab.executable.basic.utils.TopoTraversal;
import fdu.daslab.executable.spark.constants.SparkOperatorFactory;
import fdu.daslab.service.client.SchedulerServiceClient;
import org.apache.spark.api.java.JavaRDD;
import org.apache.thrift.TException;
import org.javatuples.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;

/**
 * spark平台的operator的具体实现，兼顾算子融合，因为是线性，只支持线性的合并，不支持 n - 1
 * ===> 依赖前面的pipeline同步，不依赖具体执行
 * <p>
 * 按照--udfPath指定用户定义的类的位置
 * 按照--master --appName --... 以及一些其他参数设置spark的配置信息
 * 按照--operator指定实际的算子，可以指定多个，多个在一个平台上，后台统一执行
 *
 * @author 唐志伟
 * @version 1.0
 * @since 2020/7/6 1:55 PM
 */
@Parameters(separators = "=")
public class ExecuteSparkOperator {

    @Parameter(names = {"--stageId", "-sid"})
    String stageId = null;    // stage的全局唯一标识

    @Parameter(names = {"--udfPath", "-udf"})
    String udfPath;

    @Parameter(names = {"--dagPath", "-dag"})
    String dagPath;

    // @Parameter(names = {"--port", "-p"})
    // Integer thriftPort; // 本server启动的thrift端口

    @Parameter(names = {"--masterHost", "-mh"})
    String masterHost = null; // master的thrift地址

    @Parameter(names = {"--masterPort", "-mp"})
    Integer masterPort = null; // master的thrift端口

    public static void main(String[] args) throws TException {
        Logger logger = LoggerFactory.getLogger(ExecuteSparkOperator.class);
        ExecuteSparkOperator entry = new ExecuteSparkOperator();
        JCommander.newBuilder()
                .addObject(entry)
                .build()
                .parse(args);
        // String functionPath =  entry.udfPath;
        // final FunctionModel functionModel = ReflectUtil.createInstanceAndMethodByPath(entry.udfPath);

        // 创建一个thrift client，用于和master进行交互
        SchedulerServiceClient masterClient = new SchedulerServiceClient(entry.stageId,
                entry.masterHost, entry.masterPort);

        // 开始stage
        masterClient.postStarted();

        //记录时间
        long start = System.currentTimeMillis();   //获取开始时间
        logger.info("Stage(spark) ———— Start A New Spark Stage");
        try {
            InputStream yamlStream = new FileInputStream(new File(entry.dagPath));
            Pair<List<OperatorBase>, List<OperatorBase>> headAndEndOperators =
                    ArgsUtil.parseArgs(yamlStream, new SparkOperatorFactory());

            // 遍历DAG，执行execute，每次执行前把上一跳的输出结果放到下一跳的输入槽中（用Connection来转移ResultModel里的数据）
            ParamsModel inputArgs = new ParamsModel(null);
            inputArgs.setFunctionClasspath(entry.udfPath);
            // 拓扑排序保证了opt不会出现 没得到所有输入数据就开始计算的情况
            TopoTraversal topoTraversal = new TopoTraversal(headAndEndOperators.getValue0());
            while (topoTraversal.hasNextOpt()) {
                OperatorBase<Object, Object> curOpt = topoTraversal.nextOpt();
                // 每个operator内部设置一个client，方便和master进行交互
                curOpt.setMasterClient(masterClient);
                curOpt.execute(inputArgs, null);
                // 把计算结果传递到每个下一跳opt
                List<Connection> connections = curOpt.getOutputConnections(); // curOpt没法明确泛化类型
                for (Connection connection : connections) {
                    OperatorBase<Object, Object> targetOpt = connection.getTargetOpt();
                    topoTraversal.updateInDegree(targetOpt, -1);
                    List<Pair<String, String>> keyPairs = connection.getKeys();
                    for (Pair<String, String> keyPair : keyPairs) {
                        Object sourceResult = curOpt.getOutputData(keyPair.getValue0());
                        // 将当前opt的输出结果传入下一跳的输入数据
                        targetOpt.setInputData(keyPair.getValue1(), sourceResult);
                    }
                }
                logger.info("Stage(java) ———— Current Spark Operator is " + curOpt.getName());
            }

            long end = System.currentTimeMillis(); //获取结束时间
            logger.info("Stage(spark) ———— Running hold time:  " + (end - start) + "ms");
            logger.info("Stage(spark) ———— End The Current Spark Stage");
        } catch (Exception e) {
            e.printStackTrace();

            // TODO: 尽量所有错误都直接上报给master，业务代码不要捕获其中的异常
        }

        // 结束stage
        masterClient.postCompleted();
    }
}
