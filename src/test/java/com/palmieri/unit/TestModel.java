package com.palmieri.unit;

import com.palmieri.entities.Menu;
import com.palmieri.entities.Rubrica;
import com.palmieri.models.Contatti;
import com.palmieri.models.MapModel;
import com.palmieri.models.Ruoli;
import com.palmieri.utils.FileHandler;
import org.junit.jupiter.api.*;


import java.lang.reflect.Field;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestModel {
    private final static String jsonContatti = "[{\"firstName\":\"pippo\",\"lastName\":\"baudo\",\"number\":\"1234567890\",\"email\":\"lele@gmail.com\",\"uid\":\"gbkfuv-pb\"}]";
    private final static String jsonContatti2 = "[{\"firstName\":\"pippo\",\"lastName\":\"baudo\",\"number\":\"1234567890\",\"email\":\"lele@gmail.com\",\"uid\":\"gbkfuv-pb\"},{\"firstName\":\"Mimmo\",\"lastName\":\"Andrealli\",\"number\":\"1234567890\",\"email\":\"mimmo.andrealli@gmail.com\",\"uid\":\"tofdzj-MA\"}]";
    private final static String jsonMap = "[{\"ruolo\":{\"uuid\":\"f8561573-9d62-4a80-aefb-617eaf8973fa\",\"role\":\"Admin\",\"description\":\"admin\"},\"rubrica\":{\"contattiList\":[{\"firstName\":\"pippo\",\"lastName\":\"baudo\",\"number\":\"1234567890\",\"email\":\"lele@gmail.com\",\"uid\":\"gbkfuv-pb\"}]}}]";
    private final static String jsonMap2 = "[{\"ruolo\":{\"uuid\":\"dbf8ec86-ada7-4853-91bf-5c5a4e2afc38\",\"role\":\"admin\",\"description\":\"admin\"},\"rubrica\":{\"contattiList\":[{\"firstName\":\"pippo\",\"lastName\":\"baudo\",\"number\":\"1234567890\",\"email\":\"lele@gmail.com\",\"uid\":\"gbkfuv-pb\"},{\"firstName\":\"Mimmo\",\"lastName\":\"Andrealli\",\"number\":\"1234567890\",\"email\":\"mimmo.andrealli@gmail.com\",\"uid\":\"tofdzj-MA\"}]}}][{\"ruolo\":{\"uuid\":\"dbf8ec86-ada7-4853-91bf-5c5a4e2afc38\",\"role\":\"admin\",\"description\":\"admin\"},\"rubrica\":{\"contattiList\":[{\"firstName\":\"pippo\",\"lastName\":\"baudo\",\"number\":\"1234567890\",\"email\":\"lele@gmail.com\",\"uid\":\"gbkfuv-pb\"},{\"firstName\":\"Mimmo\",\"lastName\":\"Andrealli\",\"number\":\"1234567890\",\"email\":\"mimmo.andrealli@gmail.com\",\"uid\":\"tofdzj-MA\"}]}}]";
    private static ArrayList<Contatti> contattiArrayList = new ArrayList<>();
    private static ArrayList<Contatti> contattiArrayList2 = new ArrayList<>();
    private static ArrayList<Contatti> expectedContattiArrayList = new ArrayList<>();
    private static ArrayList<Contatti> expectedContattiArrayList2 = new ArrayList<>();
    private static ArrayList<MapModel> mapModelArrayList = new ArrayList<>();
    private static ArrayList<MapModel> mapModelArrayList2 = new ArrayList<>();
    private static ArrayList<MapModel> expectedMapModelArrayList = new ArrayList<>();
    private static ArrayList<MapModel> expectedMapModelArrayList2 = new ArrayList<>();
    private static Menu menu = new Menu();
    private static Rubrica rubrica = new Rubrica();

    @BeforeAll
    static void beforeAll() {
        Contatti pippo = new Contatti("pippo", "baudo", "1234567890", "lele@gmail.com", "gbkfuv-pb");
        contattiArrayList.add(pippo);
        rubrica.setContattiList(contattiArrayList);
        expectedContattiArrayList.add(pippo);
        MapModel mapModel = new MapModel(new Ruoli("f8561573-9d62-4a80-aefb-617eaf8973fa","Admin","admin"),new Rubrica(contattiArrayList));
        mapModelArrayList.add(mapModel);
        expectedMapModelArrayList.add(mapModel);
    }



    @BeforeEach
    void beforeEach() {

    }

    @Order(1)
    @Test
    @DisplayName("Test convert json to ArrayList contatti")
    void testJsonToRubrica() {
        assertTrue(jsonContatti.equals(rubrica.rubricaToString(rubrica.getContattiList())));
    }

    @Order(2)
    @Test
    @DisplayName("Test convert json to ArrayList contatti con Override equals() in Contatti")
    void testRubricaToJson() {
        for(int i = 0; i < rubrica.stringToRubrica(jsonContatti).size(); i++) {
            assertTrue(expectedContattiArrayList.get(i).equals(rubrica.stringToRubrica(jsonContatti).get(i)));
        }

    }
    @Order(3)
    @Test
    @DisplayName("Test convert json to ArrayList contatti")
    void testRubricaToJson_003() {
        for(int i = 0; i < expectedContattiArrayList.size(); i++) {
            for(Field f:  expectedContattiArrayList.get(i).getClass().getDeclaredFields()){
                f.setAccessible(true);
                try {
                    assertEquals(f.get(rubrica.stringToRubrica(jsonContatti).get(i)),f.get(expectedContattiArrayList.get(i)));

                    }catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                }
            }
        }

    @Order(4)
    @Test
    @DisplayName("Test convert json to ArrayList MapModel")
    void testMapToJson_004() {
        try {
            for (int i = 0; i < expectedMapModelArrayList.size(); i++) {
                for (Field f : expectedMapModelArrayList.get(i).getRuolo().getClass().getDeclaredFields()) {
                    f.setAccessible(true);
                    try {
                        assertEquals(f.get(menu.stringToMap(jsonMap).get(i).getRuolo()), f.get(expectedMapModelArrayList.get(i).getRuolo()));

                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
                for (int j = 0; j < expectedMapModelArrayList.get(i).getRubrica().getContattiList().size(); j++) {
                    for (Field f : expectedMapModelArrayList.get(i).getRubrica().getContattiList().get(j).getClass().getDeclaredFields()) {
                        f.setAccessible(true);
                        try {
                            assertEquals(f.get(menu.stringToMap(jsonMap).get(i).getRubrica().getContattiList().get(j)), f.get(expectedMapModelArrayList.get(i).getRubrica().getContattiList().get(j)));

                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        } catch (IndexOutOfBoundsException e) {
            assertTrue(false);
            System.out.println(e.getMessage());
        }
    }
    @Order(5)
    @Test
    @DisplayName("Test convert json to ArrayList MapModel")
    void testJsonToMap() {
        assertTrue(jsonMap.equals(FileHandler.ArrayMapToString(mapModelArrayList)));
    }

    @Order(6)
    @Test
    @DisplayName("Test convert generico json to ArrayList con override equals in contatti")
    void testJsonToArrayContatti() {
        for (int i = 0; i < expectedContattiArrayList.size(); i++) {
            assertTrue(expectedContattiArrayList.get(i).equals(FileHandler.jsonToArrayList(jsonContatti, Contatti[].class).get(i)));

        }
    }
    @Order(7)
    @Test
    @DisplayName("Test convert generico json to ArrayList con override equals in map model")
    void testJsonToArrayMap() {
        for (int i = 0; i < expectedMapModelArrayList.size(); i++) {
            assertTrue(expectedMapModelArrayList.get(i).equals(FileHandler.jsonToArrayList(jsonContatti, MapModel[].class).get(i)));

        }
    }
    @Order(8)
    @Test
    @DisplayName("Test convert generico ArrayList to json")
    void testArrayContattiToJson() {
        assertEquals(jsonContatti,FileHandler.arrayListToJson(rubrica.getContattiList()));
    }
    @Order(8)
    @Test
    @DisplayName("Test convert generico ArrayList to json")
    void testArrayMapToJson() {
        assertEquals(jsonMap,FileHandler.arrayListToJson(mapModelArrayList));
    }
    @AfterEach
    void afterEach() {

    }

    @AfterAll
    static void afterAll() {
    }


}
