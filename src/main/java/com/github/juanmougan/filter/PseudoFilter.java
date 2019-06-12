package com.github.juanmougan.filter;

import java.util.Map;

/**
 * Example mocking a real Filter.
 */
public class PseudoFilter {
    public void doFilter(Map<String, String> req, Map<String, String> res) {
        HttpMethod method = HttpMethod.valueOf(req.get("method"));
        if (method.validate(req)) {
            // Here, I should do `chain.doFilter()`
            System.out.println("Valid request!");
            res.put("status", "ok");
        } else {
            // Here, I should set error to request
            System.out.println("Bad request :(");
            res.put("status", "fail");
        }
    }
}
