package tester.solver;

import java.util.ArrayList;

import convenience.opthash.OptionsHash;
import tester.problem.InterfaceProblem;
import tester.solution.Solution;

public abstract class Solver implements InterfaceSolver {

    NeighborMode neighMode;
    NeighType neighType;
    
    // TODO
    public abstract Solution solve(InterfaceProblem p, OptionsHash opt) throws Exception;
    
    enum ObjectiveFunction {
        MAXIMIZE,
        MINIMIZE
    }
    
    ObjectiveFunction objFunc;
    
    @Override
    public Solution bestNeighbor(Solution s, InterfaceProblem p) {
        return p.bestSolution(getNeighbors(s, p));
    }
    
    @Override
    public Solution solveByGreedy(InterfaceProblem p) {
        Solution s = initialFeasibleSolution(p);
        
        boolean solutionImproved = false;
        do {
            solutionImproved = false;
            Solution bestNeigh = bestNeighbor(s, p);
            switch(objFunc){
            case MAXIMIZE: if(p.appraiseSolution(bestNeigh) > p.appraiseSolution(s)) 
                s = bestNeigh; 
                solutionImproved = true;
                break;
            case MINIMIZE: if(p.appraiseSolution(bestNeigh) < p.appraiseSolution(s))
                s = bestNeigh; 
                solutionImproved = true;
                break;
            }
            
        } while(solutionImproved);
        
        return s;
    }
    
    @Override
    public Solution solveByBruteForce(InterfaceProblem p) throws Exception {
        ArrayList<Solution> branches = new ArrayList<Solution>();
        ArrayList<Solution> leafs = new ArrayList<Solution>(); 
        
        branches.add(initialSolution());
        while(!branches.isEmpty()){
            Solution expandee = branches.remove(0);
            if(p.isCompleteSolution(expandee)){
                leafs.add(expandee);
            } else {
                branches.addAll(getNeighbors(expandee, p));
                branches.add(expandee);
            }
        }
        
        return p.bestSolution(leafs);
    }
}
