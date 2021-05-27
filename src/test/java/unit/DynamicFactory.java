package unit;

import entities.Rubrica;
import models.Contatti;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;
import utils.FileHandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;

public class DynamicFactory {

    @TestFactory
    Collection<DynamicTest> dinamicTestsFile() {
        String path = "C:\\Users\\lelej\\Desktop\\rubricav2\\src\\test\\java\\logs";
        String fileName = "pippo.txt";
        String body = "X gon' give it to ya (what) Fuck waiting for you to get it on your own, X gon' deliver to ya (uh)";

        return Arrays.asList(
                dynamicTest("1° new file",()-> assertTrue(FileHandler.newFile(path,fileName))),
                dynamicTest("2° write & read file",()-> {
                    FileHandler.writeFile(fileName,path,body);
                    assertEquals(body, FileHandler.readFile(path,fileName));
                }),
                dynamicTest("3° delete file",()-> assertTrue(FileHandler.deleteFile(fileName,path)))
                );
    }
    @TestFactory
    Collection<DynamicTest> dinamicTestsRubrica() {
        Rubrica rubrica = new Rubrica();
        ArrayList<Contatti> mockResult = new ArrayList<>();
        Contatti pippo = new Contatti("Pippo","Franco","1234567890","gino@gmail.com");
        Contatti pippo2 = new Contatti("Pippo","Mariello","1234567890","gino@gmail.com");
        Contatti paolo = new Contatti("Paolo","Andreotti","1234567890","gino@gmail.com");

        return Arrays.asList(
                dynamicTest("1 add contatto",()->{
                    boolean check = false;
                    rubrica.addOne(pippo);
                    for(Contatti contatto: rubrica.getContattiList()) {
                        if(contatto == pippo) {
                            check = true;
                        }
                    }
                    assertTrue(check);
                }),
                dynamicTest("2 search un result", ()->{
                    mockResult.add(pippo);
                    assertEquals(mockResult, rubrica.searchByName("Pippo"));
                }),
                dynamicTest("3 add un altro contatto",()-> {
                            boolean check = false;
                            rubrica.addOne(pippo2);
                            for (Contatti contatto : rubrica.getContattiList()) {
                                if (contatto == pippo2) {
                                    check = true;
                                    break;
                                }
                            }
                    assertTrue(check);
                }),
                dynamicTest("4 search 2 result", ()->{
                    mockResult.add(pippo2);
                    assertEquals(mockResult, rubrica.searchByName("Pippo"));
                }),
                dynamicTest("5 search 0 result", ()->assertEquals(new ArrayList<Contatti>(), rubrica.searchByName("Prancesco"))),
                dynamicTest("6 Update", ()->{
                    rubrica.updateOne(1,paolo);
                    assertEquals(rubrica.getContattiList().get(1) , paolo);
                }),
                dynamicTest("7 Delete OK!", ()->assertTrue(rubrica.deleteOne(1))),
                dynamicTest("8 Delete ERRORE", ()->assertFalse(rubrica.deleteOne(10))));
    }
}
