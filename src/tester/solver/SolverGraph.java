package tester.solver;

import java.util.ArrayList;

import tester.problem.InterfaceProblem;
import tester.problem.ProblemGraph;
import tester.solution.Solution;
import tester.solution.SolutionGraph;

public abstract class SolverGraph extends Solver{
    
    @Override
    public ArrayList<Solution> getNeighbors(Solution s, InterfaceProblem p) {
        SolutionGraph sol = (SolutionGraph)s;
        ProblemGraph prob = (ProblemGraph)p;
        
        ArrayList<Solution> neighs = new ArrayList<Solution>();
        if(neighMode != NeighborMode.REDUCTIVE){
            for(int i = 0; i < prob.getNumOfCities(); i++){
                if(sol.contains(i)) continue;
                SolutionGraph neigh = new SolutionGraph();
                neigh.addAll(sol);
                neigh.add(i);
                neighs.add(neigh);
            }
        }
        if(neighMode != NeighborMode.ADDITIVE){
            for(int i = 0; i < sol.size(); i++){
                SolutionGraph neigh = new SolutionGraph();
                neigh.addAll(sol);
                neigh.remove(i);
                neighs.add(neigh);
            }
        }
        
        return neighs;
    }

}
