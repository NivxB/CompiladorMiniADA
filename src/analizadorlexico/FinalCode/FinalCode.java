/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analizadorlexico.FinalCode;

import analizadorlexico.AST.InitProcedure;
import analizadorlexico.IntermediateCode.GotoOperation;
import analizadorlexico.IntermediateCode.IfOperation;
import analizadorlexico.IntermediateCode.IntermediateCode;
import analizadorlexico.IntermediateCode.LabelOperation;
import analizadorlexico.IntermediateCode.Operation;
import analizadorlexico.IntermediateCode.ThreeOperation;
import analizadorlexico.IntermediateCode.TwoOperation;
import analizadorlexico.SemanticAnalysis.SemanticAnalysis;
import analizadorlexico.SymbolTable.ComplexNode;
import analizadorlexico.SymbolTable.Node;
import analizadorlexico.SymbolTable.SimpleNode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Kevin Barahona
 */
public class FinalCode {

    private SemanticAnalysis semanticAnalysis;
    private IntermediateCode intermediateCode;
    private List<String> finalCode;

    public FinalCode(SemanticAnalysis semanticAnalysis, IntermediateCode intermediateCode) {
        this.semanticAnalysis = semanticAnalysis;
        this.intermediateCode = intermediateCode;
        finalCode = new ArrayList();
    }

    public List<String> getFinalCode() {
        return finalCode;
    }

    public void initialGeneration() {
        Temporal.initAll();
        finalCode.add(".data\n");
        HashMap<String, Node> parentChilds = semanticAnalysis.getRoot().getHijos();
        for (String key : parentChilds.keySet()) {
            Node tmp = parentChilds.get(key);
            if (tmp instanceof SimpleNode) {
                SimpleNode tmpSimple = (SimpleNode) tmp;
                finalCode.add("_" + tmpSimple.getNombre() + " .word 0\n");
            }
        }
        finalCode.add(".text\n");
        finalCode.add(".globl main\n");
        for (String key : parentChilds.keySet()) {
            Node tmp = parentChilds.get(key);
            if (tmp instanceof ComplexNode) {
                ComplexNode tmpComplex = (ComplexNode) tmp;
                generateInnerFunctions(tmpComplex);
            }
        }

        generateMainProcedure();
    }

    public void generateInnerFunctions(ComplexNode parent) {
        for (String key : parent.getHijos().keySet()) {
            Node tmp = parent.getHijos().get(key);
            if (tmp instanceof ComplexNode) {
                ComplexNode tmpComplex = (ComplexNode) tmp;
                generateInnerFunctions(tmpComplex);
            }
        }

        /*
            MAHOU
         */
    }

