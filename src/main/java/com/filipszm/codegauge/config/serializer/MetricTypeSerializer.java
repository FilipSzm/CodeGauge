package com.filipszm.codegauge.config.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.filipszm.codegauge.metric.MetricType;

import java.io.IOException;

public class MetricTypeSerializer extends JsonSerializer<MetricType> {

    @Override
    public void serialize(MetricType value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeString(value.getName());
    }
}
