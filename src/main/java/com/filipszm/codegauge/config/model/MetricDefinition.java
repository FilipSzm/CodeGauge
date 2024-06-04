package com.filipszm.codegauge.config.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.filipszm.codegauge.config.deserializer.MetricConfigurationDeserializer;
import com.filipszm.codegauge.config.deserializer.MetricTypeDeserializer;
import com.filipszm.codegauge.config.serializer.MetricTypeSerializer;
import com.filipszm.codegauge.metric.MetricType;
import lombok.Data;

@Data
public class MetricDefinition {
    @JsonSerialize(using = MetricTypeSerializer.class)
    @JsonDeserialize(using = MetricTypeDeserializer.class)
    private MetricType type;

    @JsonDeserialize(using = MetricConfigurationDeserializer.class)
    private MetricConfiguration config;

}
