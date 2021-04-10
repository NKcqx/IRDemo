package demo;

import api.DataQuanta;
import api.PlanBuilder;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.HashMap;

/**
 * @author 李姜辛
 * @version 1.0
 * @since 2021/3/16 16:06
 */
public class FlinkBatchDemo {
    public static void main(String[] args) throws IOException, SAXException, ParserConfigurationException {
        try {
            PlanBuilder planBuilder = new PlanBuilder("test-crime");
            planBuilder.setPlatformUdfPath("java", "D:/study/code/Java/CLIC/executable-operator/executable-basic/target/classes/fdu/daslab/executable/udf/TestCrimeDataFunc.class");
            planBuilder.setPlatformUdfPath("spark", "D:/study/code/Java/CLIC/executable-operator/executable-basic/target/classes/fdu/daslab/executable/udf/TestCrimeDataFunc.class");
            planBuilder.setPlatformUdfPath("flink", "D:/study/code/Java/CLIC/executable-operator/executable-basic/target/classes/fdu/daslab/executable/udf/TestCrimeDataFunc.class");


            // 创建节点   例如该map的value值是本项目test.csv的绝对路径
            DataQuanta sourceNode = planBuilder.readDataFrom(new HashMap<String, String>() {{
                put("inputPath", "D:/study/code/Scala/FlinkTutorial/target/classes/london_crime_small.csv");
            }}).withTargetPlatform("flink");

            DataQuanta mapCateNode = DataQuanta.createInstance("map", new HashMap<String, String>() {{
                put("udfName", "mapCateFunc");
            }}).withTargetPlatform("flink");

            DataQuanta filterNode = DataQuanta.createInstance("filter", new HashMap<String, String>() {{
                put("udfName", "filterFunc");
            }}).withTargetPlatform("flink");

            DataQuanta mapMonthNode = DataQuanta.createInstance("map", new HashMap<String, String>() {{
                put("udfName", "mapMonthFunc");
            }}).withTargetPlatform("flink");

            DataQuanta reduceNode = DataQuanta.createInstance("reduce-by-key", new HashMap<String, String>() {{
                put("udfName", "reduceFunc");
                put("keyName", "reduceKey");
            }}).withTargetPlatform("flink");

            DataQuanta sortNode = DataQuanta.createInstance("sort", new HashMap<String, String>() {{
                put("udfName", "sortFunc");
            }}).withTargetPlatform("flink");

            // 最终结果的输出路径   例如该map的value值是本项目output.csv的绝对路径
            DataQuanta sinkNode = DataQuanta.createInstance("sink", new HashMap<String, String>() {{
                put("outputPath", "D:/study/code/Scala/FlinkTutorial/target/classes/output.csv"); // 具体resources的路径通过配置文件获得
            }}).withTargetPlatform("flink");

            planBuilder.addVertex(sourceNode);
            planBuilder.addVertex(mapCateNode);
            planBuilder.addVertex(filterNode);
            planBuilder.addVertex(mapMonthNode);
            planBuilder.addVertex(reduceNode);
            planBuilder.addVertex(sortNode);
            planBuilder.addVertex(sinkNode);

            // 链接节点，即构建DAG
            planBuilder.addEdge(sourceNode, mapCateNode);
            planBuilder.addEdge(mapCateNode, filterNode);
            planBuilder.addEdge(filterNode, mapMonthNode);
            planBuilder.addEdge(mapMonthNode, reduceNode);
            planBuilder.addEdge(reduceNode, sortNode);
            planBuilder.addEdge(sortNode, sinkNode);
            planBuilder.addEdge(reduceNode, sinkNode);

            planBuilder.execute();
            // QUESTION(SOLVED): 生成的java-template正确吗？下一步怎么提交给CLIC后台运行？
            // 1. 正确，但是那只是template，我们真正需要的是logical plan的yaml文件, 在default-config的yaml-output-path路径下，一个以job开头的yaml文件
            // 2. 用clic-shell，但是非常复杂，因为clic-shell是一个k8s的pod, 需要针对clic-shell去配置k8s，但是clic的k8s环境hen nan pei，具体参考deployment下的README.md
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
