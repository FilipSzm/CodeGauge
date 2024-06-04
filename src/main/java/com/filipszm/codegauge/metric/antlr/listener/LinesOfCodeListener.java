package com.filipszm.codegauge.metric.antlr.listener;

import com.filipszm.codegauge.Java20Parser;
import com.filipszm.codegauge.Java20ParserBaseListener;
import com.filipszm.codegauge.metric.configutation.LinesOfCodeConfiguration;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class LinesOfCodeListener extends Java20ParserBaseListener {
    private final LinesOfCodeConfiguration configuration;

    private long metricValue = 0;

    @Override
    public void enterStatement(Java20Parser.StatementContext ctx) {
        System.out.println("Statement: " + ctx.getText());
        metricValue++; // TODO
    }

}
