package reader.tsp;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import convenience.OptionsHash;
import reader.FileStringUtil;
import tester.problem.ProblemTSP;

public class ReaderTSP {
    
    enum Type {
        UNIDIRECTIONAL,
        BIDIRECTIONAL,  // C a D es la misma distancia que D a C.
    }
    
    ReaderTSP.Type type = Type.BIDIRECTIONAL;
    
    public ProblemTSP probFromTXT(File file) throws Exception{
        return new ProblemTSP(optFromTXT(file));
    }
    
    public OptionsHash optFromTXT(File file) throws FileNotFoundException{
        
        String content = new Scanner(file).useDelimiter("\\Z").next();
        ArrayList<String> lines = FileStringUtil.prepareLines(content);
        
        OptionsHash opt = new OptionsHash();
        
        // Line 0 contains the number of nodes. Knowing this, build the distanceMatrix.
        int size = Integer.valueOf(lines.remove(0));
        opt.put("numOfCities", String.valueOf(size));
        
        for (String line : lines){
            String[] splitLine = line.split(FileStringUtil.SEPARATOR);
            Integer from = Integer.valueOf(splitLine[0]);
            Integer to = Integer.valueOf(splitLine[1]);
            Float cost = Float.valueOf(splitLine[2]);
            opt.put(String.format("%d,%d", from-1,to-1), splitLine[2]);
            if (this.type == Type.BIDIRECTIONAL)
                opt.put(String.format("%d,%d", to-1,from-1), splitLine[2]);
        }
        return opt;
    }
}
