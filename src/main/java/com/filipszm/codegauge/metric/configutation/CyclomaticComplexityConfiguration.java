package com.filipszm.codegauge.metric.configutation;

import com.filipszm.codegauge.config.model.MetricConfiguration;

public record CyclomaticComplexityConfiguration(
        boolean countSwitchRule,
        boolean countSwitchBlockStatementGroup,
        boolean countConditionalExpression,
        boolean countConditionalOrExpression,
        boolean countConditionalAndExpression,
        boolean countCastExpression,
        boolean countMethodInvocation,
        boolean countMethodInvocationPNNA,
        boolean countFieldAccess,
        boolean countFieldAccessPNNA,
        boolean countCatchClause,
        boolean countAssertStatement,
        boolean countLambdaExpression,
        boolean countConstructorDeclaration,
        boolean countStaticInitializer,
        boolean countInstanceInitializer,
        boolean countInterfaceMethodDeclaration
) implements MetricConfiguration {}
