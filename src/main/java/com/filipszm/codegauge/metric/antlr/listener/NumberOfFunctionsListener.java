package com.filipszm.codegauge.metric.antlr.listener;

import com.filipszm.codegauge.Java20Parser;
import com.filipszm.codegauge.Java20ParserBaseListener;
import com.filipszm.codegauge.metric.configutation.NumberOfFunctionsConfiguration;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class NumberOfFunctionsListener extends Java20ParserBaseListener {
    private final NumberOfFunctionsConfiguration configuration;

    @Getter
    private long metricValue = 0;

    @Override
    public void enterMethodDeclaration(Java20Parser.MethodDeclarationContext ctx) {
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
    public void enterLambdaExpression(Java20Parser.LambdaExpressionContext ctx) {
        if (configuration.countLambdaExpression())
            metricValue++;
    }

    @Override
    public void enterInterfaceMethodDeclaration(Java20Parser.InterfaceMethodDeclarationContext ctx) {
        if (configuration.countInterfaceMethodDeclaration())
            metricValue++;
    }

}
