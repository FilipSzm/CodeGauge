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
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException, InvocationTargetException, InstantiationException,
            IllegalAccessException, NoSuchMethodException {
        List<Path> filesToAnalyse = new ArrayList<>();
        Optional<String> configPath = readArgs(args, filesToAnalyse);
        List<MetricDefinition> metricDefinitions = configPath.isPresent() ?
                        ConfigurationReader.readMetricDefinitions(configPath.get()) :
                        ConfigurationReader.readMetricDefinitions();

        var mapper = new ObjectMapper();
        ArrayNode rootNode = mapper.createArrayNode();
        for (Path path : filesToAnalyse) {
            if (!path.toFile().exists())
                return;

            var lexer = new Java20Lexer(CharStreams.fromPath(path));
            var tokens = new CommonTokenStream(lexer);
            var parser = new Java20Parser(tokens);
            ParseTree tree = parser.compilationUnit();

            ObjectNode pathNode = mapper.createObjectNode();
            pathNode.put("filename", path.toFile().getName());
            ArrayNode metricsNode = pathNode.putArray("metric_values");
            for (MetricDefinition metricDefinition : metricDefinitions) {
                ObjectNode metricNode = mapper.createObjectNode();
                metricNode.put("name", metricDefinition.getName());

                Constructor<? extends MetricEvaluator> constructor =
                        metricDefinition.getType().getEvaluatorClass().getDeclaredConstructor();

                MetricEvaluator evaluator = constructor.newInstance();

                EvaluateParams params = new EvaluateParams(tree, metricDefinition.getConfig(), metricNode);
                evaluator.evaluate(params);

                metricsNode.add(metricNode);
            }
            rootNode.add(pathNode);
        }

        System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(rootNode));
    }

    private static Optional<String> readArgs(String[] args, List<Path> filesToAnalyse) {
        Optional<String> configPath = Optional.empty();
        Iterator<String> iterator = Arrays.stream(args).iterator();
        while (iterator.hasNext()) {
            String arg = iterator.next();
            if ("-c".equals(arg)) {
                configPath = Optional.of(iterator.next());
            } else {
                filesToAnalyse.add(Paths.get(arg));
            }
        }
        return configPath;
    }


}