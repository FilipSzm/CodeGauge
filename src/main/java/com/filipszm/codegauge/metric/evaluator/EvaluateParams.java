package com.filipszm.codegauge.metric.evaluator;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.filipszm.codegauge.config.model.MetricConfiguration;
import org.antlr.v4.runtime.tree.ParseTree;

public record EvaluateParams(
        ParseTree sourceTree,
        MetricConfiguration metricConfiguration,
        ObjectNode metricValueNode
) {}
