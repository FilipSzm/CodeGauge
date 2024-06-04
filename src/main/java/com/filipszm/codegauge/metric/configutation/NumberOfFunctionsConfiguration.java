package com.filipszm.codegauge.metric.configutation;

import com.filipszm.codegauge.config.model.MetricConfiguration;

public record NumberOfFunctionsConfiguration(boolean yes, boolean no) implements MetricConfiguration {}
