package tester;

import java.util.ArrayList;

import convenience.E;
import convenience.MyTimer;
import convenience.OptionsHash;
import tester.problem.InterfaceProblem;
import tester.solution.Solution;
import tester.solver.Solver;

public class Tester implements InterfaceTester{

    ArrayList<String> order = new ArrayList<String>();
    ArrayList<OptionsHash> testResults = new ArrayList<OptionsHash>();
    // Should this be public?
    public Solver solver;
    
    public Tester(Solver solver){
        this.solver = solver;
    }
    
    @Override
    public OptionsHash testBattery(OptionsHash opt, ArrayList<String> order, int orderIndex) throws Exception {
        // TODO Auto-generated method stub
        if(orderIndex == order.size()){
            individualTest(opt);
            return null;
        }
        System.out.println(orderIndex);
        String curOpt = order.get(orderIndex);
        int min = Integer.valueOf(opt.getIndispensable(E.min + E.capitalize(curOpt)));
        int max = Integer.valueOf(opt.getIndispensable(E.max + E.capitalize(curOpt)));
        for(int i = min; i < max; i++){
            opt.put(curOpt, String.valueOf(i));
            testBattery(opt, order, orderIndex+1);
        }
        return null;
    }

    @Override
    public OptionsHash individualTest(OptionsHash opt) throws Exception {
        System.out.println(String.format("testing: %s", opt));
        /*
        InterfaceProblem p = solver.generateProblem(opt);
        MyTimer t = new MyTimer();
        t.start();
        Solution sol = solver.solve(p, opt);
        t.stop();
        OptionsHash results = new OptionsHash();
        results.put(E.timeTaken, String.valueOf(t.getTimeCount()));
        results.put(E.solution, sol.toString());
        results.put(E.solutionValue, String.valueOf(p.appraiseSolution(sol)));
        */      
        return opt;
    }

}
