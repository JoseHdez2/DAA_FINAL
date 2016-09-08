package convenience.opthash;

import convenience.E;

/**
 * Clase creada para distinguir semánticamente de otros objetos OptionsHash.
 * Contiene las opciones de generación de un grafo.
 * Será proporcionado a un método de generación para obtener la definición de un grafo.
 * 
 * @author jose
 *
 */
public class HashGraphGen extends OptionsHash{

    /**
     * Si contiene ya sea el parametro o los rangos minimo y maximo para generarlo.
     * @param str
     * @return
     */
    public boolean containsSpecificOrRandom(String str){
        return this.containsKey(str) || (this.containsKey(E.min + str) && this.containsKey(E.max + str));
    }
    
    public boolean isValid() {
        // super.isValid();
        if (!this.containsSpecificOrRandom(E.numOfCities)) return false;
        if (!this.containsSpecificOrRandom("dist")) return false;
        return true;
    }
}
