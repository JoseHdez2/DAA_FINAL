package tester.problem;

import convenience.OptionsHash;
import tester.solution.Solution;

public interface Problem {
    
    /**
     * @param opt
     * @return Generated problem from the options specified.
     */
    Problem generateProblem(OptionsHash opt);
    
    /**
     * @param s A solution to this problem, that may be incomplete or infeasible.
     * @return The value of the given solution, regardless of completeness or feasibility.
     */
    float appraiseSolution(Solution s);
    
    /**
     * Used for knowing when to stop the building of a solution.
     * @param s Partial or complete solution.
     * @return Whether the given solution is complete and feasible (not necessarily optimal).
     */
    boolean isCompleteSolution(Solution s);
    
    /**
     * Used in the calculation of heuristics.
     * If a complete solution is given, the potential is zero by definition.
     * @param s Partial (or complete) solution.
     * @return The potential worth of a solution given the problem space.
     */
    float getSolutionPotential(Solution s);
}
