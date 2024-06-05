package com.filipszm.codegauge.metric.antlr.listener;

import com.filipszm.codegauge.Java20Parser;
import com.filipszm.codegauge.Java20ParserBaseListener;
import com.filipszm.codegauge.metric.configutation.LinesOfCodeConfiguration;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class LinesOfCodeListener extends Java20ParserBaseListener {
    private final LinesOfCodeConfiguration configuration;

    @Getter
    private long metricValue = 0;

    @Override
    public void enterStatement(Java20Parser.StatementContext ctx) {
        metricValue++;
    }

    @Override
    public void enterVariableDeclarator(Java20Parser.VariableDeclaratorContext ctx) {
        metricValue++;
    }

    @Override
    public void enterPackageDeclaration(Java20Parser.PackageDeclarationContext ctx) {
        if (configuration.countPackageDeclaration())
            metricValue++;
    }

    @Override
    public void enterImportDeclaration(Java20Parser.ImportDeclarationContext ctx) {
        if (configuration.countImportDeclaration())
            metricValue++;
    }

    @Override
    public void enterFieldDeclaration(Java20Parser.FieldDeclarationContext ctx) {
        if (configuration.countFieldDeclaration())
            metricValue++;
    }

    @Override
    public void enterMethodDeclaration(Java20Parser.MethodDeclarationContext ctx) {
        if (configuration.countMethodDeclaration())
            metricValue++;
    }

    @Override
    public void enterClassDeclaration(Java20Parser.ClassDeclarationContext ctx) {
        if (configuration.countClassDeclaration())
            metricValue++;
    }

    @Override
    public void enterInterfaceDeclaration(Java20Parser.InterfaceDeclarationContext ctx) {
        if (configuration.countInterfaceDeclaration())
            metricValue++;
    }

    @Override
    public void enterInstanceInitializer(Java20Parser.InstanceInitializerContext ctx) {
        if (configuration.countInstanceInitializer())
            metricValue++;
    }

    @Override
    public void enterStaticInitializer(Java20Parser.StaticInitializerContext ctx) {
        if (configuration.countStaticInitializer())
            metricValue++;
    }

    @Override
    public void enterConstructorDeclaration(Java20Parser.ConstructorDeclarationContext ctx) {
        if (configuration.countConstructorDeclaration())
            metricValue++;
    }

    @Override
    public void enterRecordComponent(Java20Parser.RecordComponentContext ctx) {
        if (configuration.countRecordComponent())
            metricValue++;
    }

    @Override
    public void enterEnumConstant(Java20Parser.EnumConstantContext ctx) {
        if (configuration.countEnumConstant())
            metricValue++;
    }

    @Override
    public void enterFormalParameter(Java20Parser.FormalParameterContext ctx) {
        if (configuration.countFormalParameter())
            metricValue++;
    }

}
