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
}
