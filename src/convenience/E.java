package convenience;

/**
 *  Collection of strings used in the program, 
 *  to combine the security of enums with the adaptability of strings. 
 * @author jose
 *
 */
public class E {
    // public static final String fromToFormat = "%d,%d"; // TODO overengineering?
    
    // XML Reader attributes.
    public static final String name = "name";
    public static final String source = "source";
    public static final String description = "description";
    public static final String doublePrecision = "doublePrecision";
    public static final String ignoredDigits = "ignoredDigits";
    
    // Tester input.
    public static final String probSizeBeg = "probSizeBeg";
    public static final String probSizeEnd = "probSizeEnd";
    public static final String times = "times";
    // Tester output.
    public static final String timeTaken = "timeTaken";
    public static final String solution = "solution";
    
    // TSP Problem generation input.
    public static final String numOfCities = "numOfCities";
    public static final String minDist = "minDist";
    public static final String maxDist = "maxDist";
    
    // MMD Problem
    /** Target size of the solution subset. */
    public static final String m = "m";
    /** Dimensions of the points in space of the MMD Problem. */
    public static final String dim = "dim";
    
    public static final String min = "min";
    public static final String max = "max";
    
    public static String capitalize(String str){
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }
}
