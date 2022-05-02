package com.snaidermans.holidays.mapping;

import org.springframework.lang.NonNull;

public enum ResponseMapping {

    YES(true, "yes"),
    NO(false, "no");

    private final boolean flag;
    private final String response;

    ResponseMapping(boolean flag, String response) {
        this.flag = flag;
        this.response = response;
    }

    public static String map(@NonNull boolean flag) {
        for (ResponseMapping responseMapping : values()) {
            if (responseMapping.flag == flag) {
                return responseMapping.response;
            }
        }
        throw new IllegalStateException("Unable to map response");
    }
}
