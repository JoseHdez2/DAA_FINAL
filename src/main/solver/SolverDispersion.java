package main.solver;

import convenience.opthash.HashGraphGen;
import convenience.opthash.HashProbDef;
import convenience.opthash.HashProbGen;
import convenience.opthash.OptionsHash;
import main.problem.InterfaceProblem;
import main.problem.ProblemGraph;
import main.problem.ProblemDispersion;
import main.solution.Solution;
import main.solution.SolutionDispersion;
import main.solution.SolutionTSP;

public class SolverDispersion extends SolverGraph{
    
    public enum solverType {
        GREEDY,
        GREEDY_2,
        GRASP,
        MULTISTART,
        VNS,
    }
    
    public solverType myType;
    
    public SolverDispersion(SolverDispersion.solverType solverType){
        myType = solverType;
    }
    
    SolutionDispersion solveByGreedy(ProblemDispersion p) throws Exception{
        SolutionDispersion sol = new SolutionDispersion();
        // Agregamos las dos ciudades i,j con la mayor arista del grafo.
        Integer longestI = null;
        Integer longestJ = null;
        for(int i = 0; i < p.getNumOfCities(); i++){
            for(int j = 0; j < p.getNumOfCities(); j++){
                if(p.dist(i, j) == p.getLongestDistance()){
                    longestI = i;
                    longestJ = j;
                    break;
                }
            }
        }
        sol.add(longestI);
        sol.add(longestJ);
        
        boolean solutionImproved = false;
        do{
            solutionImproved = false;
            /* 
             * Escogemos el mejor de los vecinos aditivos de "sol", o lo que es lo mismo,
             * agregamos el nodo que mas optimiza la solucion.
             */
            SolutionDispersion sol2 = 
                    (SolutionDispersion) p.bestSolution(getNeighbors(sol, p, NeighborMode.ADDITIVE));
            
            if(sol2 == null) break;
            
            if(p.appraiseSolution(sol2) > p.appraiseSolution(sol)){
                sol = sol2;
                solutionImproved = true;
            }
        }while(solutionImproved);
        
        // Cuando acaba el bucle, la solución está completa.
        return sol;
    }
    
    SolutionTSP bestSolution = null;
    
    /*
    SolutionTSP solveByBruteForce(ProblemTSP p){
        SolutionTSP sol = new SolutionTSP();
        bestSolution = null;
        solveByBruteForce(p, sol);
        return bestSolution;
    }*/
    
    /*
    private void solveByBruteForce(ProblemTSP p, SolutionTSP sol){
        
        if(p.isCompleteSolution(sol)) {
            if(bestSolution == null || p.appraiseSolution(sol) < p.appraiseSolution(bestSolution))
                bestSolution = sol;
            return;
        }
        for(int c = 0; c < p.getNumOfCities(); c++){
            System.out.println(String.format("City %d...", c));
            if (sol.contains(c)){
                System.out.println(String.format("...is already in %s", sol));
                continue;
            }
            SolutionTSP newSol = new SolutionTSP();
            newSol.addAll(sol);
            newSol.add(c);
            solveByBruteForce(p,newSol);
        }
    }*/
    
    @Override
    public Solution solve(InterfaceProblem p, OptionsHash opt) throws Exception{
        switch(myType){
        case GREEDY: return solveByGreedy((new ProblemDispersion((ProblemGraph)p)));
        //case BRUTE_FORCE: return solveByBruteForce((new ProblemMMD((ProblemGraph)p)));
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
    public InterfaceProblem instantiateProblem(HashProbDef hashProbDef) throws Exception {
        return new ProblemDispersion(hashProbDef);
    }
}
