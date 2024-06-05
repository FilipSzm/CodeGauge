package com.filipszm.codegauge.metric.evaluator.implementation;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.filipszm.codegauge.metric.antlr.listener.LinesOfCodeListener;
import com.filipszm.codegauge.metric.configutation.LinesOfCodeConfiguration;
import com.filipszm.codegauge.metric.evaluator.EvaluateParams;
import com.filipszm.codegauge.metric.evaluator.MetricEvaluator;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

public class LinesOfCodeEvaluator implements MetricEvaluator {

    @Override
    public void evaluate(EvaluateParams params) {
        ObjectNode node = params.metricValueNode();
        var listener = new LinesOfCodeListener(
                (LinesOfCodeConfiguration) params.metricConfiguration());
        ParseTreeWalker.DEFAULT.walk(listener, params.sourceTree());
        node.put("value", listener.getMetricValue());
    }
}
