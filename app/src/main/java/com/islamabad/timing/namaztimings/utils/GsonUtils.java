package com.islamabad.timing.namaztimings.utils;

/**
 * Created by Bilal Rashid on 7/23/2018.
 */

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import java.lang.reflect.Type;

public class GsonUtils {
    // register a type adapter on Gson
    private static Gson gson = new GsonBuilder().create();

    public static Gson getGson() {
        return gson;
    }

    public static String toJson(Object src) {
        return getGson().toJson(src);
    }

    public static JsonObject toJsonTree(Object src) {
        return getGson().toJsonTree(src).getAsJsonObject();
    }

    public static <T> T fromJson(String json, Class<T> classOfT) {
        return getGson().fromJson(json, classOfT);
    }

    public static <T> T fromJson(String json, Type typeOfT) {
        return getGson().fromJson(json, typeOfT);
    }
}
