package tester.solver;

import convenience.opthash.HashProbDef;
import convenience.opthash.HashProbGen;
import convenience.opthash.OptionsHash;
import tester.problem.InterfaceProblem;
import tester.problem.ProblemTSP;
import tester.solution.Solution;
import tester.solution.SolutionTSP;

public class SolverTSP extends SolverGraph{
    
    enum solverType {
        GREEDY,
        BRUTE_FORCE,
        DYNAMIC_PROGRAMMING,
    }
    
    public solverType myType;
    
    public SolverTSP(){
//        myType = solverType.GREEDY;
        myType = solverType.BRUTE_FORCE;
    }
    
    SolutionTSP solveByGreedy(ProblemTSP p){
        SolutionTSP sol = new SolutionTSP();
        sol.add(1); // "1" es la ciudad inicial por defecto, no perdemos generalidad.
        
        while(!p.isCompleteSolution(sol)){
            float closestCityDist = Float.POSITIVE_INFINITY;
            Integer closestCity = null;
            
            // Buscamos la ciudad más cercana a la última ciudad y la añadimos.
            for(Integer c = 0; c < p.getNumOfCities(); c++){
                // Nos saltamos la ciudad si ya está en la solución.
                if (sol.contains(c)) continue;
                
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
    
    SolutionTSP solveByDynamicProgramming(ProblemTSP p){
        SolutionTSP sol = new SolutionTSP();
        
        //if() TODO continuara...
        
        return sol;
    }
    
    @Override
    public Solution solve(InterfaceProblem p, OptionsHash opt) throws Exception{
        switch(myType){
        case GREEDY: return solveByGreedy((ProblemTSP)p);
        case BRUTE_FORCE: return solveByBruteForce((ProblemTSP)p);
        case DYNAMIC_PROGRAMMING: return solveByDynamicProgramming((ProblemTSP)p);
        default: throw new Exception(String.format("Unexpected solver algorithm type %s", myType));
        }
    }


    @Override
    public Solution initialSolution() {
        return new SolutionTSP();
    }

    @Override
    public Solution initialFeasibleSolution(InterfaceProblem prob) {
        SolutionTSP s = new SolutionTSP();
        ProblemTSP p = (ProblemTSP)prob;
        for (int i = 0; i < p.getNumOfCities(); i++){
            s.add(i);
        }
        return s;
    }
    
    public HashProbDef generateProblemDefinition(HashProbGen hashProbGen) throws Exception {
        return super.generateProblemDefinition(hashProbGen);
    }

    @Override
    public InterfaceProblem instantiateProblem(HashProbDef hashProbDef) throws Exception {
        return new ProblemTSP(hashProbDef);
    }

}
