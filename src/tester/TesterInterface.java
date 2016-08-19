package tester;

import java.util.HashMap;

import convenience.OptionsHash;
import tester.problem.Problem;

public interface TesterInterface {
    
    /**
     * Battery of tests with generated problems.
     * @param opt Options for testing and problem generation.
     * @return Results. TODO
     */
    public HashMap<String, String> testBattery(OptionsHash opt);
    
    /**
     * Same but, all tests are done on the same Problem.
     * @param opt Options for testing.
     * @return Results. TODO
     */
    public HashMap<String, String> testBattery(Problem p, OptionsHash opt);
    
    /**
     * Perform a single test on a single problem.
     * @param opt Options for testing.
     * @return Results. TODO
     */
    public HashMap<String, String> individualTest(Problem p, OptionsHash opt);
    
    public Problem generateProblem(OptionsHash opt) throws Exception;
}
