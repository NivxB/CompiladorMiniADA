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
public class IfOperation extends Operation{

    private String firstValue;
    private String secondValue;
    private String compareOperator;
    private String gotoLabel;

    public IfOperation(String firstValue, String secondValue, String compareOperator, String gotoLabel) {
        this.firstValue = firstValue;
        this.secondValue = secondValue;
        this.compareOperator = compareOperator;
        this.gotoLabel = gotoLabel;
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

    public String getCompareOperator() {
        return compareOperator;
    }

    public void setCompareOperator(String compareOperator) {
        this.compareOperator = compareOperator;
    }

    public String getGotoLabel() {
        return gotoLabel;
    }

    public void setGotoLabel(String gotoLabel) {
        this.gotoLabel = gotoLabel;
    }
    
    
       
    @Override
    public String getStringValue() {
        return "if " +firstValue+compareOperator+secondValue+" GOTO "+gotoLabel;
    }
    
}
