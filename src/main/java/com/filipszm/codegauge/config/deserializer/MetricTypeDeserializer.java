package com.filipszm.codegauge.config.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.filipszm.codegauge.metric.MetricType;

import java.io.IOException;

public class MetricTypeDeserializer extends JsonDeserializer<MetricType> {

    @Override
    public MetricType deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        String name = p.getValueAsString();
        return MetricType.forValue(name);
    }
}