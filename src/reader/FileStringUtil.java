package reader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Predicate;

public class FileStringUtil {
    
    // Token separator, for visibly reducing whitespace, etc. TODO: weird phrasing
    public static String SEPARATOR = "#";
    
    private static Predicate<String> emptyLine(){
        return p -> p.isEmpty();
    }
    
    /**
     * Prepare lines for parsing: remove comments and empty lines, and reduce whitespace.
     * @param programFileContent
     * @return
     */
    public static ArrayList<String> prepareLines(String programFileContent){
        String[] lines = programFileContent.split(System.getProperty("line.separator")); // http://stackoverflow.com/a/1096633/3399416
        
        for (int i = 0; i < lines.length; i++){
            // Remove comments. Only keep what's before the ';' (comment separator).
            lines[i] = lines[i].split(";")[0];
            // Replace whitespace with separators.
            lines[i] = lines[i].trim().replaceAll("\\s+", SEPARATOR);
        }
        ArrayList<String> lines2 = new ArrayList<String>(Arrays.asList(lines));
        
        // Only keep the lines with code.
        lines2.removeIf(emptyLine());
        
        System.out.println(lines2);
        return lines2;
    }
}
