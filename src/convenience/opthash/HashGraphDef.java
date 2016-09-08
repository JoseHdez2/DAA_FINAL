package convenience.opthash;

import convenience.E;

public class HashGraphDef extends OptionsHash {

    @Override
    public boolean isValid() {
        // super.isValid();
        // Podemos tener una definicion en un archivo.
        if (this.containsKey(E.filename)) return true;
        /* O podemos tener el numero de ciudades, y el
         * maximo y minimo de las distancias entre ellas.
         */
        if (!this.containsKey(E.numOfCities)) return false;
        if (!(this.containsKey("0,0")||this.containsKey("1,1"))) return false;
        return true;
    }
}
