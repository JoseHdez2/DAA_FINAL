package tester.problem;

import convenience.OptionsHash;
import tester.solution.Solution;
import tester.solution.SolutionTSP;

public class ProblemTSP extends ProblemGraph{
    
    public ProblemTSP(OptionsHash opt) throws Exception {
        super(opt);
        // TODO Auto-generated constructor stub
    }

    @Override
    public float appraiseSolution(Solution s) {
        // TODO this typecasting sucks
        SolutionTSP sol = (SolutionTSP)s;
        float totalDist = 0;
        for(int i = 0; i < sol.size()-1; i++){
            totalDist += dist(i, i+1);
        }
        return totalDist;
    }
    
    public String showSolutionAppraisal(SolutionTSP s){
        String str = "";
        for(int i = 0; i < s.size()-1; i++){
            if (i == 0) str += String.format("%d",i);
            str += String.format(" >[%3.2f]> %d", dist(i, i+1), i+1);
        }
        return str;
    }

    @Override
    public boolean isCompleteSolution(Solution s) {
        SolutionTSP sol = (SolutionTSP)s;
        if (sol.size() == numOfCities) return true;
        else return false;
    }
    
    @Override
    public float getSolutionPotential(Solution s) {
        SolutionTSP sol = (SolutionTSP)s;
        int remainingCities = numOfCities - sol.size();
        // Best possible case, although maybe too naive.
        return getShortestDistance() * remainingCities;
    }

    @Override
    public ObjectiveFunction objFunc() {
        return ObjectiveFunction.MINIMIZE;
    }
}
