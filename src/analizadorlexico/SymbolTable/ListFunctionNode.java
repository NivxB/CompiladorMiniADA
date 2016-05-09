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
        int bandera=0;
        for (int i = 0; i < listfunction.size(); i++) {
            FunctionNode functiontmp=listfunction.get(i);
            if(functiontmp.parameterType.size()==function.parameterType.size()){
                for (int j = 0; j < functiontmp.parameterType.size(); j++) {
                    if(functiontmp.parameterType.get(j)!=function.parameterType.get(j)){
                        bandera=1;
                    }
                }
                
            }else{
                bandera=1;
            }
            
        }
        if(listfunction.isEmpty()){
            bandera=1;
        }
        if(bandera==1){
            this.listfunction.add(function);
            return true;
        }else{
            return false;
        }
        
    }
    
    

    @Override
    public Type getType() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
