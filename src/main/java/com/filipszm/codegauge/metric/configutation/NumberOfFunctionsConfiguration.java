package com.filipszm.codegauge.metric.configutation;

import com.filipszm.codegauge.config.model.MetricConfiguration;

public record NumberOfFunctionsConfiguration(
        boolean countLambdaExpression,
        boolean countConstructorDeclaration,
        boolean countStaticInitializer,
        boolean countInstanceInitializer,
        boolean countInterfaceMethodDeclaration
) implements MetricConfiguration {}
