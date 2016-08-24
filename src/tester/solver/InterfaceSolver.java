package tester.solver;

import java.util.ArrayList;

import convenience.opthash.OptionsHash;
import tester.problem.InterfaceProblem;
import tester.solution.Solution;

public interface InterfaceSolver {

    /**
     * @param p     Problem to solve.
     * @param opt   Options to use when solving.
     * @return      Solution to problem.
     * @throws Exception
     */
    Solution solve(InterfaceProblem p, OptionsHash opt) throws Exception;
    
    /**
     * @return Initial solution, feasible or not.
     */
    Solution initialSolution();
    
    /**
     * @return Initial feasible solution.
     */
    Solution initialFeasibleSolution(InterfaceProblem p);
    
    enum NeighborMode {
        /** Only consider neighbors that add an element to the solution. */
        ADDITIVE,
        /** Only consider neighbors that remove an element from the solution. */
        REDUCTIVE,
        /** Both additive and reductive neighbors will be considered.  */
        ALL
    }
    
    enum NeighType {
        /** Only consider feasible solutions/neighbors. */
        FEASIBLE,
        /** Both feasible and unfeasible neighbors will be considered. */
        ALL
    }
    
    ArrayList<Solution> getNeighbors(Solution s, InterfaceProblem p);
    
    Solution bestNeighbor(Solution s, InterfaceProblem p);
    
    Solution solveByGreedy(InterfaceProblem p);
    
    Solution solveByBruteForce(InterfaceProblem p) throws Exception;
    
    /**
     * 
     * @param opt
     * @return Problem instance generated within the provided parameters.
     * @throws Exception If an argument indispensable for generation is missing.
     */
    public InterfaceProblem generateProblem(OptionsHash opt) throws Exception;
}
