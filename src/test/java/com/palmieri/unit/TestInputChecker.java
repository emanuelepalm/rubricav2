package com.palmieri.unit;

import org.junit.jupiter.api.*;
import com.palmieri.utils.InputHandler;

import static org.junit.jupiter.api.Assertions.assertTrue;
@TestInstance(TestInstance.Lifecycle.PER_CLASS)

public class TestInputChecker {
    @BeforeAll
    static void beforeAll(){
    }
    @BeforeEach
    void beforeEach() {
    }
    @Test
    @DisplayName("Check Number")
    void testCheckNumber(){
        assertTrue(InputHandler.checkNumber("1900000000"));
    }
    @Test
    @DisplayName("Check Email")
    void testCheckEmail(){
        assertTrue(InputHandler.checkEmail("pippo@gmail.it"));
    }
    @AfterEach
    void afterEach() {

    }

    @AfterAll
    static void afterAll() {
    }

}
