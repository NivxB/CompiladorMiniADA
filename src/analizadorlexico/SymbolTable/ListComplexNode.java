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
public class ListComplexNode extends Node {

    String id;
    List<ComplexNode> listfunction;

    public ListComplexNode(String id, List<ComplexNode> Fnode) {
        this.id = id;
        this.listfunction = Fnode;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<ComplexNode> getListfunction() {
        return listfunction;
    }

    public boolean addFunction(ComplexNode function) {
        System.out.println("Entro Para agregar la funcion a la lista");
        boolean retVal = true;
        for (int i = 0; i < listfunction.size() && retVal; i++) {
            ComplexNode functiontmp = listfunction.get(i);
            System.out.println(function.getId() + " paramSize " + function.getParameterType().size());
            System.out.println(functiontmp.getId()+" paramSize " + functiontmp.getParameterType().size());
            if (functiontmp.getParameterType().size() == function.getParameterType().size()) {
                retVal = !compareParameters(functiontmp, function);
                if (!retVal) {
                    if (!functiontmp.getRetType().compare(function.getRetType())) {
                        retVal = true;
                    }
                }
            }
        }

        if (retVal) {
            this.listfunction.add(function);
        }
        return retVal;
    }

    private boolean compareParameters(ComplexNode ThisFunction, ComplexNode OtherFunction) {
        //Si todos son iguales return true
        boolean retVal = true;
        System.out.println("Entro a Parametros: ");
        System.out.println(ThisFunction.getParameterType().size());
        for (int j = 0; j < ThisFunction.getParameterType().size() && retVal; j++) {
            retVal = (ThisFunction.getParameterType().get(j).compare(OtherFunction.getParameterType().get(j)));
        }
        return retVal;
    }

    @Override
    public Type getType() {
        return null;
    }
}
