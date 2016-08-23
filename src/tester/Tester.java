package tester;

import java.util.ArrayList;

import convenience.E;
import convenience.OptionsHash;
import tester.problem.InterfaceProblem;
import tester.solver.Solver;

public class Tester implements InterfaceTester{

    ArrayList<String> order = new ArrayList<String>();
    ArrayList<OptionsHash> testResults = new ArrayList<OptionsHash>();
    Solver solver;
    
    public Tester(Solver solver){
        this.solver = solver;
    }
    
    // TODO temporal?
    public InterfaceProblem generateProblem(OptionsHash opt) throws Exception{
        return solver.generateProblem(opt);
    }
    
    @Override
    public OptionsHash testBattery(OptionsHash opt, ArrayList<String> order, int orderIndex) throws Exception {
        // TODO Auto-generated method stub
        if(orderIndex == order.size())
            individualTest(opt);
        String curOpt = order.get(orderIndex);
        int min = Integer.valueOf(opt.getIndispensable(E.min + E.capitalize(curOpt)));
        int max = Integer.valueOf(opt.getIndispensable(E.max + E.capitalize(curOpt)));
        for(int i = min; i < max; i++){
            opt.put(curOpt, String.valueOf(i));
            testBattery(opt, order, orderIndex++);
        }
        return null;
    }

    @Override
    public OptionsHash individualTest(OptionsHash opt) {
        return null;
    }

}
