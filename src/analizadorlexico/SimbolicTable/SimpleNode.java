/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analizadorlexico.SimbolicTable;

import analizadorlexico.TypeCheck.*;

/**
 *
 * @author Carlos
 */
public class SimpleNode extends Node{
    String id;
    Type tipo;

    public SimpleNode(String Nombre, Type tipo) {
        this.id = Nombre;
        this.tipo = tipo;
    }

    public String getNombre() {
        return id;
    }

    public void setNombre(String Nombre) {
        this.id = Nombre;
    }

    public Type getTipo() {
        return tipo;
    }

    public void setTipo(Type tipo) {
        this.tipo = tipo;
    }
    
}
