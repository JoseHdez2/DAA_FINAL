package tester;

import java.util.HashMap;

import input.TSP_Problem;
import typedef.Route;

public abstract class Tester {
    
    // Test options.
    static final String OPT_TIMES = "times"; // Times to test each configuration.
    static final String OPT_SIZE_BEG = "probSizeBeg";
    static final String OPT_SIZE_END = "probSizeEnd";
    
    // Test results.
    static final String DATA_TIME = "timeTaken";
    static final String DATA_SOL = "solution";
    
    /**
     * @param opt Options hash where all the test options are stored.
     */
    void testBattery(HashMap<String,String> opt){
        if(opt.keySet().contains(OPT_TIMES)){
            int times = Integer.getInteger(opt.get(OPT_TIMES));
            for(int i = 0; i < times; i++){
                // Individual test
                long millisecondsTaken = 0;
                int sizeBeg = 1, sizeEnd = 1;
                if(opt.keySet().contains(OPT_SIZE_BEG)){
                    sizeBeg = Integer.getInteger(opt.get(OPT_SIZE_BEG));
                }
                if(opt.keySet().contains(OPT_SIZE_BEG)){
                    sizeEnd = Integer.getInteger(opt.get(OPT_SIZE_END));
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
        results.put("", value)
        return String.format("");
    }
    
    /**
     * @return A string representation of the solution.
     */
    abstract String solveAndShowSolution(Problem p);
    
    abstract void test();
    
    String markdown(){
        String str = "";
        String[] rows = {"a", "b", "c"};
        str = String.format("%s", rows.toString());
        return str;
    }
    
    
}
