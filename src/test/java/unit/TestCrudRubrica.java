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
    private static String displayName;

    @BeforeAll
    static void beforeAll(){
        System.out.println("Inizio Test");
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
    void test_001_add(TestInfo testInfo){
        displayName = testInfo.getDisplayName();
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
    void test_002_search(TestInfo testInfo){
        displayName = testInfo.getDisplayName();
        mockResult.add(pippo);
        assertEquals(mockResult, rubrica.searchByName("Pippo"));
    }

    @Test
    @Order(3)
    @DisplayName("Search più risultati")
    void test_003_search(TestInfo testInfo){
        displayName = testInfo.getDisplayName();
        mockResult.add(pippo);
        mockResult.add(pippo2);
        rubrica.getContattiList().add(pippo2);
        assertEquals(mockResult, rubrica.searchByName("Pippo"));
    }

    @Test
    @Order(4)
    @DisplayName("Search no risultati")
    void test_004_search(TestInfo testInfo){
        displayName = testInfo.getDisplayName();
        assertEquals(mockResult, rubrica.searchByName("Miriam"));
    }

    @Test
    @Order(5)
    @DisplayName("Update persona")
    void test_005_update(TestInfo testInfo){
        displayName = testInfo.getDisplayName();
        Contatti mimmo = rubrica.addPersona("Mimmo","Giovenchi","1234567890","mimmo@gmail.com");
        rubrica.updateOne(1,mimmo);
        assertEquals(rubrica.getContattiList().get(1) , mimmo);
    }

    @Test
    @Order(6)
    @DisplayName("Delete con successo")
    void test_006_delete(TestInfo testInfo){
        displayName = testInfo.getDisplayName();
        rubrica.deleteOne(1);
    }

    @Test
    @Order(7)
    @DisplayName("Delete fallimentare")
    void test_007_delete(TestInfo testInfo){
            displayName = testInfo.getDisplayName();
            assertFalse(rubrica.deleteOne(9));
    }

    @Test
    @Order(8)
    @DisplayName("Delete su un solo elemento")
    void test_008_delete(TestInfo testInfo){
        displayName = testInfo.getDisplayName();
        mockResult.add(pippo);
        mockResult.remove(pippo);
        assertTrue(mockResult.isEmpty());
    }

    @AfterEach
    void afterEach() {
        System.out.println(displayName + " Eseguito");
    }

    @AfterAll
    static void afterAll() {
        System.out.println("Test terminati");
    }

}
