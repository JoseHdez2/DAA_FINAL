package tester;

import java.util.HashMap;

import convenience.OptionsHash;
import tester.problem.InterfaceProblem;

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
    public HashMap<String, String> testBattery(InterfaceProblem p, OptionsHash opt);
    
    /**
     * Perform a single test on a single problem.
     * @param opt Options for testing.
     * @return Results. TODO
     */
    public HashMap<String, String> individualTest(InterfaceProblem p, OptionsHash opt);
    
    /**
     * 
     * @param opt
     * @return Problem instance generated within the provided parameters.
     * @throws Exception If an argument indispensable for generation is missing.
     */
    public InterfaceProblem generateProblem(OptionsHash opt) throws Exception;
}
