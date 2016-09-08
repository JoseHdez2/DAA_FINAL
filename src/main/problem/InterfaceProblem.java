package main.problem;

import java.util.ArrayList;

import main.solution.Solution;

public interface InterfaceProblem {
    
    enum ObjectiveFunction {
        MAXIMIZE,
        MINIMIZE
    }
    
    /**
     * Used for knowing when to stop the building of a solution.
     * @param s Partial or complete solution.
     * @return Whether the given solution is complete and feasible (not necessarily optimal).
     * @throws Exception If somehow the solution became "overcomplete" (and would construct forever).
     */
    boolean isCompleteSolution(Solution s) throws Exception;
    
    /**
     * @param s A solution to this problem, that may be incomplete or infeasible.
     * @return The value of the given solution, regardless of completeness or feasibility.
     */
    float appraiseSolution(Solution s);
    
    /**
     * @param s Solutions to this problem, that may be incomplete or infeasible.
     * @return The best solution. Return null if unfeasible.
     */
    Solution bestSolution(ArrayList<Solution> solutions);
    
    /**
     * Used in the calculation of heuristics.
     * If a complete solution is given, the potential is zero by definition.
     * @param s Partial (or complete) solution.
     * @return The potential worth of a solution given the problem space.
     */
    float getSolutionPotential(Solution s);
    
    
    /**
     * Force implementing classes to have an "objective function" defined.
     * @return "Objective function" of this problem.
     */
    ObjectiveFunction objFunc();
}
