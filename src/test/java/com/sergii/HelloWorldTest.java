package com.sergii;

import com.sergii.HelloWorld;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HelloWorldTest {
    @Test
    void stringHelloName() {
        assertEquals("Hello Ostap", HelloWorld.printName("Ostap"));
    }

    @Test
    void stringHelloName2() {
        assertEquals("Hello Sergii", HelloWorld.printName("Sergii"));
    }
}