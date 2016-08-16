package tester.problem;

import tester.Solution;
import tester.SolutionTSP;

public class ProblemTSP implements Problem{
    
    protected int numOfCities;
    // Minimum number
    protected int m;
    
    protected float[][] distanceMatrix;
    
    public int getNumOfCities() {
        return numOfCities;
    }

    public float getDistance(int city1, int city2){
        return distanceMatrix[city1][city2];
    }

    @Override
    public float appraiseSolution(Solution s) {
        // TODO this typecasting sucks
        SolutionTSP sol = (SolutionTSP)s;
        float totalDist = 0;
        for(int i = 0; i < sol.size()-1; i++){
            totalDist += getDistance(i, i+1);
        }
        return totalDist;
    }

    @Override
    public boolean isCompleteSolution(Solution s) {
        SolutionTSP sol = (SolutionTSP)s;
        if (sol.size() == m) return true;
        else return false;
    }

    Float shortestDistance = null; 
    private float getShortestDistanceBetweenAnyCity(){
        // Memoization.
        if(shortestDistance != null) return shortestDistance;
        
        for(int i = 0; i < distanceMatrix.length; i++){
            for(int j = i+1; j < distanceMatrix[0].length; j++){
                if(distanceMatrix[i][j] < shortestDistance) 
                    shortestDistance = distanceMatrix[i][j];
            }
        }
        return shortestDistance;
    }
    
    @Override
    public float getSolutionPotential(Solution s) {
        SolutionTSP sol = (SolutionTSP)s;
        
        getShortestDistanceBetweenAnyCity();
        return 0;
    }
}
