package tester;

import java.util.HashMap;

public class OptionsHash extends HashMap<String, String> {
    
    /**
     * Obtiene una opción indispensable, dando error si no se da.
     * Es una manera de validar la entrada a una funcion, comprobando que 
     * existe la informacion requerida.
     * @param str
     * @return
     * @throws Exception Si la informacion indispensable no se encuentra..
     */
    public String getIndispensable(String str) throws Exception{
        if(!this.containsKey(str)){
            throw new Exception(String.format("Indispensable option %s not in OptionsHash%n%s!", str, this));
        }
        else return this.get(str);
    }
}
