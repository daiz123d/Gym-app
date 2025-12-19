package com.uilover.workout.converter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.uilover.workout.entity.Lession;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

@Converter
public class LessionListConverter implements AttributeConverter<List<Lession>, String> {
    private final Gson gson = new Gson();
    
    @Override
    public String convertToDatabaseColumn(List<Lession> lessions) {
        if (lessions == null || lessions.isEmpty()) {
            return "[]";
        }
        return gson.toJson(lessions);
    }
    
    @Override
    public List<Lession> convertToEntityAttribute(String json) {
        if (json == null || json.isEmpty() || json.equals("[]")) {
            return new ArrayList<>();
        }
        Type listType = new TypeToken<ArrayList<Lession>>(){}.getType();
        return gson.fromJson(json, listType);
    }
}

