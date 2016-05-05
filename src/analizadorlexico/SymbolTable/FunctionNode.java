/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analizadorlexico.SymbolTable;

import analizadorlexico.TypeCheck.*;
import java.util.HashMap;

/**
 *
 * @author Carlos
 */
public class FunctionNode extends Node{
    String id;
    Type tipo;
    HashMap<String,Node> hijos;
    Node padre;

    public FunctionNode(String id, Type tipo) {
        this.id = id;
        this.tipo = tipo;
        this.padre = null;
        hijos=new HashMap();
    }

    public FunctionNode(String id, Type tipo, Node padre) {
        this.id = id;
        this.tipo = tipo;
        this.padre = padre;
        hijos=new HashMap();
    }

    public Type getTipo() {
        return tipo;
    }

    public void setTipo(Type tipo) {
        this.tipo = tipo;
    }

    public HashMap<String, Node> getHijos() {
        return hijos;
    }

    public boolean addHijo(String id, Node nodo) {
        if(hijos.containsKey(id)){
            return false;
        }else{
            hijos.put(id, nodo);
            return true;
        }
    }

    public Node getPadre() {
        return padre;
    }

    public void setPadre(Node padre) {
        this.padre = padre;
    }
    
}
