package tester.solver;

import convenience.E;
import convenience.Rand;
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

    enum GenType {
        UNIFORM_DISTRIBUTION,   // Evenly random distances between minDist and maxDist.
        BIASED                  // A single path with minDist distances, rest with maxDist (good for debugging).
    }
    
    GenType genType = GenType.BIASED;
    
    @Override
    public InterfaceProblem generateProblem(OptionsHash optArg) throws Exception {
        
        if(optArg.containsKey("0,0") || optArg.containsKey("1,1")){
            return new ProblemTSP(optArg);
        }
        
        /** Hash con la definicion del problema TSP generado. */
        OptionsHash optDef = new OptionsHash();
        optDef.put(E.numOfCities, optArg.get(E.numOfCities));
        // opt.putAll(optArg);
        
        // Hay que generarlos.
        int numOfCities = Integer.valueOf(optArg.getIndispensable(E.numOfCities));
        float minDist = Float.valueOf(optArg.getIndispensable(E.minDist));
        float maxDist = Float.valueOf(optArg.getIndispensable(E.maxDist));
        
        for(int i=0; i<numOfCities; i++){
            for(int j=i; j<numOfCities; j++){
                if(j == i){
                    optDef.put(String.format("%s,%s", i,j), "0");
                }
                else {
                    switch(genType){
                    case BIASED:
                        optDef.put(String.format("%s,%s", i,j), 
                                String.valueOf(maxDist)); break;
                    case UNIFORM_DISTRIBUTION:
                        optDef.put(String.format("%s,%s", i,j), 
                                String.valueOf(Rand.randFloat(minDist, maxDist))); break;
                    }
                }
                if(genType == GenType.BIASED){
                    int randSisterCity;
                    do{
                        randSisterCity = Rand.randInt(0, numOfCities-1);
                    }while(randSisterCity == i);
                    optDef.put(String.format("%s,%s", i, randSisterCity), 
                                    String.valueOf(minDist));
                }
            }
        }
        
        return new ProblemTSP(optDef);
    }
}
