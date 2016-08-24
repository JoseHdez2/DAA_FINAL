package tester.problem;

import convenience.E;
import convenience.opthash.OptionsHash;

/**
 * Implementación de un Problema (Problem) que se pueda modelar en un grafo.
 * Proporciona funcionalidad básica para problemas que usan un grafo como estructura de datos.
 * @author jose
 *
 */
public abstract class ProblemGraph extends Problem{

    /** Number of nodes the graph contains. */
    protected int numOfCities;
    /** Distances between the nodes.*/
    protected float[][] distanceMatrix;
    
    public ProblemGraph(OptionsHash opt) throws Exception{
        
        numOfCities = Integer.valueOf(opt.getIndispensable(E.numOfCities));
        distanceMatrix = new float[numOfCities][numOfCities];
        
        for(int i = 0; i < numOfCities; i++){
            for(int j = i; j < numOfCities; j++){
                distanceMatrix[i][j] = distanceMatrix[j][i] = 
                        Float.valueOf(opt.getIndispensable(String.format("%d,%d", i,j)));
            }
        }
        // validate();
        calculateEdgeDistances();
    }
    
    private void validate() throws Exception{
        if(this.distanceMatrix.length != this.distanceMatrix[0].length)
            throw new Exception("Width and length of distance matrix don't match!");
    }
    
    /*
     * Functionality.
     */
    
    Float shortestDistance = null;
    Float longestDistance = null;
    /**
     * @return Shortest distance between any two nodes on the graph.
     */
    public float getShortestDistance(){
        if(shortestDistance == null) calculateEdgeDistances();
        return shortestDistance;
    }
    
    /**
     * @return Longest distance between any two nodes on the graph.
     */
    public float getLongestDistance(){
        if(longestDistance == null) calculateEdgeDistances();
        return longestDistance;
    }
    
    /**
     * Calculate the largest and shortest distances between any two nodes in the graph,
     * and save them internally to be called.
     */
    private void calculateEdgeDistances(){
        shortestDistance = Float.MAX_VALUE;
        longestDistance = -Float.MAX_VALUE;
        for(int i = 0; i < distanceMatrix.length; i++){
            for(int j = 0; j < distanceMatrix[0].length; j++){
                if(i == j) continue;
                if(distanceMatrix[i][j] < shortestDistance) 
                    shortestDistance = distanceMatrix[i][j];
                if(distanceMatrix[i][j] > longestDistance) 
                    longestDistance = distanceMatrix[i][j];
            }
        }
    }
    
    /**
     * Return number of paths, taking into consideration the number of 
     * @param numberOfNodes
     * @return
     */
    private double numberOfPaths(int numberOfNodes){
        // return Math.pow(numberOfNodes, 2) - numberOfNodes;
        return numberOfNodes*(numberOfNodes-1)/2;
    }
    
    /*
     * Getters.
     */
    
    public int getNumOfCities() {
        return numOfCities;
    }
    

    public float dist(int city1, int city2){
        return distanceMatrix[city1][city2];
    }
    
    /*
     * toString methods.
     */
    
    @Override
    public String toString(){
        return printDistances();
    }
    
    /**
     * @return A String representation of the graph's distance matrix.
     */
    public String printDistances(){
        String str = "";
        for(int i = 0; i < numOfCities; i++){
            for(int j = 0; j < numOfCities; j++){
                str += String.format("%3.2f ", this.distanceMatrix[i][j]);
            }
            str += String.format("%n");
        }
        return str;
    }
}
