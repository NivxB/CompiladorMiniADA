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
public class GetCall extends Operation{
    String nombre;
    String variable;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getVariable() {
        return variable;
    }

    public void setVariable(String variable) {
        this.variable = variable;
    }

    public GetCall(String nombre, String variable) {
        this.nombre = nombre;
        this.variable = variable;
    }

    @Override
    public String toString() {
        return nombre +" "+ variable;
    }

    @Override
    public String getStringValue() {
        return nombre +" "+variable;
    }
    
}
