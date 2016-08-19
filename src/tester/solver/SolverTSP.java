package tester.solver;

import java.awt.datatransfer.SystemFlavorMap;
import java.util.Arrays;

import tester.OptionsHash;
import tester.problem.Problem;
import tester.problem.ProblemTSP;
import tester.solution.Solution;
import tester.solution.SolutionTSP;

public class SolverTSP implements Solver{
    
    enum solverType {
        GREEDY,
        BRUTE_FORCE,
        DYNAMIC_PROGRAMMING,
    }
    
    public solverType myType;
    
    public SolverTSP(){
        myType = solverType.BRUTE_FORCE;
    }
    
    SolutionTSP solveByGreedy(ProblemTSP p){
        SolutionTSP sol = new SolutionTSP();
        sol.add(1); // Ciudad inicial por defecto.
        
        while(!p.isCompleteSolution(sol)){
            float closestCityDist = Float.POSITIVE_INFINITY;
            Integer closestCity = null;
            
            // Buscamos la ciudad más cercana a la última ciudad y la añadimos.
            for(Integer c = 0; c < p.getNumOfCities(); c++){
                // Nos saltamos la ciudad si ya está en la solución.
                if (sol.contains(c)) {
                    // System.out.println(String.format("%d already in solution", c));
                    continue;
                }
                if(p.getDistance(sol.getLatest(), c) < closestCityDist){
                    System.out.println(String.format("New best: %d (%3.2f)", c, p.getDistance(sol.getLatest(), c)));
                    closestCityDist = p.getDistance(sol.getLatest(), c);
                    closestCity = c;
                }
            }
            // System.out.println("---");
            sol.add(closestCity);
        }
        // Cuando acaba el bucle, la solución está completa.
        return sol;
    }
    
    SolutionTSP bestSolution = null;
    
    SolutionTSP solveByBruteForce(ProblemTSP p){
        SolutionTSP sol = new SolutionTSP();
        solveByBruteForce(p, sol);
        return bestSolution;
    }
    
    private void solveByBruteForce(ProblemTSP p, SolutionTSP sol){
        
        if(p.isCompleteSolution(sol)) {
            if(bestSolution == null || p.appraiseSolution(sol) < p.appraiseSolution(bestSolution))
                bestSolution = sol;
            return;
        }
        for(int c = 0; c < p.getNumOfCities(); c++){
            if (sol.contains(c)) continue;
            SolutionTSP newSol = new SolutionTSP();
            newSol.addAll(sol);
            newSol.add(c);
            solveByBruteForce(p,newSol);
        }
    }
    
    SolutionTSP solveByDynamicProgramming(ProblemTSP p){
        SolutionTSP sol = new SolutionTSP();
        
        //if() TODO continuara...
        
        return sol;
    }
    
    @Override
    public Solution solve(Problem p, OptionsHash opt) throws Exception{
        switch(myType){
        case GREEDY: return solveByGreedy((ProblemTSP)p);
        case BRUTE_FORCE: return solveByBruteForce((ProblemTSP)p);
        case DYNAMIC_PROGRAMMING: return solveByDynamicProgramming((ProblemTSP)p);
        default: throw new Exception(String.format("Unexpected solver algorithm type %s", myType));
        }
    }
}
