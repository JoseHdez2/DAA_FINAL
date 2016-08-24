package tester;

import java.util.ArrayList;

import convenience.E;
import convenience.MyTimer;
import convenience.opthash.OptionsHash;
import tester.problem.InterfaceProblem;
import tester.solution.Solution;
import tester.solver.Solver;

public class Tester implements InterfaceTester{

    MyTimer timer = new MyTimer();
    ArrayList<String> order = new ArrayList<String>();
    ArrayList<OptionsHash> testResults = new ArrayList<OptionsHash>();
    // TODO Should this be public?
    public Solver solver;
    
    public Tester(Solver solver){
        this.solver = solver;
    }
    
    @Override
    public ArrayList<OptionsHash> testBattery(OptionsHash opt, ArrayList<String> order, int orderIndex) throws Exception {
        // TODO Auto-generated method stub
        if(orderIndex == order.size()){
            testResults.add(individualTest(opt));
            return null; // testResults;
        }
        System.out.println(String.format("orderIndex= %d", orderIndex));
        String curOpt = order.get(orderIndex);
        int min = Integer.valueOf(opt.getIndispensable(E.min + E.capitalize(curOpt)));
        int max = Integer.valueOf(opt.getIndispensable(E.max + E.capitalize(curOpt)));
        for(int i = min; i <= max; i++){
            opt.put(curOpt, String.valueOf(i));
            testBattery(opt, order, orderIndex+1);
        }
        
        printResultsAsMarkdown(order);
        System.out.println(String.format("Pepperoni: %s", order));
        System.out.println(String.format("Pizza: %s", testResults));

        return null; // testResults;
    }

    @Override
    public OptionsHash individualTest(OptionsHash opt) throws Exception {
        System.out.println(String.format("testing: %s", opt));
        
        InterfaceProblem p = solver.generateProblem(opt);
        System.out.println(p);
       
        timer.start();
        Solution sol = solver.solve(p, opt);
        timer.stop();
        OptionsHash resultHash = new OptionsHash();
        resultHash.putAll(opt);
        resultHash.put(E.timeTaken, String.valueOf(timer.getTimeCount()));
        resultHash.put(E.solution, sol.toString());
        resultHash.put(E.solutionValue, String.valueOf(p.appraiseSolution(sol)));
        
        // Output to markdown inner variable
        
        // System.out.println(resultHash);
        // testResults.add(resultHash);
        
        return resultHash;
    }
    
    
    public String getResultsAsMarkdown(ArrayList<String> order){
        ArrayList<String> order2 = new ArrayList<String>();
        order2.addAll(order);
        order2.add(E.timeTaken);
        order2.add(E.solutionValue);
        order2.add(E.solution);
        
        String md = String.format("%s%n", "## Test Results");
        String line = "";
        for(String str : order2){
            md += String.format("%s |", str);
        }
        md += String.format("%s%n---%n", line);
        
        for(OptionsHash oh : testResults){
            line = "";
            for(String str : order2){
                String format = "%s |";
                //if(str == E.solution || str == E.filename) format = "%s";
                //else format = "%f";
                line += String.format(format, oh.get(str));
            }
            md += String.format("%s%n", line);
        }
        return md;
    }
    
    public void printResultsAsMarkdown(ArrayList<String> order){
        System.out.print(getResultsAsMarkdown(order));
    }

}
