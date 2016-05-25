/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analizadorlexico.IntermediateCode;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Kevin Barahona
 */
public class IntermediateCode {
    private List<Operation> codeOperations;

    public IntermediateCode() {
        codeOperations = new ArrayList<>();
    }
    
    public void generateSimpleOperation(String toValue, String firstOperation, String secondOperation,String typeOperation){
        // t1 = t2 + 5
        /*
        t1 == toValue
        t2 == firstOperation
        5 == secondOperation
        + = typeOperation
        */
        SimpleOperation newOp = new SimpleOperation(toValue,firstOperation,secondOperation,"=",typeOperation);
        codeOperations.add(newOp);
    }
    
    public void generateLabelOperation(String label){
        //LABEL:
        LabelOperation newOp = new LabelOperation(label);
        codeOperations.add(newOp);
    }
    
    public void generateIfOperation(String firstValue,String secondValue,String compareOperator, String gotoLabel){
        IfOperation newOp = new IfOperation(firstValue,secondValue,compareOperator,gotoLabel);
        codeOperations.add(newOp);
    }
    
    public void generateGotoOperation(String label){
        GotoOperation newOp = new GotoOperation(label);
        codeOperations.add(newOp);
    }
    
    
}
