package fdu.daslab.executable.spark.constants;

import fdu.daslab.executable.basic.model.ExecutionOperator;
import fdu.daslab.executable.spark.operators.*;
import org.apache.spark.api.java.JavaRDD;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * spark平台支持的算子的枚举
 *
 * @author 唐志伟
 * @since 2020/7/6 1:51 PM
 * @version 1.0
 */
public final class SparkOperatorEnums {

    // 以下用于参数的传递
    public static final String FILE_SOURCE = "file_source";  // 读取文件的source
    public static final String FILE_SINK = "file_sink";   // 写入文件的sink
    public static final String FILTER = "filter";
    public static final String MAP = "map";
    public static final String REDUCE_BY_KEY = "reduce_by_key";
    public static final String SORT = "sort";
////    public static final String JOIN = "join";

    private SparkOperatorEnums() {
    }

    // 所有支持的operator
    public static Map<String, ExecutionOperator<JavaRDD<List<String>>>> getAllOperators() {
        return null;
//        return new HashMap<String, ExecutionOperator<JavaRDD<List<String>>>>() {{
//            put(FILE_SOURCE, new FileSource());
//            put(FILE_SINK, new FileSink());
//            put(FILTER, new FilterOperator());
//            put(MAP, new MapOperator());
//            put(REDUCE_BY_KEY, new ReduceByKeyOperator());
//            put(SORT, new SortOperator());
//        }};
    }
}
