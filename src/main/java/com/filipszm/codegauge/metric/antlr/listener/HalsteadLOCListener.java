package com.filipszm.codegauge.metric.antlr.listener;

import com.filipszm.codegauge.Java20Parser;
import com.filipszm.codegauge.Java20ParserBaseListener;
import lombok.Getter;

@Getter
public class HalsteadLOCListener extends Java20ParserBaseListener {
    private long metricValue = 0;

    @Override
    public void enterStatement(Java20Parser.StatementContext ctx) {
        System.out.println("Statement: " + ctx.getText());
        metricValue++;
    }

    @Override
    public void enterVariableDeclarator(Java20Parser.VariableDeclaratorContext ctx) {
        System.out.println("VariableDeclarator: " + ctx.getText());
        metricValue++;
    }

}
