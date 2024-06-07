package com.filipszm.codegauge;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.filipszm.codegauge.config.ArgumentReader;
import com.filipszm.codegauge.config.ConfigurationReader;
import com.filipszm.codegauge.config.model.Arguments;
import com.filipszm.codegauge.config.model.MetricDefinition;
import com.filipszm.codegauge.metric.evaluator.EvaluateParams;
import com.filipszm.codegauge.metric.evaluator.MetricEvaluator;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Path;
import java.util.*;

public class CodeGauge {
    private final ObjectMapper mapper = new ObjectMapper();

    private void analyze(Arguments arguments) {
        List<MetricDefinition> metricDefinitions = arguments.configPath().isPresent() ?
                ConfigurationReader.readMetricDefinitions(arguments.configPath().get()) :
                ConfigurationReader.readMetricDefinitions();

        ArrayNode rootNode = mapper.createArrayNode();
        arguments.filesToAnalyze().forEach(path -> analyzeFile(path, metricDefinitions, rootNode));
        printMetricValues(rootNode);
    }

    private void analyzeFile(Path path, List<MetricDefinition> metricDefinitions, ArrayNode rootNode) {
        if (!path.toFile().exists())
            return;

        ParseTree tree = getParseTree(path);
        ObjectNode pathNode = mapper.createObjectNode();
        pathNode.put("filename", path.toFile().getName());
        ArrayNode metricsNode = pathNode.putArray("metric_values");
        metricDefinitions.forEach(metricDefinition -> evaluateMetric(tree, metricDefinition, metricsNode));
        rootNode.add(pathNode);
    }

    private void evaluateMetric(ParseTree tree, MetricDefinition metricDefinition, ArrayNode metricsNode) {
        ObjectNode metricNode = mapper.createObjectNode();
        metricNode.put("name", metricDefinition.getName());
        MetricEvaluator evaluator = getMetricEvaluator(metricDefinition);
        EvaluateParams params = new EvaluateParams(tree, metricDefinition.getConfig(), metricNode);
        evaluator.evaluate(params);
        metricsNode.add(metricNode);
    }

    private MetricEvaluator getMetricEvaluator(MetricDefinition metricDefinition) {
        try {
            return metricDefinition.getType().getEvaluatorClass().getDeclaredConstructor().newInstance();
        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException |
                 InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    private void printMetricValues(ArrayNode rootNode) {
        try {
            System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(rootNode));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private ParseTree getParseTree(Path path) {
        Java20Lexer lexer;
        try {
            lexer = new Java20Lexer(CharStreams.fromPath(path));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        var tokens = new CommonTokenStream(lexer);
        var parser = new Java20Parser(tokens);
        return parser.compilationUnit();
    }

    public static void main(String[] args) {
        new CodeGauge().analyze(ArgumentReader.readArguments(args));
    }
}