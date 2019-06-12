package com.github.juanmougan.filter;

import com.google.common.collect.ImmutableMap;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PseudoFilterTest {

    private PseudoFilter pseudoFilter = new PseudoFilter();

    @Test
    void validGet() {
        Map<String, String> request = ImmutableMap.of("method", "GET", "Accept", "application/json");
        Map<String, String> response = new HashMap<>();

        pseudoFilter.doFilter(request, response);

        assertEquals("ok", response.get("status"));
    }

    @Test
    void invalidPost() {
        Map<String, String> request = ImmutableMap.of("method", "POST", "Accept", "application/json");
        Map<String, String> response = new HashMap<>();

        pseudoFilter.doFilter(request, response);

        assertEquals("fail", response.get("status"));
    }

}