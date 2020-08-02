package io.github.konieshadow.conf.common.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsonorg.JsonOrgModule;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.LinkedHashMap;
import java.util.Map;

public class JsonUtil {

    private static final ObjectMapper objectMapper = JsonMapper.builder()
            .addModule(new JsonOrgModule())
            .build();

    public static ObjectMapper objectMapper() {
        return objectMapper;
    }

    public static <T> T readValue(String json, Class<T> valueType) throws IOException {
        if (StringUtils.isEmpty(json)) {
            return null;
        }
        return objectMapper().readValue(json, valueType);
    }

    public static <T> T readValue(String json, TypeReference<T> valueType) throws IOException {
        if (StringUtils.isEmpty(json)) {
            return null;
        }
        return objectMapper().readValue(json, valueType);
    }

    public static <K, V> Map<K, V> readValueAsMap(String json, Class<K> keyType,
                                                  Class<V> valueType) throws IOException {
        if (StringUtils.isEmpty(json)) {
            return null;
        }
        return objectMapper().readValue(json, objectMapper().getTypeFactory()
                .constructMapType(LinkedHashMap.class, keyType, valueType));
    }

    public static JSONObject readJSONObject(String json) throws JsonProcessingException {
        return objectMapper().readValue(json, JSONObject.class);
    }

    public static JSONArray readJSONArray(String json) throws JsonProcessingException {
        return objectMapper().readValue(json, JSONArray.class);
    }

    public static String toString(Object value) throws UncheckedIOException {
        if (value == null) {
            return StringUtils.EMPTY;
        }

        try {
            return objectMapper().writeValueAsString(value);
        } catch (IOException exception) {
            throw new UncheckedIOException(exception);
        }
    }

}
