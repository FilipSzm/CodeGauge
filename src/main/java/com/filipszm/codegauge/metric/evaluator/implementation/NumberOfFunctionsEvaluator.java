package com.filipszm.codegauge.metric.evaluator.implementation;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.filipszm.codegauge.metric.antlr.listener.NumberOfFunctionsListener;
import com.filipszm.codegauge.metric.configutation.NumberOfFunctionsConfiguration;
import com.filipszm.codegauge.metric.evaluator.EvaluateParams;
import com.filipszm.codegauge.metric.evaluator.MetricEvaluator;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

public class NumberOfFunctionsEvaluator implements MetricEvaluator {

    @Override
    public void evaluate(EvaluateParams params) {
        ObjectNode node = params.metricValueNode();
        var listener = new NumberOfFunctionsListener(
                (NumberOfFunctionsConfiguration) params.metricConfiguration());
        ParseTreeWalker.DEFAULT.walk(listener, params.sourceTree());
        node.put("value", listener.getMetricValue());
    }
}
