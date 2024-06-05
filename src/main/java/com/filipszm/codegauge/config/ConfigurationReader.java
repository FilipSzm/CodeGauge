package com.filipszm.codegauge.config;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.filipszm.codegauge.config.model.MetricDefinition;
import com.filipszm.codegauge.config.predefined.PredefinedDefinitionsFactory;

import java.io.File;
import java.io.IOException;
import java.util.List;

public final class ConfigurationReader {
    private static final File DEFAULT_CONFIG_PATH = new File("config.json");
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    public static List<MetricDefinition> readMetricDefinitions() {
        if (!DEFAULT_CONFIG_PATH.exists()) {
            try {
                List<MetricDefinition> metricDefinitions = PredefinedDefinitionsFactory.createPredefinedDefinitions();
                OBJECT_MAPPER.writerWithDefaultPrettyPrinter().writeValue(DEFAULT_CONFIG_PATH, metricDefinitions);
                System.out.println("Configuration file created: " + DEFAULT_CONFIG_PATH);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return readMetricDefinitions(DEFAULT_CONFIG_PATH);
    }

    public static List<MetricDefinition> readMetricDefinitions(String pathname) {
        File file = new File(pathname);
        if (!file.exists()) {
            throw new RuntimeException("File with configuration at given path does not exist: " + pathname);
        }
        return readMetricDefinitions(file);
    }

    private static List<MetricDefinition> readMetricDefinitions(File file) {
        try {
            return OBJECT_MAPPER.readValue(file, new TypeReference<>() {});
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
