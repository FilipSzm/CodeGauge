package com.filipszm.codegauge.metric;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.filipszm.codegauge.config.model.MetricConfiguration;
import com.filipszm.codegauge.metric.configutation.CyclomaticComplexityConfiguration;
import com.filipszm.codegauge.metric.configutation.LinesOfCodeConfiguration;
import com.filipszm.codegauge.metric.configutation.NumberOfFunctionsConfiguration;
import com.filipszm.codegauge.metric.evaluator.MetricEvaluator;
import com.filipszm.codegauge.metric.evaluator.implementation.CyclomaticComplexityEvaluator;
import com.filipszm.codegauge.metric.evaluator.implementation.LinesOfCodeEvaluator;
import com.filipszm.codegauge.metric.evaluator.implementation.NumberOfFunctionsEvaluator;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MetricType {
    LINES_OF_CODE(
            "lines_of_code",
            LinesOfCodeConfiguration.class,
            LinesOfCodeEvaluator.class
    ),
    CYCLOMATIC_COMPLEXITY(
            "cyclomatic_complexity",
            CyclomaticComplexityConfiguration.class,
            CyclomaticComplexityEvaluator.class
    ),
    NUMBER_OF_FUNCTIONS(
            "number_of_functions",
            NumberOfFunctionsConfiguration.class,
            NumberOfFunctionsEvaluator.class
    );

    private final String name;
    private final Class<? extends MetricConfiguration> configClass;
    private final Class<? extends MetricEvaluator> evaluatorClass;

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
