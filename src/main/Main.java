package main;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import javax.print.StreamPrintServiceFactory;
import javax.security.auth.login.AccountLockedException;

import reader.TSPProblemFromXML;
import tester.E;
import tester.OptionsHash;
import tester.TesterTSP;
import tester.problem.ProblemTSP;
import tester.solution.SolutionTSP;
import tester.solver.SolverTSP;

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
    
    /**
     * @param probType  Type of problem to generate.
     * @param req   Required options.
     * @param opt   Optional options.
     * @return
     */
    /*
    static Problem generateProblem(probType probType, OptionsHash req, OptionsHash opt){
        
        switch(probType){
        case TSP:
            return (ProblemTSP);
        }
    }*/
    
    public static void main(String[] args) throws Exception {
        OptionsHash probOptsGen = new OptionsHash();
        probOptsGen.put("m", "5");
        probOptsGen.put("numOfCities", "4");
        probOptsGen.put("minDist", "1");
        probOptsGen.put("maxDist", "9");
        System.out.println(probOptsGen);
        TesterTSP tt = new TesterTSP();
        ProblemTSP probGen = (ProblemTSP) tt.generateProblem(probOptsGen);
        System.out.println(probGen);
        
        /*
        OptionsHash probOptsXml = TSPProblemFromXML.OptFromXML(new File("res/samples/gr17.xml"));
        System.out.println(probOptsXml);
        probOptsXml.put(E.m, String.valueOf(Integer.valueOf(probOptsXml.getIndispensable(E.numOfCities)) / 3));
        ProblemTSP probXml = new ProblemTSP(probOptsXml);
        System.out.println(probXml);
        */
        
        SolverTSP solver = new SolverTSP();
        SolutionTSP sol = (SolutionTSP)solver.solve(probGen, new OptionsHash());
        System.out.println(String.format("Sol : %s", sol));
        System.out.println(String.format("Sol : %3.2f", probGen.appraiseSolution(sol)));
    }
}
