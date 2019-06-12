package com.github.juanmougan.filter;

import lombok.RequiredArgsConstructor;

import java.util.Map;

/**
 * Direct mapping from HTTP methods.
 */
@RequiredArgsConstructor
public enum HttpMethod {
    GET("GET") {
        @Override
        public boolean validate(Map<String, String> request) {
            return "application/json".equals(request.get("Accept"));
        }
    }, POST("POST") {
        @Override
        public boolean validate(Map<String, String> request) {
            return "application/json".equals(request.get("Accept"))
                    && "application/json".equals(request.get("Content-Type"));
        }
    };

    private final String method;

    public abstract boolean validate(Map<String, String> request);
}
