package core;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class FileHandler{
    private static String data;

    public static String readFile(File file){
        data = "";

        try{
            Scanner scanner = new Scanner(file);
            while(scanner.hasNextLine()){
                data += scanner.nextLine() + "\n";
            }
            scanner.close();

        }catch(FileNotFoundException e){
            System.out.println(e.getStackTrace());
        }

        return data;
    }

    public static void saveFile(File file, String text){

        try {
            FileWriter writer = new FileWriter(file);
            writer.write(text);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
