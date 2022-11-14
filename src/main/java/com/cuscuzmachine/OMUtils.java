package com.cuscuzmachine;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public class OMUtils {
    public static ObjectMapper getOB(){
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.findAndRegisterModules();

        return objectMapper;
    }

    public static <E> E readValue(String stringJSON, Class<E> e){

        try {
            return getOB().readValue(stringJSON, e);
        } catch (JsonProcessingException ex) {
            throw new RuntimeException(ex);
        }

    }

}
