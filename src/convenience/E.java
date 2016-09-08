package convenience;

/**
 *  Coleccion de cadenas utilizadas en el programa de manera estatica
 *  para aprovechar tanto la seguridad de los enums
 *  como la flexibilidad de las cadenas.
 *  La clase se llama E debido a que se asemeja a un enum.
 * @author jose
 *
 */
public class E {
    // public static final String fromToFormat = "%d,%d"; // TODO overengineering?
    
    /*
     *  Atributos del lector XML.
     */
    public static final String name = "name";
    public static final String source = "source";
    public static final String description = "description";
    public static final String doublePrecision = "doublePrecision";
    public static final String ignoredDigits = "ignoredDigits";
    
    /*
     * Opciones para el testeo.
     */
    public static final String times = "times";

    /*
     * Opciones para la generacion de problemas.
     */
    public static final String randomSeed = "randomSeed";
    
    /*
     * Opciones para la generacion de problemas de grafo.
     */
    public static final String numOfCities = "numOfCities";
    public static final String minDist = "minDist";
    public static final String maxDist = "maxDist";
    
    public static final String filename = "filename";
    public static final String probSizeBeg = "probSizeBeg";
    public static final String probSizeEnd = "probSizeEnd";
    
    // Tester output.
    public static final String timeTaken = "timeTaken";
    public static final String solution = "solution";
    public static final String solutionValue = "solutionValue";
    
    /** Para diferenciar distintas resoluciones del mismo problema con las mismas opciones. */
    public static final String executionNumber = "executionNumber";
    
    /*
     *  Opciones para la generacion del Problema Max-Mean Dispersion
     */
    /** Target size of the solution subset. */
    public static final String m = "m";
    /** Dimensions of the points in space of the MMD Problem. */
    public static final String dim = "dim";
    
    public static final String generationMode = "generationMode";
    public static final String genModeBiased = "genModeBiased";
    public static final String genModeUniDist = "genModeUniDist";
    
    public static final String min = "min";
    public static final String max = "max";
    
    private static String capitalize(String str){
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }
    
    /** E.min("str") = "minStr" */
    public static String min(String str){
        return E.min + E.capitalize(str);
    }
    
    /** E.max("str") = "maxStr" */
    public static String max(String str){
        return E.max + E.capitalize(str);
    }
}
