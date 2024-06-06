package com.filipszm.codegauge.metric.antlr.listener;

import com.filipszm.codegauge.Java20Parser;
import com.filipszm.codegauge.Java20ParserBaseListener;
import com.filipszm.codegauge.metric.configutation.CyclomaticComplexityConfiguration;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CyclomaticComplexityListener extends Java20ParserBaseListener {
    private final CyclomaticComplexityConfiguration configuration;

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
    public void enterSwitchRule(Java20Parser.SwitchRuleContext ctx) {
        if (configuration.countSwitchRule())
            metricValue++;
    }

    @Override
    public void enterSwitchBlockStatementGroup(Java20Parser.SwitchBlockStatementGroupContext ctx) {
        if (configuration.countSwitchBlockStatementGroup())
            metricValue++;
    }

    @Override
    public void enterConditionalExpression(Java20Parser.ConditionalExpressionContext ctx) {
        if (configuration.countConditionalExpression()) {
            if (1 != ctx.children.size())
                metricValue++;
        }
    }

    @Override
    public void enterConditionalOrExpression(Java20Parser.ConditionalOrExpressionContext ctx) {
        if (configuration.countConditionalOrExpression()) {
            if (1 != ctx.children.size())
                metricValue++;
        }
    }

    @Override
    public void enterConditionalAndExpression(Java20Parser.ConditionalAndExpressionContext ctx) {
        if (configuration.countConditionalAndExpression()) {
            if (1 != ctx.children.size())
                metricValue++;
        }
    }

    @Override
    public void enterCastExpression(Java20Parser.CastExpressionContext ctx) {
        if (configuration.countCastExpression())
            metricValue++;
    }

    @Override
    public void enterMethodInvocation(Java20Parser.MethodInvocationContext ctx) {
        if (configuration.countMethodInvocation())
            metricValue++;
    }

    @Override
    public void enterPrimaryNoNewArray(Java20Parser.PrimaryNoNewArrayContext ctx) {
        if (configuration.countMethodInvocationPNNA()) {
            if (null != ctx.LPAREN())
                metricValue++;
        }
    }

    @Override
    public void enterPNNA(Java20Parser.PNNAContext ctx) {
        if (configuration.countMethodInvocationPNNA()) {
            if (null != ctx.LPAREN())
                metricValue++;
        } else if (configuration.countFieldAccessPNNA()) {
            if (null == ctx.LPAREN() && null != ctx.DOT() && null != ctx.Identifier()) {
                metricValue++;
            }
        }
    }

    @Override
    public void enterFieldAccess(Java20Parser.FieldAccessContext ctx) {
        if (configuration.countFieldAccess()) {
            if (null == ctx.SUPER())
                metricValue++;
        }
    }

    @Override
    public void enterExpressionName(Java20Parser.ExpressionNameContext ctx) {
        if (configuration.countFieldAccessPNNA()) {
            if (null != ctx.ambiguousName())
                metricValue++;
        }
    }

    @Override
    public void enterCatchClause(Java20Parser.CatchClauseContext ctx) {
        if (configuration.countCatchClause())
            metricValue++;
    }

    @Override
    public void enterAssertStatement(Java20Parser.AssertStatementContext ctx) {
        if (configuration.countAssertStatement())
            metricValue++;
    }

    @Override
    public void enterDoStatement(Java20Parser.DoStatementContext ctx) {
        metricValue++;
    }

    @Override
    public void enterIfThenElseStatementNoShortIf(Java20Parser.IfThenElseStatementNoShortIfContext ctx) {
        metricValue++;
    }

    @Override
    public void enterWhileStatementNoShortIf(Java20Parser.WhileStatementNoShortIfContext ctx) {
        metricValue++;
    }

    @Override
    public void enterForStatementNoShortIf(Java20Parser.ForStatementNoShortIfContext ctx) {
        metricValue++;
    }

    @Override
    public void enterIfThenStatement(Java20Parser.IfThenStatementContext ctx) {
        metricValue++;
    }

    @Override
    public void enterIfThenElseStatement(Java20Parser.IfThenElseStatementContext ctx) {
        metricValue++;
    }

    @Override
    public void enterWhileStatement(Java20Parser.WhileStatementContext ctx) {
        metricValue++;
    }

    @Override
    public void enterForStatement(Java20Parser.ForStatementContext ctx) {
        metricValue++;
    }

}
