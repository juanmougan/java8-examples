package com.github.juanmougan;

import com.github.juanmougan.functions.CommentedException;
import com.github.juanmougan.functions.Person;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.function.Function;

public class FunctionExamples {

    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();

    @Test
    public void testNestedFunctions() {
        // Given
        Person p = Person.builder().firstName("Carlos").lastName("González").dni(-1L).build();
        Function<String, ? extends CommentedException> exGenerator = (String cause) ->
                new CommentedException("Sorry, we had a problem due to: " + cause);
        Function<Function<String, ? extends CommentedException>, Void> validation =
                (Function<String, ? extends CommentedException> ex)
                        -> {
                    if (!p.validate()) {
                        ex.apply("Exception");
                    }
                    return null;
                };
        // When
        exGenerator.apply("por las dudas");
        // Then
        exceptionRule.expect(CommentedException.class);
        exceptionRule.expectMessage("Sorry, we had a problem due to: por las dudas");
    }

}
