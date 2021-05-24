package unit;

import entities.Rubrica;
import models.Contatti;
import org.junit.jupiter.api.*;

import java.util.ArrayList;

import static org.junit.Assert.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestCrudRubrica {
    private static Rubrica rubrica;
    private static ArrayList<Contatti> mockResult = new ArrayList<>();
    private static Contatti pippo;
    private static Contatti pippo2;
    private static Contatti paolo;
    private static boolean check;

    @BeforeAll
    static void beforeAll(){
        rubrica = new Rubrica();
        rubrica.setContattiList(new ArrayList<>());
        pippo = new Contatti("Pippo","Franco","1234567890","gino@gmail.com");
        pippo2 = new Contatti("Pippo","Mariello","1234567890","gino@gmail.com");
        paolo = new Contatti("Paolo","Andreotti","1234567890","gino@gmail.com");

    }

    @BeforeEach
    void beforeEach() {
        mockResult = new ArrayList<>();
    }

    @Test
    @Order(1)
    @DisplayName("Add Persona")
    void test_001_add(){
        rubrica.getContattiList().add(pippo);
        for(Contatti contatto: rubrica.getContattiList()) {
            if(contatto == pippo) {
               check = true;
            }
        }
        assertTrue("Ricerca ok: ", check);
    }

    @Test
    @Order(2)
    @DisplayName("Search un risultato")
    void test_002_search(){
        mockResult.add(pippo);
        assertEquals(mockResult, rubrica.searchByName("Pippo"));
    }

    @Test
    @Order(3)
    @DisplayName("Search più risultati")
    void test_003_search(){
        mockResult.add(pippo);
        mockResult.add(pippo2);
        rubrica.getContattiList().add(pippo2);
        assertEquals(mockResult, rubrica.searchByName("Pippo"));
    }

    @Test
    @Order(4)
    @DisplayName("Search no risultati")
    void test_004_search(){
        assertEquals(mockResult, rubrica.searchByName("Miriam"));
    }

    @Test
    @Order(5)
    @DisplayName("Update persona")
    void test_005_update(){
        Contatti mimmo = rubrica.addPersona("Mimmo","Giovenchi","1234567890","mimmo@gmail.com");
        rubrica.updateOne(1,mimmo);
        assertEquals(rubrica.getContattiList().get(1),mimmo);
    }

    @Test
    @Order(6)
    @DisplayName("Delete con successo")
    void test_006_delete(){
        rubrica.deleteOne(1);
    }

    @Test
    @Order(7)
    @DisplayName("Delete fallimentare")
    void test_007_delete(){
        assertFalse(rubrica.deleteOne(9));
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