    public void generateMainProcedure() {
        Operation operationCheck = intermediateCode.getNextOperation();
        //System.err.println(operationCheck);
        if (operationCheck != null) {
            if (operationCheck instanceof GotoOperation) {
                GotoOperation tmp = (GotoOperation) operationCheck;
                finalCode.add("b " + tmp.getLabel() + "\n");
            } else if (operationCheck instanceof IfOperation) {
                IfOperation tmp = (IfOperation) operationCheck;
                String compareString = tmp.getCompareOperator();
                if (compareString.trim().equals("")) {
                    String firstVal = getLoadTemporalValue(tmp.getFirstValue());
                    String nextTemp = Temporal.getFreeTemporal();
                    finalCode.add("li " + nextTemp + ",1" + "\n");
                    finalCode.add("beq " + firstVal + "," + nextTemp + "," + tmp.getGotoLabel() + "\n");
                    if (firstVal.charAt(0) == '$') {
                        if (!Temporal.freeTemporal.contains(firstVal)) {
                            Temporal.freeTemporal.push(firstVal);
                        }
                    }
                    if (!Temporal.freeTemporal.contains(nextTemp)) {
                        Temporal.freeTemporal.push(nextTemp);
                    }

                } else {
                    String compare = "";
                    if (compareString.trim().equals(">")) {
                        compare = "bgt";
                    } else if (compareString.trim().equals(">=")) {
                        compare = "bge";
                    } else if (compareString.trim().equals("<=")) {
                        compare = "ble";
                    } else if (compareString.trim().equals("<")) {
                        compare = "blt";
                    } else if (compareString.trim().equals("=")) {
                        compare = "beq";
                    }
                    String firstVal = getLoadTemporalValue(tmp.getFirstValue());
                    String nextTemp = getLoadTemporalValue(tmp.getSecondValue());
                    finalCode.add(compare + " " + firstVal + "," + nextTemp + "," + tmp.getGotoLabel() + "\n");
                    if (firstVal.charAt(0) == '$' && !Temporal.freeTemporal.contains(firstVal)) {
                        Temporal.freeTemporal.push(firstVal);
                    }
                    if (nextTemp.charAt(0) == '$' && !Temporal.freeTemporal.contains(nextTemp)) {
                        Temporal.freeTemporal.push(nextTemp);
                    }

                }
            } else if (operationCheck instanceof LabelOperation) {
                LabelOperation tmp = (LabelOperation) operationCheck;
                finalCode.add(tmp.getLabel() + ":" + "\n");
            } else if (operationCheck instanceof ThreeOperation) {
                ThreeOperation tmp = (ThreeOperation) operationCheck;
                String firstVal = getLoadTemporalValue(tmp.getSecondValue());
                String secondVal = getLoadTemporalValue(tmp.getThirdValue());
                String toValue = getLoadTemporalValue(tmp.getFirstValue());
                String oper = "";
                if (tmp.getSecondOperator().equalsIgnoreCase("+")) {
                    oper = "add";
                } else if (tmp.getSecondOperator().equalsIgnoreCase("-")) {
                    oper = "sub";
                } else if (tmp.getSecondOperator().equalsIgnoreCase("*")) {
                    oper = "mul";
                } else if (tmp.getSecondOperator().equalsIgnoreCase("/")) {
                    oper = "div";
                }
                //Temporal.mapFakeTemporalToReal.put(tmp.getFirstValue(), toValue);
                finalCode.add(oper + " " + toValue + "," + firstVal + "," + secondVal + "\n");
                if (firstVal.charAt(0) == '$' && !Temporal.freeTemporal.contains(firstVal)) {
                    Temporal.freeTemporal.push(firstVal);
                }
                if (secondVal.charAt(0) == '$' && !Temporal.freeTemporal.contains(secondVal)) {
                    Temporal.freeTemporal.push(secondVal);
                }
                if (tmp.getFirstValue().charAt(0) != '$') {
                    finalCode.add("sw " + toValue + ", _" + tmp.getFirstValue() + "\n");
                    if (!Temporal.freeTemporal.contains(toValue)) {
                        Temporal.freeTemporal.push(toValue);
                    }
                }
            } else if (operationCheck instanceof TwoOperation) {
                TwoOperation tmp = (TwoOperation) operationCheck;
                if (tmp.getFirstValue().charAt(0) != '$') {
                    String toVal = Temporal.mapFakeTemporalToReal.get(tmp.getSecondValue());
                    //Temporal.mapFakeTemporalToReal.remove(tmp.getSecondValue());
                    finalCode.add("sw " + toVal + ", _" + tmp.getFirstValue() + "\n");
                    if (!Temporal.freeTemporal.contains(toVal)) {
                        Temporal.freeTemporal.push(toVal);
                    }
                } else {
                    //???
                    String tmpTemporal = getLoadTemporalValue(tmp.getSecondValue());
                    Temporal.mapFakeTemporalToReal.put(tmp.getFirstValue(),tmpTemporal);
                }
            }
            generateMainProcedure();
        }
    }

    private String getLoadTemporalValue(String value) {

        if (value.charAt(0) == '$') {
            return Temporal.getTempValue(value);
        } else {
            String retVal = Temporal.getTempValue(value);
            if (isInteger(value)) {
                finalCode.add("li " + retVal + "," + value + "\n");
            } else {
                finalCode.add("lw " + retVal + ", _" + value + "\n");
            }
            return retVal;
        }
    }

    public static boolean isInteger(String s) {
        return isInteger(s, 10);
    }

    public static boolean isInteger(String s, int radix) {
        if (s.isEmpty()) {
            return false;
        }
        for (int i = 0; i < s.length(); i++) {
            if (i == 0 && s.charAt(i) == '-') {
                if (s.length() == 1) {
                    return false;
                } else {
                    continue;
                }
            }
            if (Character.digit(s.charAt(i), radix) < 0) {
                return false;
            }
        }
        return true;
    }
}
