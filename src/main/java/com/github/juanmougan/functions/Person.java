package com.github.juanmougan.functions;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Person {

    private final String firstName;

    private final String lastName;

    private final Long dni;

    public boolean validate() {
        return dni > 0;
    }

}
