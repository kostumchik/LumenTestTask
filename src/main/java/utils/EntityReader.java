package utils;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;

import java.io.IOException;

import static utils.FileReader.getFileByResourcePath;

public class EntityReader {
    public static ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);

    public static <T> T getEntityByClassName(Response response, Class<T> classname) {
        JavaType type = mapper.getTypeFactory().constructType(classname);
        try {
            return mapper.readValue(response.body().asString(), type);
        } catch (IOException e) {
            throw new RuntimeException("Mapping is failed. Check error message \n" + response.body().asString() + e);
        }
    }

    public static <T> T getEntityFromFile(String filePath, Class<T> clazz) {
        try {
            return mapper.readValue(getFileByResourcePath(filePath), clazz);
        } catch (IOException e) {
            throw new RuntimeException("Mapping is failed. Check error message \n" + e);
        }
    }


}
