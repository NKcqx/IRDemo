package basic.operators;

import basic.Param;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.w3c.dom.Document;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

/**
 * Testing for Operator.java
 *
 * @author 刘丰艺
 * @version 1.0
 * @since 2020/7/10 0:52
 */
@RunWith(PowerMockRunner.class) // 指定run的是PowerMockRunner
@PrepareForTest(Operator.class) // 所有要测试的类（本例只有Operator.java）
public class OperatorTest {

    private String configFilePath;
    private Operator spyOpt;

    @Before
    public void before() throws Exception {
        configFilePath = "Operator/Filter/conf/FilterOperator.xml";
        spyOpt = spy(new Operator(configFilePath));
    }

    @Test
    public void simpleConstructorTest() throws Exception {
        Operator opt = new Operator("Operator/Filter/conf/FilterOperator.xml");
    }

    @Test
    public void inputDataListTest() {

        String name = "udfName2";
        String dataType = "string";
        String defaultValue = null;
        Boolean isRequired = false;
        Param param = new Param(name, dataType, isRequired, defaultValue);
        Map<String, Param> inputDataList = spyOpt.getInputDataList();
        inputDataList.put(name, param);

        for (Map.Entry<String, Param> entry : spyOpt.getInputDataList().entrySet()) {
            // 打印出inputDataList可发现put函数调用成功
            String entryKey = entry.getKey();
        }
    }

    @Test
    public void getPlatformOptConfTest() throws Exception {
        spyOpt.getPlatformOptConf();
        verify(spyOpt, times(1)).getPlatformOptConf();

        spyOpt.getEntities().forEach((k, v) -> {
            if (k.equals("java")) {
               assertEquals("java", v.getLanguage());
            }
            if (k.equals("spark")) {
                assertEquals("java", v.getLanguage());
            }
        });
    }

    @Test
    public void loadImplementsTest() throws Exception {
        spyOpt.getPlatformOptConf();
        PowerMockito.verifyPrivate(spyOpt, Mockito.times(2))
                .invoke("loadImplements", Mockito.any(Document.class));
    }


    @Test
    public void selectEntityTest() throws Exception {
        spyOpt.selectEntity("java");
        verify(spyOpt, times(1)).selectEntity("java");
        verify(spyOpt, times(0)).selectEntity("spark");
    }

    @Test
    public void evaluateTest() {
        boolean flag = spyOpt.evaluate();
        assertEquals(true, flag);
        Mockito.when(spyOpt.evaluate()).thenReturn(false);
        boolean mockFlag = spyOpt.evaluate();
        assertEquals(false, mockFlag);
        verify(spyOpt, times(3)).evaluate();
    }

    @Test
    public void tempPrepareDataTest() {
        spyOpt.tempPrepareData();
        verify(spyOpt, times(1)).tempPrepareData();

        spyOpt.getInputDataList().forEach((k, v) -> {
            assertEquals(k + "'s temp value", v.getData());
        });
    }

    @Test
    public void tempDoEvaluateTest() {
        spyOpt.tempDoEvaluate();
        verify(spyOpt, times(1)).tempDoEvaluate();
    }

    @Test
    public void inputDataTest() {

        //spyOpt.setData("inputKey", "inputData"); // 此时会抛出NoSuchElementException“未在配置文件中...”

        String name = "inputKey";
        String dataType = "string";
        String defaultValue = null;
        Boolean isRequired = false;
        Param param = new Param(name, dataType, isRequired, defaultValue);
        Map<String, Param> inputDataList = spyOpt.getInputDataList();
        inputDataList.put(name, param);

        spyOpt.setData("inputKey", "inputData");

        spyOpt.getInputDataList().forEach((k, v) -> {
            if (k.equals("inputKey")) {
                assertEquals("inputKey", v.getName());
                assertEquals("inputData", v.getData());
            }
            if (k.equals("udfName")) {
                assertEquals("udfName", v.getName());
                assertEquals(null, v.getData());
            }
        });

        verify(spyOpt, times(1)).setData("inputKey", "inputData");
    }

    @Test
    public void outputDataTest() {

        //spyOpt.setData("outputKey", "outputData"); // 此时会抛出NoSuchElementException“未在配置文件中...”

        String name = "outputKey";
        String dataType = "string";
        String defaultValue = null;
        Boolean isRequired = false;
        Param param = new Param(name, dataType, isRequired, defaultValue);
        Map<String, Param> outputDataList = spyOpt.getOutputDataList();
        outputDataList.put(name, param);

        spyOpt.setData("outputKey", "outputData");

        spyOpt.getOutputDataList().forEach((k, v) -> {
            if (k.equals("result")) {
                assertEquals("result", v.getName());
                assertEquals(null, v.getData());
            }
            if (k.equals("outputKey")) {
                assertEquals("outputKey", v.getName());
                assertEquals("outputData", v.getData());
            }
        });

        verify(spyOpt, times(1)).setData("outputKey", "outputData");
    }
}
