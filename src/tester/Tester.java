package tester;

import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;

import convenience.E;
import convenience.Graph;
import convenience.MyTimer;
import convenience.opthash.HashProbDef;
import convenience.opthash.HashProbGen;
import convenience.opthash.HashTestRes;
import convenience.opthash.OptionsHash;
import tester.problem.InterfaceProblem;
import tester.solution.Solution;
import tester.solver.Solver;

public class Tester implements InterfaceTester{

    MyTimer timer = new MyTimer();
    ArrayList<String> order = new ArrayList<String>();
    ArrayList<HashTestRes> testResults = new ArrayList<HashTestRes>();
    // TODO Should this be public?
    public Solver solver;
    
    public Tester(Solver solver){
        this.solver = solver;
    }
    
    public void testBatteryAndPrint(HashProbGen opt, ArrayList<String> order, String filename) throws Exception {
        testBattery(opt, order, 0);
        printResultsAsMarkdown(order, filename);
    }
    
    
    @Override
    public ArrayList<HashTestRes> testBattery(HashProbGen opt, ArrayList<String> order, int orderIndex) throws Exception {
        
        if(orderIndex == order.size()){
            testResults.add(individualTest(opt));
            return null; // testResults;
        }

        String curOpt = order.get(orderIndex);
        int min = Integer.valueOf(opt.getIndispensable(E.min(curOpt)));
        int max = Integer.valueOf(opt.getIndispensable(E.max(curOpt)));
        for(int i = min; i <= max; i++){
            opt.put(curOpt, String.valueOf(i));
            testBattery(opt, order, orderIndex+1);
        }

        return testResults;
    }

    ArrayList<HashProbDef> pastProblems = new ArrayList<HashProbDef>();
    // TODO HACK remember to clean this list on each call to batteryTest. 
    
    public String getPastProblemsAsStr() throws Exception{
        String str = "";
        int i = 0;
        for(OptionsHash probDef : pastProblems)
            str += String.format("Problema %d:%n%s%n", i++, new Graph(probDef));
        return str;
    }
    
    @Override
    public HashTestRes individualTest(HashProbGen opt) throws Exception {
        // System.out.println(String.format("testing: %s", opt));
        
        HashProbDef problemDefinition = solver.generateProblemDefinition(opt);
        if(!pastProblems.contains(problemDefinition)) pastProblems.add(problemDefinition);
        
        InterfaceProblem p = solver.instantiateProblem(problemDefinition);
        
        timer.start();
        Solution sol = solver.solve(p, opt);
        timer.stop();
        HashTestRes resultHash = new HashTestRes();
        resultHash.putAll(opt);
        resultHash.put(E.timeTaken, String.format("%2.4f s", timer.getTimeCountAsSeconds()));
        resultHash.put(E.solution, sol.toString());
        resultHash.put(E.solutionValue, String.valueOf(p.appraiseSolution(sol)));
        
        return resultHash;
    }
    
    
    public String getResultsAsMarkdown(ArrayList<String> order){
        ArrayList<String> order2 = new ArrayList<String>();
        order2.addAll(order);
        order2.add(E.timeTaken);
        order2.add(E.solutionValue);
        order2.add(E.solution);
        
        String md = String.format("%s%n%n", "## Test Results");
        String line = "";
        for(String str : order2){
            md += String.format("%s |", str);
        }
        md += String.format("%n");
        for(String str : order2){
            md += String.format("---|", line);
        }

        md += String.format("%n");
        for(OptionsHash oh : testResults){
            line = "";
            for(String str : order2){
                String format = "%s |";
                // if(str == E.timeTaken) format = "%2.3f";
                // if(str == E.solution || str == E.filename) format = "%s";
                // else format = "%f";
                line += String.format(format, oh.get(str));
            }
            md += String.format("%s%n", line);
        }
        return md;
    }
    
    public void printResultsAsMarkdown(ArrayList<String> order, String filename) throws Exception{
        String md = "";
        md += getPastProblemsAsStr();
        md += getResultsAsMarkdown(order);
        System.out.print(md);
        ArrayList<String> md2 = new ArrayList<String>(Arrays.asList(md.split("\n")));
        Path file = Paths.get(filename);
        Files.write(file, md2, Charset.forName("UTF-8"));
        System.out.println(String.format("Written into file '%s'", filename));
    }

}
