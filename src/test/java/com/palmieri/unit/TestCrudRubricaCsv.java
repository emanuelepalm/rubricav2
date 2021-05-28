package com.palmieri.unit;

import com.palmieri.entities.*;
import com.palmieri.models.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.ArrayList;

import static org.junit.Assert.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestCrudRubricaCsv {
    private static Rubrica rubrica;
    private static ArrayList<Contatti> mockResult = new ArrayList<>();
    private static boolean check;
    private static String displayName;

    @BeforeAll
    static void beforeAll(){
        System.out.println("Inizio Test");
        rubrica = new Rubrica();
        rubrica.setContattiList(new ArrayList<>());
    }

    @BeforeEach
    void beforeEach() {
        mockResult = new ArrayList<>();
        rubrica.setContattiList(new ArrayList<>());
    }

    @ParameterizedTest
    @CsvSource({"Pippo,Franco,1234567890,gino@gmail.com","Prancesco, Rodriguez, 3382002548 ,pipporodriguez@gmail.it"})
    @Order(1)
    @DisplayName("Add Persona")
    void test_001_add_csv(String firstName, String lastName, String number, String email){
        Contatti contatto = new Contatti(firstName, lastName, number, email);
        rubrica.getContattiList().add(contatto);
        for(Contatti each: rubrica.getContattiList()) {
            if (each == contatto) {
                check = true;
                break;
            }
        }
        assertTrue("Ricerca ok: ", check);
    }

    @ParameterizedTest
    @CsvSource({"Pippo,Franco,1234567890,gino@gmail.com","Prancesco, Rodriguez, 3382002548 ,pipporodriguez@gmail.it"})
    @Order(2)
    @DisplayName("Search un risultato")
    void test_002_search_csv(String firstName, String lastName, String number, String email){
        Contatti contatto = new Contatti(firstName, lastName, number, email);
        rubrica.getContattiList().add(contatto);
        mockResult.add(contatto);
        assertEquals(mockResult, rubrica.searchByName(contatto.getFirstName()));
    }

    @ParameterizedTest
    @CsvSource({"Pippo,Franco,1234567890,gino@gmail.com,Pippo,Franchini,1234567890,pino@gmail.com","Prancesco, Rodriguez, 3382002548 ,pipporodriguez@gmail.it,Prancesco,Gonzalez,1234567890,pas@fms.it"})
    @Order(3)
    @DisplayName("Search più risultati")
    void test_003_search_csv(String firstName, String lastName, String number, String email,String firstName2, String lastName2, String number2, String email2){
        Contatti contatto = new Contatti(firstName, lastName, number, email);
        Contatti contatto2 = new Contatti(firstName2, lastName2, number2, email2);
        mockResult.add(contatto);
        mockResult.add(contatto2);
        rubrica.getContattiList().add(contatto);
        rubrica.getContattiList().add(contatto2);
        assertEquals(mockResult, rubrica.searchByName(contatto.getFirstName()));
    }

    @Test
    @Order(4)
    @DisplayName("Search no risultati")
    void test_004_search_csv(){
        assertEquals(mockResult, rubrica.searchByName("Miriam"));
    }

    @ParameterizedTest
    @CsvSource({"Pippo,Franco,1234567890,gino@gmail.com,Pippo,Franchini,1234567890,pino@gmail.com","Prancesco, Rodriguez, 3382002548 ,pipporodriguez@gmail.it,Gennaro,Gonzalez,1234567890,pas@fms.it"})
    @Order(5)
    @DisplayName("Update persona")
    void test_005_update_csv(String firstName, String lastName, String number, String email,String firstName2, String lastName2, String number2, String email2){
        rubrica.addOne(new Contatti(firstName, lastName, number, email));
        Contatti contatto2 = new Contatti(firstName2, lastName2, number2, email2);
        rubrica.updateOne(0,contatto2);
        assertEquals(rubrica.getContattiList().get(0) , contatto2);
    }

    @ParameterizedTest
    @CsvSource({"Pippo,Franco,1234567890,gino@gmail.com","Prancesco, Rodriguez, 3382002548 ,pipporodriguez@gmail.it"})
    @Order(6)
    @DisplayName("Delete con successo")
    void test_006_delete_csv(String firstName, String lastName, String number, String email ){
        rubrica.addOne(new Contatti(firstName, lastName, number, email));
        rubrica.deleteOne(0);
    }

    @Test
    @Order(7)
    @DisplayName("Delete fallimentare")
    void test_007_delete_csv(){
            assertFalse(rubrica.deleteOne(9));
    }

    @ParameterizedTest
    @CsvSource({"Pippo,Franco,1234567890,gino@gmail.com","Prancesco, Rodriguez, 3382002548 ,pipporodriguez@gmail.it"})
    @Order(8)
    @DisplayName("Delete su un solo elemento")
    void test_008_delete_csv(String firstName, String lastName, String number, String email){
        Contatti contatto = new Contatti(firstName, lastName, number, email);
        mockResult.add(contatto);
        mockResult.remove(contatto);
        assertTrue(mockResult.isEmpty());
    }

    @AfterEach
    void afterEach() {

    }

    @AfterAll
    static void afterAll() {
        System.out.println("Test terminati");
    }

}
