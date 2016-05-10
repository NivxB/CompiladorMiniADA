/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analizadorlexico.SymbolTable;

import analizadorlexico.TypeCheck.*;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Carlos
 */
public class FunctionNode extends Node{
    String id;
    Type tipo;
    HashMap<String,Node> hijos;
    FunctionNode padre;
    List<Type> parameterType;

    public FunctionNode(String id, Type tipo , List<Type> pType) {
        this.id = id;
        this.tipo = tipo;
        this.padre = null;
        this.parameterType = pType;
        hijos=new HashMap();
    }

    public List<Type> getParameterType() {
        return parameterType;
    }

    public void addParameter(Type type){
        parameterType.add(type);
    }

    public FunctionNode(String id, Type tipo, List<Type> pType ,FunctionNode padre) {
        this.id = id;
        this.tipo = tipo;
        this.padre = padre;
        hijos=new HashMap();
        this.parameterType = pType;
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
    
    public Type searchTypeById(String id){
        Node retVal = searchNodeById(id);
        if (retVal != null){
            return retVal.getType();
        }
        return null;
    }
    
    public Node searchNodeById(String id){
        if (hijos.containsKey("id")){
            return hijos.get(id);
        }else if (padre != null){
            Node retVal = padre.searchNodeById(id);
            if (retVal != null){
                return retVal;
            }
        }
        return null;
    }
    
    public Type searchFunctionNodeTypeById(String id, List<Type> params){
        //TODO:
        return null;
    }
    
    public Node searchFunctionNodeById(String id, List<Type> params){
        //TODO:
        return null;
    }
    
    public boolean compareParameters(List<Type> params){
        boolean retVal = true;
        if (params.size() != parameterType.size()){
            retVal = false;
        }
        for (int i = 0 ; i < params.size() && retVal ; i++){
            retVal = parameterType.get(i).compare(params.get(i));
        }
        return retVal;
    }
    
    public Node getPadre() {
        return padre;
    }

    public void setPadre(FunctionNode padre) {
        this.padre = padre;
    }

    @Override
    public Type getType() {
        return tipo;
    }
    
}
