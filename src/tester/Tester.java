package tester;

import java.util.HashMap;

import tester.problem.Problem;

public abstract class Tester {
    
    abstract HashMap<String, String> testBattery(HashMap<String, String> opt);
    
    abstract HashMap<String, String> individualTest(Problem p);
    
    abstract String solveAndShowSolution(Problem p);
}
