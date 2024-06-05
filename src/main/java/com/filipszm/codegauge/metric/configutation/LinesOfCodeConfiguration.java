package com.filipszm.codegauge.metric.configutation;

import com.filipszm.codegauge.config.model.MetricConfiguration;

public record LinesOfCodeConfiguration(
        boolean countPackageDeclaration,
        boolean countImportDeclaration,
        boolean countFieldDeclaration,
        boolean countMethodDeclaration,
        boolean countClassDeclaration,
        boolean countInterfaceDeclaration,
        boolean countInstanceInitializer,
        boolean countStaticInitializer,
        boolean countConstructorDeclaration,
        boolean countRecordComponent,
        boolean countEnumConstant,
        boolean countFormalParameter
) implements MetricConfiguration {}
