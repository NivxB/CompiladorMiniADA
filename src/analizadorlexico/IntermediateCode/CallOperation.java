/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analizadorlexico.IntermediateCode;

/**
 *
 * @author Carlos
 */
public class CallOperation extends Operation{
    String nombre;
    int paramnumber;
    public CallOperation(String nombre, int paramnumber) {
        this.nombre = nombre;
        this.paramnumber=paramnumber;
    }

    public int getParamnumber() {
        return paramnumber;
    }

    public void setParamnumber(int paramnumber) {
        this.paramnumber = paramnumber;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    @Override
    public String getStringValue() {
        return "call "+nombre+" , "+ paramnumber;
    }
    
}
