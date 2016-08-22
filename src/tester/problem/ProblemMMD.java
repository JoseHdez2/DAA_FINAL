package tester.problem;

import convenience.E;
import convenience.OptionsHash;
import tester.solution.Solution;
import tester.solution.SolutionMMD;
import tester.solution.SolutionTSP;

public class ProblemMMD extends ProblemGraph{

    /** Target size of the solution subset. */
    int m;
    /** Number of nodes */
    int n;
    
    ProblemMMD(OptionsHash opt) throws Exception{
        super(opt);
        m = Integer.valueOf(opt.getIndispensable(E.m));
        n = numOfCities;
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
        SolutionMMD sol = (SolutionMMD)s;
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
        return getLongestDistance() * remainingCities;
    }

    @Override
    public ObjectiveFunction objFunc() {
        return ObjectiveFunction.MAXIMIZE;
    }

}
