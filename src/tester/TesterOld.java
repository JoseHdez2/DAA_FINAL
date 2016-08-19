package tester;

import java.util.HashMap;

import convenience.E;
import convenience.OptionsHash;
import tester.problem.Problem;

public abstract class TesterOld {
    
    // Test options.
    static final String OPT_SIZE_BEG = "probSizeBeg";
    static final String OPT_SIZE_END = "probSizeEnd";
    
    // Test results.
    static final String DATA_TIME = "timeTaken";
    static final String DATA_SOL = "solution";
    
    /**
     * @param opt Options hash where all the test options are stored.
     */
    void testBattery(OptionsHash opt){
        if(opt.keySet().contains(E.times)){
            int times = Integer.getInteger(opt.get(E.times));
            for(int i = 0; i < times; i++){
                // Individual test
                long millisecondsTaken = 0;
                int sizeBeg = 1, sizeEnd = 1;
                if(opt.keySet().contains(E.probSizeBeg)){
                    sizeBeg = Integer.getInteger(opt.get(E.probSizeBeg));
                }
                if(opt.keySet().contains(E.probSizeEnd)){
                    sizeEnd = Integer.getInteger(opt.get(E.probSizeEnd));
                }
                for(int j = sizeBeg; j <= sizeEnd; j++){
                    test();
                }
            }
        }
    }
    
    /**
     * We execute an individual test case and measure the time it takes to solve.
     * Return the relevant data.
     */
    HashMap<String,String> individualTest(Problem p){
        long startTime = System.nanoTime();
        String sol = solveAndShowSolution(p);
        long endTime = System.nanoTime();
        long millisecondsTaken = (endTime - startTime);
        HashMap<String,String> results = new HashMap<String,String>();
        results.put(E.timeTaken, String.valueOf(millisecondsTaken));
        results.put(E.solution, sol);
        return results;
    }
    
    /**
     * @return A string representation of the solution.
     */
    abstract String solveAndShowSolution(Problem p);
    
    abstract void test();
    
    /**
     * @return The test results in Markdown format.
     */
    String markdown(){
        String str = "";
        String[] rows = {"a", "b", "c"};
        str = String.format("%s", rows.toString());
        return str;
    }
    
    
}
