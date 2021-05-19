package utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import entities.Rubrica;
import models.Contatti;
import models.MapModel;
import models.Ruoli;


import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;


public class FileHandler {
    private static String json = "";
    private static final String path = "C:\\Users\\lelej\\Desktop\\rubricav2\\src\\main\\resources\\";
    private static final String extension = ".json";
    private static final String pathMap = path + "map\\";
    private static final String pathRubrica = path + "rubrica\\";

    public static void writeArrayInFile(String fileName, ArrayList<Contatti> contattiArrayList) {
        try {
            File file = new File(pathRubrica+ fileName +extension);
            if (file.createNewFile()) {
                System.out.println("File created: " + file.getName());
                FileWriter myWriter = new FileWriter(pathRubrica + fileName + extension);

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
    public static void writeMapInFile(String fileName, Map<Ruoli, Rubrica> rubricaMap) {
        try {
            File file = new File(pathMap+ fileName +extension);
            ArrayList<MapModel> mapList = new ArrayList<MapModel>();
            if (file.createNewFile()) {
                for (Map.Entry<Ruoli, Rubrica> entry : rubricaMap.entrySet()) {
                    mapList.add(new MapModel(entry.getKey(),entry.getValue()));
                }
                    System.out.println("File created: " + file.getName());
                    FileWriter myWriter = new FileWriter(pathMap + fileName + extension);
                    myWriter.write(new Gson().toJson(mapList));
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
    public static String readFileRubrica(String fileName)  {
        json ="";
        try {
            FileReader fileReader =new FileReader(pathRubrica + fileName + extension);
            int i;
            while ((i = fileReader.read()) != -1) {
                if(i != -1)
                json += ((char) i);
            }
            fileReader.close();
        }catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
        }catch (IOException e) {
            System.err.println(e.getMessage());
        }
        return json;
    }

    public static String readFileMap(String fileName)  {
        json ="";
        try {
            FileReader fileReader =new FileReader(pathMap + fileName + extension);
            int i;
            while ((i = fileReader.read()) != -1) {
                if(i != -1)
                    json += ((char) i);
            }
            fileReader.close();
        }catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
        }catch (IOException e) {
            System.err.println(e.getMessage());
        }
        return json;
    }

    public static void deleteFile(String fileName) {
        if(!fileName.endsWith("\\")) {
            File file = new File(pathMap + fileName + extension);
            if (file.delete()) {
                System.out.println("File Cancellato: " + file.getName());
            } else {
                System.out.println("Impossibile eliminare il file " + fileName);
            }
        } else {
            File folder = new File(fileName);
            if(folder.delete()) {
                System.out.println("Cartella Cancellata: " + folder.getName());
            }
            else {
                System.out.println("Impossibile eliminare la cartella, svuotala e riprova");
            }
        }
    }

    public static void deleteFile(boolean all) {
        for (File file : new File(pathMap).listFiles()) {
            if (file.delete()) {
                System.out.println("File Cancellato: " + file.getName());
            } else {
                System.out.println("Impossibile eliminare il file ");
            }
        }
    }
  public static void deleteFile(boolean all, String extension) {
      for (File file : new File(pathMap).listFiles()) {
          if (file.getName().endsWith(extension)) {
              if (file.delete()) {
                  System.out.println("File Cancellato: " + file.getName());
              } else {
                  System.out.println("Impossibile eliminare il file ");
              }
          }
      }
  } /*
  public  void deleteFile(boolean all, String extension) {
      for (File file : new File(pathMap).listFiles()) {
          FilenameFilter filenameFilter = new LogFilterFilter(extension);
          if (int i = 0){
              if (file.delete()) {
                  System.out.println("File Cancellato: " + file.getName());
              } else {
                  System.out.println("Impossibile eliminare il file ");
              }
          }
      }

}
    class LogFilterFilter implements FilenameFilter
    {
        private String extension;
        public LogFilterFilter(String extension) {
            this.extension = extension;
        }
        @Override
        public boolean accept(File dir, String fileName)
        {
            return (fileName.endsWith(extension));
        }
    }*/
 }