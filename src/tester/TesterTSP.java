/**
 * 
 */
package tester;

import java.util.HashMap;

import tester.problem.Problem;
import tester.problem.ProblemTSP;

/**
 * @author jose
 *
 */
public class TesterTSP extends Tester {
    
    Solver solver;
    
    @Override
    public HashMap<String, String> testBattery(OptionsHash opt) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public HashMap<String, String> testBattery(Problem p, OptionsHash opt) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public HashMap<String, String> individualTest(Problem p, OptionsHash opt) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Problem generateProblem(OptionsHash opt) throws Exception {
        
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
                        opt.put(String.format("%s,%s", i,j), 
                                String.valueOf(Rand.randFloat(minDist, maxDist)));
                    }
                }
            }
        }

        return new ProblemTSP(opt);
    }

}
