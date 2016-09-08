package main;

import java.io.File;
import java.util.Scanner;

public class Main {
    
    static File getFile(String path){
        try{
            if(path.endsWith("/")) path = path.substring(0, path.length() -1);
            System.out.print(String.format("Load file: %s/", path));
            Scanner keyboard = new Scanner(System.in);
            String str = keyboard.nextLine();
            keyboard.close();
            return new File(path + "/" + str);
        } catch (Exception e) {
            e.printStackTrace(System.err);
            throw e;
        }
    }
    
    enum probType {
        TSP
    }
    
    public static void main(String[] args) throws Exception {
        Test.test6();
    }
}
