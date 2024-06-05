package com.filipszm.codegauge.metric.evaluator.implementation;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.filipszm.codegauge.metric.antlr.listener.CyclomaticComplexityListener;
import com.filipszm.codegauge.metric.configutation.CyclomaticComplexityConfiguration;
import com.filipszm.codegauge.metric.evaluator.EvaluateParams;
import com.filipszm.codegauge.metric.evaluator.MetricEvaluator;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

public class CyclomaticComplexityEvaluator implements MetricEvaluator {

    @Override
    public void evaluate(EvaluateParams params) {
        ObjectNode node = params.metricValueNode();
        var listener = new CyclomaticComplexityListener(
                (CyclomaticComplexityConfiguration) params.metricConfiguration());
        ParseTreeWalker.DEFAULT.walk(listener, params.sourceTree());
        node.put("value", listener.getMetricValue());
    }
}
