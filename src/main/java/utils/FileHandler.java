package utils;

import com.google.gson.Gson;
import entities.Rubrica;
import models.Contatti;
import models.MapModel;
import models.Ruoli;

import java.io.*;
import java.util.ArrayList;
import java.util.Map;
import java.util.Properties;

import static utils.GlobalProperties.*;


public class FileHandler {
    private static String json = "";

    public static String populate(){
        json ="";
        try {
            FileReader fileReader =new FileReader(BASEPATH + "pop" + EXTENSION_JS);
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

    public static void writeArrayInFile(String fileName, ArrayList<Contatti> contattiArrayList) {
        try {
            File file = new File(PATHRUBRICA+ fileName +EXTENSION_JS);
            if (file.createNewFile()) {
                System.out.println("File created: " + file.getName());
                FileWriter myWriter = new FileWriter(PATHRUBRICA + fileName + EXTENSION_JS);

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
            File file = new File(PATHMAP + fileName + EXTENSION_JS);
            ArrayList<MapModel> mapList = new ArrayList<MapModel>();
            if (file.createNewFile()) {
                for (Map.Entry<Ruoli, Rubrica> entry : rubricaMap.entrySet()) {
                    mapList.add(new MapModel(entry.getKey(),entry.getValue()));
                }
                    System.out.println("File created: " + file.getName());
                    FileWriter myWriter = new FileWriter(PATHMAP + fileName + EXTENSION_JS);
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
            FileReader fileReader =new FileReader(PATHRUBRICA + fileName + EXTENSION_JS);
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
            FileReader fileReader =new FileReader(PATHMAP + fileName + EXTENSION_JS);
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
            File file = new File(PATHMAP + fileName + EXTENSION_JS);
            if (file.delete()) {
                System.out.println("File Cancellato: " + file.getName());
            } else {
                System.out.println("Impossibile eliminare il file " + fileName);
            }
        } else {
            File folder = new File(BASEPATH + fileName);
            if(folder.delete()) {
                System.out.println("Cartella Cancellata: " + folder.getName());
            }
            else {
                System.out.println("Cartella piena. Vuoi svuotarne il contenuto e eliminarla?\n0)NO\n1)SI");
                if(InputHandler.nextInt() == 1) {
                  emptyDir(fileName);
                  deleteFile(fileName);
                }
            }
        }
    }

    public static void emptyDir(String dirName) {
        for (File file : new File(BASEPATH + dirName).listFiles()) {
            if (file.delete()) {
                System.out.println("File Cancellato: " + file.getName());
            } else {
                System.out.println("Impossibile eliminare il file ");
            }
        }
    }

    public static void deleteFile(boolean all) {
        for (File file : new File(PATHMAP).listFiles()) {
            if (file.delete()) {
                System.out.println("File Cancellato: " + file.getName());
            } else {
                System.out.println("Impossibile eliminare il file ");
            }
        }
    }

  /*
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
  }
  */

  public  void deleteFile(boolean all, String extension) {
      for (File file : new File(PATHMAP).listFiles()) {
          FilenameFilter filenameFilter = new ExtensionFilter(extension);
          if (filenameFilter.accept(new File(PATHMAP),file.getName())){
              if (file.delete()) {
                  System.out.println("File Cancellato: " + file.getName());
              }
          }
      }
    }

    public static boolean newDir(String dirPath) {
      if(!new File(BASEPATH+dirPath).mkdir()) {
          return false;
      } else {
          return true;
      }
    }
    public static Properties loadProp() {
        Properties props = new Properties();
        try {
            props.load(new FileInputStream(PATHPROPERTIES + "settings" + EXTENSION_PROP));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return props;
    }

    public static void writeProperty(String properties) {
        try {
            File file = new File(PATHPROPERTIES+ "settings" + EXTENSION_PROP);
            if (file.createNewFile()) {
                System.out.println("File created: " + file.getName());
                FileWriter myWriter = new FileWriter(PATHPROPERTIES + "settings" + EXTENSION_PROP);

                myWriter.write(properties);
                myWriter.flush();
                myWriter.close();
                System.out.println("Successfully wrote to the file.");
            }
            else {
                System.out.println("File already exists.");
                FileWriter myWriter = new FileWriter(PATHPROPERTIES + "settings" + EXTENSION_PROP);

                myWriter.write(properties);
                myWriter.flush();
                myWriter.close();
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}