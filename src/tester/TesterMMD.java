/**
 * 
 */
package tester;

import java.util.HashMap;

import convenience.E;
import convenience.OptionsHash;
import convenience.Rand;
import tester.problem.InterfaceProblem;
import tester.problem.ProblemTSP;
import tester.solver.Solver;

/**
 * @author jose
 *
 */
public class TesterMMD implements TesterInterface {
    
    Solver solver;
    
    enum GenType {
        UNIFORM_DISTRIBUTION,   // Evenly random distances between minDist and maxDist.
        BIASED                  // A single path with minDist distances, rest with maxDist (good for debugging).
    }
    
    GenType genType = GenType.BIASED;
    
    @Override
    public HashMap<String, String> testBattery(OptionsHash opt) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public HashMap<String, String> testBattery(InterfaceProblem p, OptionsHash opt) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public HashMap<String, String> individualTest(InterfaceProblem p, OptionsHash opt) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public InterfaceProblem generateProblem(OptionsHash opt) throws Exception {
        
        // TODO fast and loose
        if(opt.containsKey("0,0") || opt.containsKey("1,1")){
            // Ya viene con datos.
        } else {
            // Hay que generarlos.
            int numOfCities = Integer.valueOf(opt.getIndispensable(E.numOfCities));
            float minDist = Float.valueOf(opt.getIndispensable(E.minDist));
            float maxDist = Float.valueOf(opt.getIndispensable(E.maxDist));
            
            for(int i=0; i<numOfCities; i++){
                for(int j=i; j<numOfCities; j++){
                    if(j == i){
                        opt.put(String.format("%s,%s", i,j), "0");
                    }
                    else {
                        switch(genType){
                        case BIASED:
                            opt.put(String.format("%s,%s", i,j), 
                                    String.valueOf(maxDist)); break;
                        case UNIFORM_DISTRIBUTION:
                            opt.put(String.format("%s,%s", i,j), 
                                    String.valueOf(Rand.randFloat(minDist, maxDist))); break;
                        }
                    }
                    if(genType == GenType.BIASED){
                        int randSisterCity;
                        do{
                            randSisterCity = Rand.randInt(0, numOfCities-1);
                        }while(randSisterCity == i);
                        opt.put(String.format("%s,%s", i, randSisterCity), 
                                        String.valueOf(minDist));
                    }
                }
            }
        }

        return new ProblemTSP(opt);
    }

}
