package tester.solver;

import java.util.ArrayList;

import convenience.OptionsHash;
import tester.problem.InterfaceProblem;
import tester.problem.ProblemMMD;
import tester.problem.ProblemTSP;
import tester.solution.Solution;
import tester.solution.SolutionTSP;

public class SolverMMD extends Solver{
    
    enum solverType {
        GREEDY,
        BRUTE_FORCE,
    }
    
    public solverType myType;
    
    public SolverMMD(){
//        myType = solverType.GREEDY;
        myType = solverType.BRUTE_FORCE;
    }
    
    SolutionTSP solveByGreedy(ProblemMMD p) throws Exception{
        SolutionTSP sol = new SolutionTSP();
        // Agregamos las dos ciudades con la mayor arista del grafo.
        for(int i = 0; i < p.getNumOfCities(); i++){
            for(int j = 0; j < p.getNumOfCities(); j++){
                if(p.dist(i, j) == p.getLongestDistance()){
                    sol.add(i);
                    sol.add(j);
                    break;
                }
            }
        }
        // for
        // sol.add(p.getLongestDistance()); // Ciudad inicial por defecto.
        
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
                if(p.dist(sol.getLatest(), c) < closestCityDist){
                    System.out.println(String.format("New best: %d (%3.2f)", c, p.dist(sol.getLatest(), c)));
                    closestCityDist = p.dist(sol.getLatest(), c);
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
        bestSolution = null;
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
    
    @Override
    public Solution solve(InterfaceProblem p, OptionsHash opt) throws Exception{
        switch(myType){
        case GREEDY: return solveByGreedy((ProblemTSP)p);
        case BRUTE_FORCE: return solveByBruteForce((ProblemTSP)p);
        default: throw new Exception(String.format("Unexpected solver algorithm type %s", myType));
        }
    }

    @Override
    public Solution initialSolution() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Solution initialFeasibleSolution(InterfaceProblem p) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ArrayList<Solution> getNeighbors(Solution s, InterfaceProblem p) {
        // TODO Auto-generated method stub
        return null;
    }
}
