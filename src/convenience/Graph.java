package convenience;

import convenience.opthash.OptionsHash;

/**
 * Clase que representa un grafo.
 * @author jose
 *
 */
public class Graph {
    /** Number of nodes the graph contains. */
    public int numOfCities;
    /** Distances between the nodes.*/
    public float[][] distanceMatrix;
    
    public Graph(Graph g){
        numOfCities = g.numOfCities;
        distanceMatrix = g.distanceMatrix;
    }
    
    public Graph(OptionsHash opt) throws Exception{
        
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
    
    /**
     * @return A String representation of the graph's distance matrix.
     */
    public String printDistancesAsMarkdown(){
        String str = "";
        for(int i = 0; i < numOfCities; i++) str += "--- |";
        str += String.format("%n");
        for(int i = 0; i < numOfCities; i++) str += "--- |";
        str += String.format("%n");
        for(int i = 0; i < numOfCities; i++){
            for(int j = 0; j < numOfCities; j++){
                str += String.format("%3.2f |", this.distanceMatrix[i][j]);
            }
            str += String.format("%n");
        }
        return str;
    }
    
    @Override
    public String toString() {
        return printDistancesAsMarkdown();
    }
}
