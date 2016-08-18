package main;

import tester.OptionsHash;
import tester.TesterTSP;
import tester.problem.ProblemTSP;

public class Main {
    public static void main(String[] args) throws Exception {
        OptionsHash oh = new OptionsHash();
        oh.put("m", "5");
        oh.put("numOfCities", "4");
        oh.put("minDist", "1");
        oh.put("maxDist", "9");
        System.out.println(oh);
        TesterTSP tt = new TesterTSP();
        ProblemTSP p = (ProblemTSP) tt.generateProblem(oh);
        System.out.println(p);
    }
}
