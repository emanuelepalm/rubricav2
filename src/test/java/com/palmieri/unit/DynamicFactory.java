package com.palmieri.unit;

import com.palmieri.entities.Rubrica;
import com.palmieri.models.Contatti;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;
import com.palmieri.utils.FileHandler;
import org.junit.jupiter.api.TestInstance;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;
@TestInstance(TestInstance.Lifecycle.PER_CLASS)

public class DynamicFactory {

    @TestFactory
    Collection<DynamicTest> dinamicTestsFile() {
        String path = "C:\\Users\\lelej\\Desktop\\testProj\\rubricav2\\src\\test\\java\\com\\palmieri\\logs";
        String fileName = "mario.txt";
        String body = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Morbi dapibus, lacus non pretium ornare, nulla nunc accumsan leo, vitae luctus tellus felis vitae velit. Aliquam eget metus porta, fringilla lectus in, pulvinar sapien. Fusce vitae lobortis risus. In hac habitasse platea dictumst. Sed efficitur ultricies magna, at vehicula urna mattis ac. Quisque eget dictum elit, non tempor odio. Morbi ultricies finibus sagittis. Cras viverra egestas bibendum. Nulla orci massa, ultrices et dapibus ac, tristique lobortis quam. Maecenas et orci rutrum, mattis lectus sit amet, commodo dolor. Nulla ac suscipit velit, quis ullamcorper leo. In mattis risus velit, sed imperdiet metus pharetra quis. Suspendisse rhoncus, neque gravida fermentum mattis, turpis justo blandit ex, quis consectetur ex massa id tortor. Praesent nec neque lectus. Vivamus non viverra justo.\n" +
                "\n" +
                "Duis commodo ipsum eros, eu posuere arcu pretium in. Etiam vitae risus eu massa euismod pretium ac et lacus. Vivamus lobortis ex in neque vehicula tempor id ac sem. Nullam sed tellus consectetur, convallis nisl nec, hendrerit ex. Praesent vitae eros fringilla, rhoncus sem ut, placerat diam. Sed nec mauris ac mi luctus placerat vitae in massa. Donec pulvinar tincidunt risus, ac fringilla nisl mollis at. Interdum et malesuada fames ac ante ipsum primis in faucibus. Integer sagittis faucibus eros porta sodales. Vestibulum quis rutrum metus. Quisque eros dui, porta vel condimentum at, volutpat sed mi. Praesent at felis nibh.\n" +
                "\n" +
                "Duis at imperdiet dolor, quis tempus quam. Proin volutpat nisi vel nulla sagittis, a auctor lorem finibus. Aenean egestas orci vitae lectus dapibus, id rhoncus dui fermentum. Nam rutrum urna id pellentesque dignissim. Aenean faucibus iaculis nunc, ac convallis urna imperdiet nec. Morbi posuere ex vitae mi tempor vehicula. Praesent consequat non dui vitae pretium. Etiam sagittis iaculis erat vitae venenatis. Vivamus mollis consectetur est a porta. Praesent sollicitudin id ex id maximus. Proin tellus metus, aliquam eget nisl non, volutpat eleifend sapien. Etiam feugiat nibh at nisl finibus varius. Interdum et malesuada fames ac ante ipsum primis in faucibus. Curabitur turpis risus, scelerisque vitae velit id, aliquet aliquet lorem. Donec lacinia dui in elit scelerisque, nec iaculis odio vulputate.";

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
                    rubrica.addOne(pippo);
                    assertTrue(rubrica.getContattiList().contains(pippo));
                }),
                dynamicTest("2 search un result", ()->{
                    mockResult.add(pippo);
                    assertEquals(mockResult, rubrica.searchByName("Pippo"));
                }),
                dynamicTest("3 add un altro contatto",()-> {
                            rubrica.addOne(pippo2);
                            assertTrue(rubrica.getContattiList().contains(pippo2));
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
