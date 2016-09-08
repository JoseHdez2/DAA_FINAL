package tester.problem;

import convenience.Graph;
import convenience.opthash.OptionsHash;

/**
 * Implementación de un Problema (Problem) que se pueda modelar en un grafo.
 * Proporciona funcionalidad básica para problemas que usan un grafo como estructura de datos.
 * @author jose
 *
 */
public abstract class ProblemGraph extends Problem{

    Graph g;
    
    public ProblemGraph(ProblemGraph p){
        // TODO passing a reference or a copy?
        g = new Graph(p.g);
    }
    
    public ProblemGraph(OptionsHash opt) throws Exception{
        g = new Graph(opt);
    }
    
    /*
     * Getters.
     */
    
    public int getNumOfCities() {
        return g.numOfCities;
    }
    

    public float dist(int city1, int city2){
        return g.distanceMatrix[city1][city2];
    }
    
    /*
     * toString methods.
     */
    
    @Override
    public String toString(){
        return g.toString();
    }

}
