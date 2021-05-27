package unit;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import utils.FileHandler;

import static org.junit.Assert.assertEquals;
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestCrudFile {
    private static final String BASEPATH = "C:\\Users\\lelej\\Desktop\\rubricav2\\src\\test\\java\\logs";
    private static final String fileName = "pippo.txt";
    private static final String body = "X gon' give it to ya (what) Fuck waiting for you to get it on your own, X gon' deliver to ya (uh)";

    @BeforeAll
    static void beforeAll(){}

    @BeforeEach
    void beforeEach() {}

    @ParameterizedTest
    @CsvSource({BASEPATH})
    @Order(1)
    @DisplayName("Write File")
    void test_001_new(String path){
        FileHandler.newFile(path, fileName);
    }

    @ParameterizedTest
    @CsvSource({BASEPATH})
    @Order(2)
    @DisplayName("Write File")
    void test_002_write(String path){
        FileHandler.writeFile(fileName, path,  body);
    }

    @ParameterizedTest
    @CsvSource({BASEPATH})
    @Order(3)
    @DisplayName("Read")
    void test_003_read(String path){
       assertEquals(body, FileHandler.readFile(path,fileName));
    }

    @ParameterizedTest
    @CsvSource({BASEPATH})
    @Order(4)
    @DisplayName("Delete")
    void test_004_delete(String path){
        assertEquals(true ,FileHandler.deleteFile( fileName,path));
    }




    @AfterEach
    void afterEach() {
    }

    @AfterAll
    static void afterAll() {
    }
}
