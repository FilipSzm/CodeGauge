package com.filipszm.codegauge;

import com.filipszm.codegauge.config.ConfigurationReader;
import com.filipszm.codegauge.config.model.MetricDefinition;
import com.filipszm.codegauge.metric.antlr.listener.HalsteadLOCListener;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {

        String javaClassContent = Files.readString(Paths.get(args[0]));
        Java20Lexer lexer = new Java20Lexer(CharStreams.fromString(javaClassContent));
        CommonTokenStream tokens = new CommonTokenStream(lexer);

        Java20Parser parser = new Java20Parser(tokens);

        ParseTree tree = parser.compilationUnit();

        ParseTreeWalker walker = new ParseTreeWalker();
        var loc = new HalsteadLOCListener();
        walker.walk(loc, tree);

        System.out.println(loc.getMetricValue());

//        ObjectMapper objectMapper = new ObjectMapper();
//
//        List<MetricDefinition> metrics = new ArrayList<>();
//
//        LinesOfCodeConfiguration locConfig = new LinesOfCodeConfiguration(true);
//        MetricDefinition metric1 = new MetricDefinition();
//        metric1.setType(MetricType.LINES_OF_CODE);
//        metric1.setConfig(locConfig);
//        metrics.add(metric1);
//
//
//        try {
//            objectMapper.writeValue(new File("metrics_output.json"), metrics);
//            System.out.println("JSON file created: metrics_output.json");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        List<MetricDefinition> metrics2 = ConfigurationReader.readMetricDefinitions("config.json");

        for (MetricDefinition metric : metrics2) {
            System.out.println("MetricType: " + metric.getType());
            System.out.println("Configuration: " + metric.getConfig());
        }

    }
}