package tester;

import java.util.HashMap;

import tester.problem.Problem;

public abstract class Tester {
    
    /**
     * Battery of tests with generated problems.
     * @param opt Options for testing and problem generation.
     * @return Results. TODO
     */
    abstract public HashMap<String, String> testBattery(OptionsHash opt);
    
    /**
     * Same but, all tests are done on the same Problem.
     * @param opt Options for testing.
     * @return Results. TODO
     */
    abstract public HashMap<String, String> testBattery(Problem p, OptionsHash opt);
    
    /**
     * Perform a single test on a single problem.
     * @param opt Options for testing.
     * @return Results. TODO
     */
    abstract public HashMap<String, String> individualTest(Problem p, OptionsHash opt);
    
    abstract public Problem generateProblem(OptionsHash opt) throws Exception;
}
