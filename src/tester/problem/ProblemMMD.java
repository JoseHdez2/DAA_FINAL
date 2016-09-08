package tester.problem;

import convenience.opthash.OptionsHash;
import tester.solution.Solution;
import tester.solution.SolutionMMD;
import tester.solution.SolutionTSP;

public class ProblemMMD extends ProblemGraph{

    /** Numero de nodos del grafo. */
    int n;
    
    public ProblemMMD(ProblemGraph p){
        super(p);
        n = this.getNumOfCities();
    }
    
    public ProblemMMD(OptionsHash opt) throws Exception{
        super(opt);
        n = this.getNumOfCities();
    }
    
    /** Mean Dispersion (function to maximize) of a Solution. */
    private float md(SolutionMMD s){
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
        return md((SolutionMMD)s);
    }

    @Override
    public boolean isCompleteSolution(Solution s) throws Exception {
        return true;
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

    public float getLongestDistance() {
        return g.getLongestDistance();
    }

}
