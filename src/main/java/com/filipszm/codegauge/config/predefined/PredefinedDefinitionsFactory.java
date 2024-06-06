package com.filipszm.codegauge.config.predefined;

import com.filipszm.codegauge.config.model.MetricDefinition;
import com.filipszm.codegauge.metric.MetricType;
import com.filipszm.codegauge.metric.configutation.CyclomaticComplexityConfiguration;
import com.filipszm.codegauge.metric.configutation.LinesOfCodeConfiguration;
import com.filipszm.codegauge.metric.configutation.NumberOfFunctionsConfiguration;

import java.util.ArrayList;
import java.util.List;

public final class PredefinedDefinitionsFactory {

    public static List<MetricDefinition> createPredefinedDefinitions() {
        var metrics = new ArrayList<MetricDefinition>();
        metrics.add(MetricDefinition
                .builder()
                .name("ELOC")
                .enabled(true)
                .type(MetricType.LINES_OF_CODE)
                .config(createELOCConfiguration())
                .build());
        metrics.add(MetricDefinition
                .builder()
                .name("LLOC")
                .enabled(true)
                .type(MetricType.LINES_OF_CODE)
                .config(createLLOCConfiguration())
                .build());
        metrics.add(MetricDefinition
                .builder()
                .name("NUMBER_OF_FUNCTIONS")
                .enabled(true)
                .type(MetricType.NUMBER_OF_FUNCTIONS)
                .config(createNumberOfFunctionsConfiguration())
                .build());
        metrics.add(MetricDefinition
                .builder()
                .name("CYC")
                .enabled(true)
                .type(MetricType.CYCLOMATIC_COMPLEXITY)
                .config(createCYCConfiguration())
                .build());

        return metrics;
    }

    private static LinesOfCodeConfiguration createELOCConfiguration() {
        return new LinesOfCodeConfiguration(
                false,
                false,
                false,
                false,
                false,
                false,
                false,
                false,
                false,
                false,
                false,
                false
        );
    }

    private static LinesOfCodeConfiguration createLLOCConfiguration() {
        return new LinesOfCodeConfiguration(
                true,
                true,
                true,
                true,
                true,
                true,
                true,
                true,
                true,
                true,
                true,
                true
        );
    }

    private static NumberOfFunctionsConfiguration createNumberOfFunctionsConfiguration() {
        return new NumberOfFunctionsConfiguration(
                true,
                true,
                true,
                true,
                true
        );
    }

    private static CyclomaticComplexityConfiguration createCYCConfiguration() {
        return new CyclomaticComplexityConfiguration(
                true,
                true,
                true,
                true,
                true,
                false,
                false,
                false,
                false,
                false,
                true,
                false,
                false,
                true,
                false,
                false,
                false
        );
    }
}
