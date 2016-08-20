package convenience;

import java.util.Random;

public class Rand {

    static Random r = new Random();
    
    /**
     * @param min
     * @param max
     * @return Random float.
     */
    public static float randFloat(float min, float max) {
        return min + (r.nextFloat() * (max-min));
    }
    
    /**
     * @param min
     * @param max, inclusive.
     * @return Random integer.
     */
    public static int randInt(int min, int max) {
        return min + (r.nextInt(max-min+1));
    }
}
