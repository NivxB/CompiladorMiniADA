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
public class TwoOperation extends Operation {
    private String firstValue;
    private String secondValue;
    private String firstOperator;

    public TwoOperation(String firstValue, String secondValue, String firstOperator) {
        this.firstValue = firstValue;
        this.secondValue = secondValue;
        this.firstOperator = firstOperator;
    }

    public String getFirstValue() {
        return firstValue;
    }

    public void setFirstValue(String firstValue) {
        this.firstValue = firstValue;
    }

    public String getSecondValue() {
        return secondValue;
    }

    public void setSecondValue(String secondValue) {
        this.secondValue = secondValue;
    }

    public String getFirstOperator() {
        return firstOperator;
    }

    public void setFirstOperator(String firstOperator) {
        this.firstOperator = firstOperator;
    }

    @Override
    public String getStringValue() {
       return firstValue+ firstOperator +secondValue;
    }
    
    
}
