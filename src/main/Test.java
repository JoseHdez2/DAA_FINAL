package main;

import java.io.File;
import java.util.ArrayList;

import convenience.E;
import convenience.Rand;
import convenience.opthash.HashProbGen;
import convenience.opthash.OptionsHash;
import main.problem.ProblemTSP;
import main.solver.SolverMMD;
import main.solver.SolverTSP;
import main.tester.Tester;
import reader.tsp.GraphProblemFromXML;

public abstract class Test {

    static void test1() throws Exception{
        OptionsHash probOptsXml = GraphProblemFromXML.graphDefFromXML(new File("res/samples/gr17.xml"));
        System.out.println(probOptsXml);
        probOptsXml.put(E.m, String.valueOf(Integer.valueOf(probOptsXml.getIndispensable(E.numOfCities)) / 3));
        ProblemTSP probXml = new ProblemTSP(probOptsXml);
        System.out.println(probXml);
    }
    
    static void test2() throws Exception{
        HashProbGen probOptsGen = new HashProbGen();
        probOptsGen.put("numOfCities", "6");
        probOptsGen.put("minDist", "1");
        probOptsGen.put("maxDist", "9");
        System.out.println(probOptsGen);
        Tester tt = new Tester(new SolverTSP());
        ProblemTSP probGen = (ProblemTSP) tt.solver.generateProblem(probOptsGen);
        System.out.println(probGen);
    }
    
    static void test3() throws Exception{
        HashProbGen probOptsGen = new HashProbGen();
        probOptsGen.put(E.numOfCities, "6");
        probOptsGen.put(E.minDist, "1");
        probOptsGen.put(E.maxDist, "9");
        probOptsGen.put(E.min(E.numOfCities), "3");
        probOptsGen.put(E.max(E.numOfCities), "4");
        probOptsGen.put(E.min(E.maxDist), "8");
        probOptsGen.put(E.max(E.maxDist), "9");
        Tester testes = new Tester(new SolverTSP());
        ArrayList<String> order = new ArrayList<String>();
        order.add(E.numOfCities);
        order.add(E.maxDist);
        testes.testBatteryAndPrint(probOptsGen, order, "test3.md");
    }
    
    static void test4() throws Exception{
        HashProbGen probGenOpt = new HashProbGen();
        probGenOpt.put(E.numOfCities, "6");
        probGenOpt.put(E.minDist, "1");
        probGenOpt.put(E.maxDist, "9");
        probGenOpt.put(E.min(E.randomSeed), "3");
        probGenOpt.put(E.max(E.randomSeed), "5");
        probGenOpt.put(E.min(E.executionNumber), "1");
        probGenOpt.put(E.max(E.executionNumber), "5");
        ArrayList<String> order = new ArrayList<String>();
        order.add(E.randomSeed);
        order.add(E.executionNumber);
        Tester testes = new Tester(new SolverMMD(SolverMMD.solverType.GREEDY));
        testes.testBatteryAndPrint(probGenOpt, order, "test4.md");
    }
    
    static void test5(){
        Rand.setSeed(3);
        System.out.println("Set seed");
        System.out.println(Rand.randInt(1, 100));
        System.out.println(Rand.randInt(1, 100));
        System.out.println(Rand.randInt(1, 100));
        Rand.setSeed(3);
        System.out.println("Set seed");
        System.out.println(Rand.randInt(1, 100));
        int i = 2 + 5;
        System.out.println(Rand.randInt(1, 100));
        System.out.println(Rand.randInt(1, 100));
    }
    
    static void test6() throws Exception{
        Tester testes = new Tester(new SolverTSP());
        HashProbGen opt = new HashProbGen();
        opt.put(E.filename, "res/samples/mmd1.txt");
        opt.put(E.min(E.executionNumber), "1");
        opt.put(E.max(E.executionNumber), "5");
        ArrayList<String> order = new ArrayList<String>();
        order.add(E.executionNumber);
        testes.testBatteryAndPrint(opt, order, "mmd1.md");
    }
}
