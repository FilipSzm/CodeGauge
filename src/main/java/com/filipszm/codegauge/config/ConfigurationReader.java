package com.filipszm.codegauge.config;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.filipszm.codegauge.config.model.MetricDefinition;

import java.io.File;
import java.io.IOException;
import java.util.List;

public final class ConfigurationReader {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static List<MetricDefinition> readMetricDefinitions(String pathname) {
        try {
            return objectMapper.readValue(new File(pathname), new TypeReference<>() {});
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
