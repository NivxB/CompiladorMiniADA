/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analizadorlexico.SymbolTable;

import analizadorlexico.TypeCheck.*;

/**
 *
 * @author Carlos
 */
public class SimpleNode extends Node{
    String id;
    Type tipo;
    int offset;
    

    public SimpleNode(String Nombre, Type tipo, int offset) {
        this.id = Nombre;
        this.tipo = tipo;
        this.offset = offset;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
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

    @Override
    public Type getType() {
       return tipo;
    }
    
}
