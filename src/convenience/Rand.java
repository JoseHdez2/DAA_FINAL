package convenience;

import java.util.Random;

public class Rand {

    static Random r = new Random();
    
    /**
     * @param min
     * @param max
     * @return Numero de punto flotante al azar.
     */
    public static float randFloat(float min, float max) {
        return min + (r.nextFloat() * (max-min));
    }
    
    /**
     * @param min
     * @param max, inclusive.
     * @return Numero entero al azar.
     */
    public static int randInt(int min, int max) {
        return min + (r.nextInt(max-min+1));
    }
    
    /**
     * Fija el estado del generador de numeros aleatorios.
     * De este modo se obtienen los mismos resultados.
     * @param seed
     */
    public static void setSeed(long seed) {
        r.setSeed(seed);
    }
}
