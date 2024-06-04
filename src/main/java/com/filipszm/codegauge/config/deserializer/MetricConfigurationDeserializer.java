package com.filipszm.codegauge.config.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.filipszm.codegauge.config.model.MetricConfiguration;
import com.filipszm.codegauge.config.model.MetricDefinition;
import com.filipszm.codegauge.metric.MetricType;

import java.io.IOException;

public class MetricConfigurationDeserializer extends JsonDeserializer<MetricConfiguration> {
    @Override
    public MetricConfiguration deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        ObjectMapper mapper = (ObjectMapper) p.getCodec();
        JsonNode node = p.readValueAsTree();

        MetricType metricType = ((MetricDefinition) p.getParsingContext().getCurrentValue()).getType();
        return mapper.treeToValue(node, metricType.getConfigClass());
    }
}
