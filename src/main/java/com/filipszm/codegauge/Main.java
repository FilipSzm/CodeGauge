package com.filipszm.codegauge;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.filipszm.codegauge.config.ConfigurationReader;
import com.filipszm.codegauge.config.model.MetricDefinition;
import com.filipszm.codegauge.metric.evaluator.EvaluateParams;
import com.filipszm.codegauge.metric.evaluator.MetricEvaluator;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Paths;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException, NoSuchMethodException, InvocationTargetException,
            InstantiationException, IllegalAccessException {


        List<MetricDefinition> metricDefinitions = ConfigurationReader.readMetricDefinitions("config.json");

        var lexer = new Java20Lexer(CharStreams.fromPath(Paths.get(args[0])));
        var tokens = new CommonTokenStream(lexer);
        var parser = new Java20Parser(tokens);

        ParseTree tree = parser.compilationUnit();

        var mapper = new ObjectMapper();
        ArrayNode rootNode = mapper.createArrayNode();

        for (MetricDefinition metricDefinition : metricDefinitions) {
            ObjectNode metricNode = mapper.createObjectNode();
            metricNode.put("name", metricDefinition.getName());

            Constructor<? extends MetricEvaluator> constructor = metricDefinition.getType().getEvaluatorClass().getDeclaredConstructor();

            MetricEvaluator evaluator = constructor.newInstance();

            EvaluateParams params = new EvaluateParams(tree, metricDefinition.getConfig(), metricNode);
            evaluator.evaluate(params);

            rootNode.add(metricNode);
        }


        String jsonString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(rootNode);
        System.out.println(jsonString);



//
//
//        List<MetricDefinition> metrics = new ArrayList<>();
//
//        NumberOfFunctionsConfiguration locConfig =
//                new NumberOfFunctionsConfiguration(true, true, true, true);
//        MetricDefinition metric1 = new MetricDefinition();
//        metric1.setName("function1");
//        metric1.setEnabled(true);
//        metric1.setType(MetricType.NUMBER_OF_FUNCTIONS);
//        metric1.setConfig(locConfig);
//        metrics.add(metric1);
//
//
//        try {
//            objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File("config.json"), metrics);
//            System.out.println("JSON file created: config.json");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }




    }
}