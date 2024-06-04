package com.filipszm.codegauge.metric;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.filipszm.codegauge.config.model.MetricConfiguration;
import com.filipszm.codegauge.metric.configutation.CyclomaticComplexityConfiguration;
import com.filipszm.codegauge.metric.configutation.LinesOfCodeConfiguration;
import com.filipszm.codegauge.metric.configutation.NumberOfFunctionsConfiguration;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MetricType {
    LINES_OF_CODE("lines_of_code", LinesOfCodeConfiguration.class),
    CYCLOMATIC_COMPLEXITY("cyclomatic_complexity", CyclomaticComplexityConfiguration.class),
    NUMBER_OF_FUNCTIONS("number_of_functions", NumberOfFunctionsConfiguration.class);

    private final String name;
    private final Class<? extends MetricConfiguration> configClass;

    @JsonCreator
    public static MetricType forValue(@JsonProperty("name") String name) {
        for (MetricType type : MetricType.values()) {
            if (type.name.equals(name)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Unknown metric type: " + name);
    }
}
