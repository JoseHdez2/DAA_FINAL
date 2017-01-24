package main.problem;

import main.solution.Solution;
import main.solution.SolutionDispersion;
import main.solution.SolutionTSP;

/**
 * Maximum Diversity Problem
 */
public class ProblemDiversity extends ProblemGraph{

    /** Tamaño objetivo del conjunto solucion a este problema. */
    int m;
    /** Numero de nodos del grafo. */
    int n;
    
    ProblemDiversity(ProblemGraph p){
        super(p);
        ProblemDiversity p2 = (ProblemDiversity)p;
        m = p2.m;
        n = this.g.numOfCities;
    }
    /*
    ProblemMDiv(OptionsHash opt) throws Exception{
        super(opt);
        m = Integer.valueOf(opt.getIndispensable(E.m));
        n = getNumOfCities();
    }*/
    
    /** Mean Dispersion (function to maximize) of a Solution. */
    private float md(SolutionDispersion s){
        float sum = 0;
        for(int i = 0; i < s.size(); i++){
            for(int j = 0; j < i; j++){
                sum += dist(i, j);
            }
        }
        return sum / s.size();
    }

    @Override
    public float appraiseSolution(Solution s) {
        return md((SolutionDispersion)s);
    }

    @Override
    public boolean isCompleteSolution(Solution s) throws Exception {
        SolutionDispersion sol = (SolutionDispersion)s;
        if (sol.size() == m) return true;
        if (sol.size() < m) return false;
        // reach if (sol.size() > m) 
        throw new Exception(String.format("The jig is up", true));
    }

    @Override
    public float getSolutionPotential(Solution s) {
        SolutionTSP sol = (SolutionTSP)s;
        int remainingCities =  - sol.size();
        // Best possible case, although maybe too naive.
        return g.getLongestDistance() * remainingCities;
    }

    @Override
    public ObjectiveFunction objFunc() {
        return ObjectiveFunction.MAXIMIZE;
    }

}
