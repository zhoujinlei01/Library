package com.lay.common.util;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

/**
 * @author Lay
 * @date 2023/12/12
 */
public class JsonUtils {
    //默认GSON解析
    private static Gson sJsonExecutor = new Gson();

    public static String toJson(Object obj) {
        return sJsonExecutor.toJson(obj);
    }

    public static <T> T fromJson(String json, Class<T> classOfT) {
        return sJsonExecutor.fromJson(json, classOfT);
    }

    public static <T> T fromJson(String json, Class<T> rawType, Class classOfT) {
        Type type = TypeToken.getParameterized(rawType, classOfT).getType();
        return sJsonExecutor.fromJson(json, type);
    }

    public static <T> List<T> fromJsonList(String json, Class<T> classOfT) {
        Type listType = TypeToken.getParameterized(List.class, classOfT).getType();
        return sJsonExecutor.fromJson(json, listType);
    }
}