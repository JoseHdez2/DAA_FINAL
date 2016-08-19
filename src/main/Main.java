package main;

import java.io.File;
import java.util.Scanner;

import reader.TSPProblemFromXML;
import tester.OptionsHash;
import tester.TesterTSP;
import tester.problem.ProblemTSP;

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
    
    public static void main(String[] args) throws Exception {
        OptionsHash oh = new OptionsHash();
        oh.put("m", "5");
        oh.put("numOfCities", "4");
        oh.put("minDist", "1");
        oh.put("maxDist", "9");
        System.out.println(oh);
        TesterTSP tt = new TesterTSP();
        ProblemTSP p = (ProblemTSP) tt.generateProblem(oh);
        System.out.println(p);
        
        System.out.println(TSPProblemFromXML.OptFromXML(getFile("res/samples/")));
    }
}
