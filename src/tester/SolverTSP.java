package tester;

import java.util.Arrays;

import tester.problem.ProblemTSP;

public class SolverTSP {
    
    SolutionTSP solveByGreedy(ProblemTSP p){
        SolutionTSP sol = new SolutionTSP();
        sol.add(1); // Ciudad inicial por defecto.
        
        while(!p.isCompleteSolution(sol)){
            float closestCityDist = Float.POSITIVE_INFINITY;
            Integer closestCity = null;
            
            // Buscamos la ciudad más cercana a la última ciudad y la añadimos.
            for(Integer c = 0; c < p.getNumOfCities(); c++){
                // Nos saltamos la ciudad si ya está en la solución.
                if (Arrays.asList(sol).contains(c)) continue;
                if(p.getDistance(sol.getLatest(), c) < closestCityDist){
                    closestCityDist = p.getDistance(sol.getLatest(), c);
                    closestCity = c;
                }
            }
            sol.add(closestCity);
        }
        // Cuando acaba el bucle, la solución está completa.
        return sol;
    }
    
    SolutionTSP solveByBruteForce(ProblemTSP p){
        SolutionTSP sol = new SolutionTSP();
        
        //if() TODO continuara...
        
        return sol;
    }
}
