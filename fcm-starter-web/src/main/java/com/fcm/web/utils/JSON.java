package com.fcm.web.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.experimental.UtilityClass;
import org.apache.commons.lang3.StringUtils;

@UtilityClass
public class JSON {
    private final ObjectMapper objectMapper = new ObjectMapper();

    public String stringify(Object object) {
        try {
            return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(object);
        } catch (Exception ex) {
            return StringUtils.EMPTY;
        }
    }

    public String writeObject(Object object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (Exception ex) {
            return StringUtils.EMPTY;
        }
    }
}