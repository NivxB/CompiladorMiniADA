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
public class SimpleOperation extends Operation{
    private String firstValue;
    private String secondValue;
    private String thirdValue;
    private String firstOperator;
    private String secondOperator;

    public SimpleOperation(String firstValue, String secondValue, String thirdValue, String firstOperator, String secondOperator) {
        this.firstValue = firstValue;
        this.secondValue = secondValue;
        this.thirdValue = thirdValue;
        this.firstOperator = firstOperator;
        this.secondOperator = secondOperator;
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

    public String getThirdValue() {
        return thirdValue;
    }

    public void setThirdValue(String thirdValue) {
        this.thirdValue = thirdValue;
    }

    public String getFirstOperator() {
        return firstOperator;
    }

    public void setFirstOperator(String firstOperator) {
        this.firstOperator = firstOperator;
    }

    public String getSecondOperator() {
        return secondOperator;
    }

    public void setSecondOperator(String secondOperator) {
        this.secondOperator = secondOperator;
    }

    @Override
    public String getStringValue() {
        return firstValue + firstOperator + secondValue + secondOperator + thirdValue;
    }
    
    
}
