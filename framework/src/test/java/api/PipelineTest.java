package api;

import adapters.ArgoAdapter;
import basic.operators.Operator;
import channel.Channel;
import fdu.daslab.backend.executor.model.Pipeline;
import org.junit.Before;
import org.junit.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Testing for Pipeline.java
 *
 * @author 刘丰艺
 * @version 1.0
 * @since 2020/7/14 16:42
 */
public class PipelineTest {

    private Pipeline spyArgoPipeline;
    private Pipeline argoPipeline;

    @Before
    public void setUp() throws Exception {
        Operator opt1 = new Operator("Operator/Source/conf/SourceOperator.xml");
        Operator opt2 = new Operator("Operator/Sink/conf/SinkOperator.xml");
        Operator opt3 = new Operator("Operator/Filter/conf/FilterOperator.xml");
        Operator opt4 = new Operator("Operator/Map/conf/MapOperator.xml");
        Operator opt5 = new Operator("Operator/Sink/conf/SinkOperator.xml");
        opt1.selectEntity("java");
        opt2.selectEntity("java");
        opt3.selectEntity("java");
        opt4.selectEntity("java");
        opt5.selectEntity("java");

        opt1.connectTo(new Channel(opt1, opt2, null));
        opt2.connectFrom(new Channel(opt1, opt2, null));
        opt1.connectTo(new Channel(opt1, opt3, null));
        opt3.connectFrom(new Channel(opt1, opt3, null));
        opt3.connectTo(new Channel(opt3, opt4, null));
        opt4.connectFrom(new Channel(opt3, opt4, null));
        opt3.connectTo(new Channel(opt3, opt5, null));
        opt5.connectFrom(new Channel(opt3, opt5, null));

        List<Operator> allOperators = new ArrayList<>();
        allOperators.add(opt1);
        allOperators.add(opt2);
        allOperators.add(opt3);
        allOperators.add(opt4);
        allOperators.add(opt5);
        argoPipeline = new Pipeline(new ArgoAdapter(), allOperators);
        spyArgoPipeline = spy(argoPipeline);
    }

    @Test
    public void execute() {
        // YamlUti.java 77行的filter操作
        // .filter(template -> template.getPlatform().equals(node.getPlatform()))
        // 返回结果为空
        // 抛出IndexOutOfBoundsException异常
        // java.lang.IndexOutOfBoundsException: Index: 0, Size: 0
        // at fdu.daslab.backend.executor.utils.YamlUtil.joinYaml(YamlUtil.java:79)
        //	at fdu.daslab.backend.executor.utils.YamlUtil.createArgoYaml(YamlUtil.java:48)
        //	at fdu.daslab.backend.executor.model.Pipeline.execute(Pipeline.java:43)
        doThrow(IndexOutOfBoundsException.class).when(spyArgoPipeline).execute();
    }
}
