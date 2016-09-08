package tester.solver;

import java.io.File;
import java.util.ArrayList;

import convenience.E;
import convenience.Rand;
import convenience.opthash.HashGraphDef;
import convenience.opthash.HashProbDef;
import convenience.opthash.HashProbGen;
import reader.tsp.GraphProblemFromXML;
import reader.tsp.ReaderGraph;
import tester.problem.InterfaceProblem;
import tester.problem.ProblemGraph;
import tester.solution.Solution;
import tester.solution.SolutionGraph;

public abstract class SolverGraph extends Solver{
    
    @Override
    public ArrayList<Solution> getNeighbors(Solution s, InterfaceProblem p, NeighborMode mode) {
        SolutionGraph sol = (SolutionGraph)s;
        ProblemGraph prob = (ProblemGraph)p;
        
        ArrayList<Solution> neighs = new ArrayList<Solution>();
        if(neighMode == NeighborMode.ADDITIVE ||
                neighMode == NeighborMode.ALL){
            for(int i = 0; i < prob.getNumOfCities(); i++){
                System.out.println(String.format("addCity %d...", i));
                if(sol.contains(i)){
                    System.out.println(String.format("...is already in %s", sol));
                    continue;
                }
                SolutionGraph neigh = new SolutionGraph();
                neigh.addAll(sol);
                neigh.add(i);
                neighs.add(neigh);
            }
        }
        if(neighMode == NeighborMode.REDUCTIVE ||
                neighMode == NeighborMode.ALL){
            for(int i = 0; i < sol.size(); i++){
                SolutionGraph neigh = new SolutionGraph();
                neigh.addAll(sol);
                neigh.remove(i);
                neighs.add(neigh);
            }
        }
        
        return neighs;
    }

    enum GenType {
        UNIFORM_DISTRIBUTION,   // Evenly random distances between minDist and maxDist.
        BIASED                  // A single path with minDist distances, rest with maxDist (good for debugging).
    }
    
    GenType genType = GenType.UNIFORM_DISTRIBUTION;
    
    // TODO revisar esta funcion
    @Override
    public HashProbDef generateProblemDefinition(HashProbGen hashProbGen) throws Exception {
        
        /* En caso de que recibamos un hash con el grafo en vez de
         * un grafo con las opciones de generacion. */
        
        if(hashProbGen.containsKey(E.filename)){
            HashGraphDef graphDef = new HashGraphDef();
            String filename = hashProbGen.get(E.filename);
            String extension = filename.substring(filename.length()-4);
            switch(extension){
            case ".txt": graphDef = ReaderGraph.optFromTXT(new File(filename)); break;
            case ".xml": graphDef = GraphProblemFromXML.graphDefFromXML(new File(filename)); break;
            default: throw new Exception("Unknown file extension.");
            }
            HashProbDef probDef = new HashProbDef();
            probDef.putAll(graphDef);
            return probDef;
        }
        
        // HACK
        if(hashProbGen.containsKey("0,0") || hashProbGen.containsKey("1,1")){
            HashProbDef h = new HashProbDef();
            h.putAll(hashProbGen);
            return h;
        }
        
        if(hashProbGen.containsKey(E.generationMode)){
            switch(hashProbGen.getIndispensable(E.generationMode)){
            case E.genModeUniDist: genType = GenType.UNIFORM_DISTRIBUTION; break;
            case E.genModeBiased: genType = GenType.BIASED; break;
            }
        }
        
        
        if(hashProbGen.containsKey(E.randomSeed)) 
            Rand.setSeed(Long.valueOf(hashProbGen.get(E.randomSeed)));
        
        /** Hash con la definicion arista por arista del grafo generado. */
        HashProbDef optDef = new HashProbDef();
        optDef.put(E.numOfCities, hashProbGen.get(E.numOfCities));
        // opt.putAll(optArg);
        
        // Hay que generarlos.
        int numOfCities = Integer.valueOf(hashProbGen.getIndispensable(E.numOfCities));
        float minDist = Float.valueOf(hashProbGen.getIndispensable(E.minDist));
        float maxDist = Float.valueOf(hashProbGen.getIndispensable(E.maxDist));
        
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
        
        return optDef;
    }
    
    // abstract public ProblemGraph generateGraphProblem(HashGraphGen g, HashProbGen p);
    
    /*
    @Override
    public InterfaceProblem generateProblem(OptionsHash opt) throws Exception {
        return new ProblemGraph(generateProblemDefinition(opt));
    }*/
}
