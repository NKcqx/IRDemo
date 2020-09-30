package fdu.daslab.executable.java.operators;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;
import fdu.daslab.executable.basic.model.OperatorBase;
import fdu.daslab.executable.basic.model.ParamsModel;
import fdu.daslab.executable.basic.model.ResultModel;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import scala.collection.mutable.StringBuilder;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

@Parameters(separators = "=")
public class HDFSSink extends OperatorBase<Stream<List<String>>, Stream<List<String>>> {

    @Parameter(names = {"--output"}, required = true)
    String outputHdfsPath;

    @Parameter(names = {"--separator"})
    String separator = ",";

    public HDFSSink(String id, List<String> inputKeys, List<String> outputKeys, Map<String, String> params) {
        super("HDFSSink", id, inputKeys, outputKeys, params);
    }

    private FileSystem getFileSystem() throws IOException, URISyntaxException, InterruptedException {
        Configuration configuration = new Configuration();
        configuration.set("dfs.replication","1");
        return FileSystem.get(new URI("hdfs://localhost:8020"),configuration,"zhuxingpo");
    }

    @Override
    public void execute(ParamsModel inputArgs, ResultModel<Stream<List<String>>> result) {
        try {
            Path path = new Path(this.params.get("outputPath"));
            FSDataOutputStream fsDataOutputStream = getFileSystem().create(path);

            this.getInputData("data")
                    .forEach(record -> {
                        StringBuilder writeLine = new StringBuilder();
                        record.forEach(field -> {
                            writeLine.append(field);
                            writeLine.append(this.params.get(separator));
                        });
                        writeLine.deleteCharAt(writeLine.length() - 1);
                        writeLine.append("\n");
                        try {
                            fsDataOutputStream.write(writeLine.toString().getBytes("UTF-8"));
//                            fsDataOutputStream.write("\n".getBytes("UTF-8"));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });
            fsDataOutputStream.close();

        } catch (IOException | InterruptedException | URISyntaxException e) {
            e.printStackTrace();
        }
    }
}
