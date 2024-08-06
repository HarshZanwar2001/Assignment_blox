package com.springboot.testapi;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class JsonParseUtil{

    private static final ObjectMapper mapper = new ObjectMapper();

    static {
        mapper.configure(DeserializationFeature.USE_BIG_DECIMAL_FOR_FLOATS, true);
        mapper.configure(DeserializationFeature.USE_BIG_INTEGER_FOR_INTS, true);
    }

    public static Object parseJson(String jsonString) throws IOException {
        JsonNode rootNode = mapper.readTree(jsonString);
        return convertJsonNode(rootNode);
    }

    private static Object convertJsonNode(JsonNode node) {
        if (node.isObject()) {
            return mapper.convertValue(node, new TypeReference<Map<String, Object>>() {});
        } else if (node.isArray()) {
            return mapper.convertValue(node, new TypeReference<List<Object>>() {});
        } else if (node.isNumber()) {
            if (node.isBigInteger()) {
                return node.bigIntegerValue();
            } else if (node.isBigDecimal()) {
                return node.decimalValue();
            } else if (node.isIntegralNumber()) {
                return new BigInteger(node.asText());
            } else {
                return new BigDecimal(node.asText());
            }
        } else if (node.isTextual()) {
            return node.textValue();
        } else if (node.isBoolean()) {
            return node.booleanValue();
        } else if (node.isNull()) {
            return null;
        }
        return node.asText();
    }

    public static void main(String[] args) {
        String jsonString = "{\"Name\": \"John Doe\", \"Email\": \"john.doe@example.com\", \"address\": \"123 Main St, Anytown, USA\"}";
        try {
            Object result = parseJson(jsonString);
            System.out.println(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

