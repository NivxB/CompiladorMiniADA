/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analizadorlexico.SymbolTable;

import analizadorlexico.TypeCheck.Type;
import java.util.List;


/**
 *
 * @author Carlos
 */
public class ListFunctionNode extends Node{
    String id;
    List<FunctionNode> listfunction;

    public ListFunctionNode(String id, List<FunctionNode> Fnode) {
        this.id = id;
        this.listfunction=Fnode; 
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<FunctionNode> getListfunction() {
        return listfunction;
    }

    public boolean addFunction(FunctionNode function) {
        boolean retVal = true;
        for (int i = 0; i < listfunction.size() && retVal; i++) {
            FunctionNode functiontmp=listfunction.get(i);
            if(functiontmp.parameterType.size()==function.parameterType.size()){
                retVal = !compareParameters(functiontmp,function);
                //Si se encuentran todos iguales, significa que ya existe una funcion igual
                //TODO: comprobacion de tipo de retorno. 
                if(!retVal){
                    if(functiontmp.tipo!=function.tipo){
                        retVal=true;
                    }
                }
            }
        }
 
        if(retVal){
            this.listfunction.add(function);
        }
        return retVal;
    }
    
    private boolean compareParameters(FunctionNode ThisFunction,FunctionNode OtherFunction){
        //Si todos son iguales return true
        boolean retVal = true;
        for (int j = 0; j < ThisFunction.parameterType.size() && retVal; j++) {
            retVal = (ThisFunction.parameterType.get(j).compare(OtherFunction.parameterType.get(j)));
        }
        return retVal;
    }
    

    @Override
    public Type getType() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
