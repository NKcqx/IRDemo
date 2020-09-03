package fdu.daslab.executable.spark.operators;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;
import fdu.daslab.executable.basic.model.ExecutionOperator;
import fdu.daslab.executable.basic.model.OperatorBase;
import fdu.daslab.executable.basic.model.ParamsModel;
import fdu.daslab.executable.basic.model.ResultModel;
import org.apache.commons.lang.StringUtils;
import org.apache.spark.api.java.JavaRDD;

import java.util.List;
import java.util.Map;

/**
 * 写入文件的算子
 *
 * @author 唐志伟
 * @since 2020/7/6 1:52 PM
 * @version 1.0
 */
@Parameters(separators = "=")
public class FileSink  extends OperatorBase<JavaRDD<List<String>>, JavaRDD<List<String>>> {
    // 输入路径
    @Parameter(names = {"--output"}, required = true)
    String outputFileName;

    // 输出的分隔符
    @Parameter(names = {"--sep"})
    String separateStr = ",";

    public FileSink(String id, List<String> inputKeys, List<String> outputKeys, Map<String, String> params) {
        super("SparkFileSink", id, inputKeys, outputKeys, params);
    }

    @Override
    public void execute(ParamsModel inputArgs,
                        ResultModel<JavaRDD<List<String>>> result) {
        // FileSink fileSinkArgs = (FileSink) inputArgs.getOperatorParam();
        // 写入文件

        this.getInputData("data")
                .map(line -> StringUtils.join(line, this.params.get("separator")))
                .saveAsTextFile(this.params.get("outputPath"));
    }
}
