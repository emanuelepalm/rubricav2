package utils;

import com.google.gson.Gson;
import models.Contatti;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;


public class FileHandler {
    private static String json = "";
    private static final String path = "C:\\Users\\lelej\\Desktop\\rubricav2\\src\\main\\resources\\";
    private static final String extension = ".json";

    public static void writeFile(String fileName, ArrayList<Contatti> contattiArrayList) {
        try {
            File file = new File(path+ fileName +extension);
            if (file.createNewFile()) {
                System.out.println("File created: " + file.getName());
                FileWriter myWriter = new FileWriter(path + fileName + extension);
                myWriter.write(new Gson().toJson(contattiArrayList));
                myWriter.flush();
                myWriter.close();
                System.out.println("Successfully wrote to the file.");
            }
            else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    public static ArrayList<Contatti> readFile(String fileName)  {
        ArrayList<Contatti> contatti = new ArrayList<Contatti>();
        try {
            FileReader fileReader =new FileReader(path + fileName + extension);
            int i;
            while ((i = fileReader.read()) != -1) {
                json += ((char) i);
            }
            fileReader.close();
            contatti.addAll(Arrays.asList(new Gson().fromJson(json, Contatti[].class)));
        }catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
        }catch (IOException e) {
            System.err.println(e.getMessage());
        }

        return contatti;
    }

}
