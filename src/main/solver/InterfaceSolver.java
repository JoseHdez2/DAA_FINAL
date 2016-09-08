package main.solver;

import java.util.ArrayList;

import convenience.opthash.HashProbDef;
import convenience.opthash.HashProbGen;
import convenience.opthash.OptionsHash;
import main.problem.InterfaceProblem;
import main.solution.Solution;

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
    
    /**
     * Devolver los vecinos en el espacio de soluciones.
     * @param s La solucion cuyos vecinos se devolveran.
     * @param p El problema que especificara el espacio de soluciones.
     * @param mode Modo que indica el tipo de vecinos que devolver.
     * @return
     */
    ArrayList<Solution> getNeighbors(Solution s, InterfaceProblem p, NeighborMode mode);
    
    Solution bestNeighbor(Solution s, InterfaceProblem p, NeighborMode mode);
    
    Solution solveByGreedy(InterfaceProblem p);
    
    Solution solveByBruteForce(InterfaceProblem p) throws Exception;
    
    /**
     * 
     * @param opt
     * @return Problem description instance generated within the provided parameters.
     * @throws Exception If an argument indispensable for generation is missing.
     */
    public HashProbDef generateProblemDefinition(HashProbGen hashProbGen) throws Exception;
    
    /**
     * 
     * @param opt
     * @return Problem instance generated within the provided parameters.
     * @throws Exception If an argument indispensable for generation is missing.
     */
    public InterfaceProblem generateProblem(HashProbGen hashProbGen) throws Exception;
    
    /**
     * 
     */
    public InterfaceProblem instantiateProblem(HashProbDef hashProbDef) throws Exception;
}
