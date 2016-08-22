package tester.problem;

import java.util.ArrayList;

import tester.solution.Solution;

public abstract class Problem implements InterfaceProblem{
    
    @Override
    public Solution bestSolution(ArrayList<Solution> solutions) {
        // TODO if(s.isEmpty())
        Solution bestNeigh = null;
        Float bestVal = null;
        for (Solution neigh : solutions){
            if (bestNeigh == null){
                bestNeigh = neigh;
                bestVal = appraiseSolution(neigh);
                continue;
            }
            
            switch(objFunc()){
            case MAXIMIZE:
                if(appraiseSolution(neigh) > bestVal){
                    bestNeigh = neigh;
                    bestVal = appraiseSolution(neigh);
                }
                break;
            case MINIMIZE:
                if(appraiseSolution(neigh) < bestVal){
                    bestNeigh = neigh;
                    bestVal = appraiseSolution(neigh);
                }
                break;
            }
        }
        return bestNeigh;
    }
}
