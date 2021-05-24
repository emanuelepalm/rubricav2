package unit;

import org.junit.jupiter.api.*;
import utils.InputHandler;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestInputChecker {
    @BeforeAll
    static void beforeAll(){
        System.out.println("before all");
    }
    @BeforeEach
    void beforeEach() {
        System.out.println("before each");
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
        System.out.println("after each");
    }

    @AfterAll
    static void afterAll() {
        System.out.println("after all");
    }

}
