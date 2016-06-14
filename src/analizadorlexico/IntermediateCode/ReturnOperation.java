/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analizadorlexico.IntermediateCode;

/**
 *
 * @author Kevin Barahona
 */
public class ReturnOperation extends Operation{
    private String retVal;

    public ReturnOperation(String retVal) {
        this.retVal = retVal;
    }

    public String getRetVal() {
        return retVal;
    }

    public void setRetVal(String retVal) {
        this.retVal = retVal;
    }
    
    

    @Override
    public String getStringValue() {
       return "RET " + retVal;
    }
    
}
