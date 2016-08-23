package tester;

import java.util.ArrayList;

import convenience.OptionsHash;

public interface InterfaceTester {
    
    /**
     * Battery of tests with generated problems.
     * @param opt Options for testing and problem generation.
     * @return Results. TODO
     * @throws Exception 
     * @throws NumberFormatException 
     */
    public OptionsHash testBattery(OptionsHash opt, ArrayList<String> order, int orderIndex) throws NumberFormatException, Exception;
    
    /**
     * Same but, all tests are done on the same Problem.
     * @param opt Options for testing.
     * @return Results. TODO
     */
    // public HashMap<String, String> testBattery(InterfaceProblem p, OptionsHash opt);
    
    /**
     * Perform a single test on a single problem.
     * @param opt Options for testing.
     * @return Results. TODO
     * @throws Exception 
     */
    // public OptionsHash individualTest(InterfaceProblem p, OptionsHash opt);
    
    public OptionsHash individualTest(OptionsHash opt) throws Exception;
}
