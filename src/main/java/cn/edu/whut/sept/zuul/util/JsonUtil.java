package cn.edu.whut.sept.zuul.util;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author AnthonyCJ
 * @version 1.0
 * @description TODO
 * @date 2023/06/25 12:31
 */
public class JsonUtil {
    private final static ObjectMapper mapper;

    static {
        mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    public static class MapWithLong2StrSerializer extends JsonSerializer<Map<String, Long>> {
        @Override
        public void serialize(Map<String, Long> stringLongMap, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
            Map<String, String> map = new HashMap<>();
            for(Map.Entry<String, Long> entry : stringLongMap.entrySet()) {
                map.put(entry.getKey(), entry.getValue().toString());
            }
            jsonGenerator.writeObject(map);
        }
    }


    public static <T> String objToJson(T obj) {
        if (obj == null) {
            return null;
        }
        try {
            return obj instanceof String ? (String) obj : mapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            return null;
        }
    }

    public static <T> T jsonToObj(String str, Class<T> clazz){
        if(str == null || "".equals(str) || clazz == null){
            return null;
        }
        try {
            return clazz.equals(String.class) ? (T) str : mapper.readValue(str, clazz);
        } catch (Exception e) {
            return null;
        }
    }
}
