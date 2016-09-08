package reader.tsp;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import convenience.E;
import convenience.opthash.HashGraphDef;
import reader.FileStringUtil;

public abstract class ReaderGraph {
    
    enum Type {
        UNIDIRECTIONAL,
        BIDIRECTIONAL,  // C a D es la misma distancia que D a C.
    }
    
    public static ReaderGraph.Type type = Type.BIDIRECTIONAL;
    /*
    public static ProblemTSP probTspFromTXT(File file) throws Exception{
        return new ProblemTSP(optFromTXT(file));
    }*/
    
    public static HashGraphDef optFromTXT(File file) throws FileNotFoundException{
        
        Scanner scanner = new Scanner(file);
        String content = scanner.useDelimiter("\\Z").next();
        scanner.close();
        ArrayList<String> lines = FileStringUtil.prepareLines(content);
        
        HashGraphDef opt = new HashGraphDef();
        
        // Line 0 contains the number of nodes. Knowing this, build the distanceMatrix.
        int size = Integer.valueOf(lines.remove(0));
        opt.put(E.numOfCities, String.valueOf(size));
        int n = Integer.valueOf(opt.get(E.numOfCities));
        for(int i = 0; i < n; i++){
            opt.put(String.format("%d,%d", i,i), "0");
        }
        
        for (String line : lines){
            String[] splitLine = line.split(FileStringUtil.SEPARATOR);
            Integer from = Integer.valueOf(splitLine[0]);
            Integer to = Integer.valueOf(splitLine[1]);
            Float cost = Float.valueOf(splitLine[2]);
            opt.put(String.format("%d,%d", from-1,to-1), Float.toString(cost));
            if (ReaderGraph.type == Type.BIDIRECTIONAL)
                opt.put(String.format("%d,%d", to-1,from-1), Float.toString(cost));
        }
        return opt;
    }
}
