/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analizadorlexico.SymbolTable;

import analizadorlexico.TypeCheck.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Carlos
 */
public class ComplexNode extends Node {

    private String id;
    private Type retType;
    private HashMap<String, Node> hijos;
    private ComplexNode padre;
    private List<Type> parameterType;
    private int offset;

    public ComplexNode(String id, Type retType, List<Type> pType) {
        this.id = id;
        this.retType = retType;
        this.padre = null;
        this.parameterType = pType;
        hijos = new HashMap();
        offset = 0;
    }

    public List<Type> getParameterType() {
        return parameterType;
    }

    public void addParameter(Type type) {
        parameterType.add(type);
    }

    public ComplexNode(String id, Type retType, List<Type> pType, ComplexNode padre) {
        this.id = id;
        this.retType = retType;
        this.padre = padre;
        hijos = new HashMap();
        this.parameterType = pType;
    }

    public void setTipo(Type retType) {
        this.retType = retType;
    }

    public HashMap<String, Node> getHijos() {
        return hijos;
    }

    public boolean addHijo(String id, Node nodo) {
        if (hijos.containsKey(id)) {
            return false;
        } else {
            if (nodo.getType() != null) {
                offset += nodo.getType().getSIZE();
            }
            hijos.put(id, nodo);
            return true;
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Type searchTypeById(String id) {

        Node retVal = searchNodeById(id);
        if (retVal != null) {
            return retVal.getType();
        }
        return new IntType();
    }

    public Node searchNodeById(String id) {
        System.out.println("Searching: " + id);
        System.out.println("Searching on: " + this.id);
        if (hijos.containsKey(id)) {
            return hijos.get(id);
        } else if (padre != null) {
            //System.out.println("Searching Parent");
            Node retVal = padre.searchNodeById(id);
            return retVal;
        }
        return null;
    }

    public Type searchFunctionNodeTypeById(String id, List<Type> params) {
        Node retVal = searchFunctionNodeById(id, params);
        if (retVal != null) {
            return ((ComplexNode) retVal).retType;
        }
        return new IntType();
    }

    public Node searchFunctionNodeById(String id, List<Type> params) {
        System.out.println("Searching: " + id);
        System.out.println("Searching on: " + this.id);
        if (hijos.containsKey(id)) {
            if (hijos.get(id) instanceof ListComplexNode) {
                ListComplexNode functions = (ListComplexNode) hijos.get(id);
                for (int i = 0; i < functions.getListfunction().size(); i++) {
                    ComplexNode function = functions.getListfunction().get(i);
                    if (function.compareParameters(params)) {
                        return function;
                    }

                }
            }
        } else if (padre != null) {
            System.err.println("Didn't found " + id + " on " + this.id);
            System.err.println("Searching parent");
            Node retVal = padre.searchFunctionNodeById(id, params);
            return retVal;
        }
        System.err.println("Didn't found " + id + " anywhere");
        return null;
    }

    public boolean compareParameters(List<Type> params) {
        boolean retVal = true;
        if (params.size() != parameterType.size()) {
            retVal = false;
        }
        for (int i = 0; i < params.size() && retVal; i++) {
            retVal = parameterType.get(i).compare(params.get(i));
        }
        return retVal;
    }

    public Node getPadre() {
        return padre;
    }

    public void setPadre(ComplexNode padre) {
        this.padre = padre;
    }

    @Override
    public Type getType() {
        return new VoidType();
    }

    public Type getRetType() {
        return retType;
    }
}
