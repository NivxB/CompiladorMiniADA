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
public class PutCall extends Operation{
    String nombre;
    String mensaje;
    int type;

    public PutCall(String nombre, String mensaje, int type) {
        this.nombre = nombre;
        this.mensaje = mensaje;
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    @Override
    public String toString() {
        return "print" +" "+ mensaje;
    }

    @Override
    public String getStringValue() {
        return "print" +" "+ mensaje;
    }
    
    
}
