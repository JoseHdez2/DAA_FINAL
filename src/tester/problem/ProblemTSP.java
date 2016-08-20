package tester.problem;

import convenience.E;
import convenience.OptionsHash;
import tester.solution.Solution;
import tester.solution.SolutionTSP;

public class ProblemTSP implements Problem{
    
    protected int numOfCities;
    
    protected float[][] distanceMatrix;
    
    @Override
    public String toString(){
        String str = "";
        for(int i = 0; i < numOfCities; i++){
            for(int j = 0; j < numOfCities; j++){
                str += String.format("%3.2f ", this.distanceMatrix[i][j]);
            }
            str += String.format("%n");
        }
        return str;
    }
    
    public ProblemTSP(OptionsHash opt) throws Exception{
        
        numOfCities = Integer.valueOf(opt.getIndispensable(E.numOfCities));
        distanceMatrix = new float[numOfCities][numOfCities];
        
        for(int i = 0; i < numOfCities; i++){
            for(int j = i; j < numOfCities; j++){
                distanceMatrix[i][j] = distanceMatrix[j][i] = 
                        Float.valueOf(opt.getIndispensable(String.format("%d,%d", i,j)));
            }
        }
        // validate();
    }
    
    private void validate() throws Exception{
        if(this.distanceMatrix.length != this.distanceMatrix[0].length)
            throw new Exception("Width and length of distance matrix don't match!");
    }
    
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
    
    public String showSolutionAppraisal(SolutionTSP s){
        String str = "";
        for(int i = 0; i < s.size()-1; i++){
            if (i == 0) str += String.format("%d",i);
            str += String.format(" >[%3.2f]> %d", getDistance(i, i+1), i+1);
        }
        return str;
    }

    @Override
    public boolean isCompleteSolution(Solution s) {
        SolutionTSP sol = (SolutionTSP)s;
        if (sol.size() == numOfCities) return true;
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

    @Override
    public Problem generateProblem(OptionsHash opt) {
        // TODO Auto-generated method stub
        return null;
    }
}
