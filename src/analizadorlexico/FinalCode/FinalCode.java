/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analizadorlexico.FinalCode;

import analizadorlexico.AST.InitProcedure;
import analizadorlexico.IntermediateCode.CallOperation;
import analizadorlexico.IntermediateCode.GotoOperation;
import analizadorlexico.IntermediateCode.IfOperation;
import analizadorlexico.IntermediateCode.IntermediateCode;
import analizadorlexico.IntermediateCode.LabelOperation;
import analizadorlexico.IntermediateCode.Operation;
import analizadorlexico.IntermediateCode.ParamOperation;
import analizadorlexico.IntermediateCode.ThreeOperation;
import analizadorlexico.IntermediateCode.TwoOperation;
import analizadorlexico.SemanticAnalysis.SemanticAnalysis;
import analizadorlexico.SymbolTable.ComplexNode;
import analizadorlexico.SymbolTable.ListComplexNode;
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
            if (tmp instanceof ListComplexNode) {
                ListComplexNode tmpComplex = (ListComplexNode) tmp;
                for (ComplexNode tmpCom : tmpComplex.getListfunction()) {
                    generateInnerFunctions(tmpCom);
                }
            }
        }

        generateMainProcedure();
    }

    public void generateInnerFunctions(ComplexNode parent) {
        for (String key : parent.getHijos().keySet()) {
            Node tmp = parent.getHijos().get(key);
            if (tmp instanceof ListComplexNode) {
                ListComplexNode tmpComplex = (ListComplexNode) tmp;
                for (ComplexNode tmpCom : tmpComplex.getListfunction()) {
                    generateInnerFunctions(tmpCom);
                }
            }
        }
        Operation operationCheck = intermediateCode.getNextOperation();
        if (operationCheck instanceof LabelOperation) {
            LabelOperation tmp = (LabelOperation) operationCheck;
            finalCode.add(tmp.getLabel() + ":" + "\n");
        } else {
            System.out.println("WUT");
        }
        finalCode.add("sw $fp,-4($sp)" + "\n");
        finalCode.add("sw $ra,-8($sp)" + "\n");
        int offset = 12;
        for (int i = 0; i < parent.getParameterType().size(); i++) {
            if (i < 9) {
                finalCode.add("sw $s" + i + ",-" + offset + "($sp)" + "\n");
            } else {
                /*MAHOU*/
            }
            offset += 4;
        }
        finalCode.add("move $fp,$sp" + "\n");
        finalCode.add("sub $sp,$sp," + offset + "\n");
        int variableCount = (offset - 12) / 4;
        for (int i = 0; i < variableCount; i++) {
            if (i < 3) {
                finalCode.add("move $s" + i + "," + "$a" + i + "\n");
            } else {
                /*MAHOU*/
            }
        }
        generateFunctionCode(parent);
        finalCode.add("move $sp,$fp" + "\n");
        for (int i = offset - 4; i >= 12; i++) {
            if (i < 9) {
                finalCode.add("sw $s" + (i / 4) + ",-" + offset + "($sp)" + "\n");
            } else {
                /*MAHOU*/
            }
            offset -= 4;
        }
        finalCode.add("lw $ra,-8($sp)" + "\n");
        finalCode.add("lw $fp,-4($sp)" + "\n");
        finalCode.add("jr $ra" + "\n");
    }

    public void generateFunctionCode(ComplexNode parent) {
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
                if (tmp.getLabel().equalsIgnoreCase("ENDFUNCTION")){
                    return;
                }
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
                    Temporal.mapFakeTemporalToReal.put(tmp.getFirstValue(), tmpTemporal);
                }
            } else if (operationCheck instanceof ParamOperation) {
                ParamOperation tmp = (ParamOperation) operationCheck;
                String temporalString = getLoadTemporalValue(tmp.getTemporal());
                String A = Temporal.getNextA();
                if (A.equalsIgnoreCase("NULL")) {
                    /* STACK MAHOU */
                } else {
                    finalCode.add("move " + A + "," + temporalString + "\n");
                }
            } else if (operationCheck instanceof CallOperation) {
                CallOperation tmp = (CallOperation) operationCheck;
                Temporal beforeT = new Temporal();
                List<String> aliveTemporal = beforeT.getAliveTemporal();
                int newPosition = 0;
                for (int i = 0; i < aliveTemporal.size(); i++) {
                    String currentTemporal = aliveTemporal.get(i);
                    finalCode.add("sw " + currentTemporal + ", -" + ((i + 1) * 4) + "($sp)" + "\n");
                    newPosition += 4;
                }
                if (newPosition != 0) {
                    finalCode.add("sub $sp,$sp," + newPosition + "\n");
                }
                //Temporal.initAll();
                finalCode.add("jal _" + tmp.getNombre() + tmp.getParamnumber() + "\n");
                if (newPosition != 0) {
                    finalCode.add("add $sp,$sp," + newPosition + "\n");
                }
                for (int i = 0; i < aliveTemporal.size(); i++) {
                    String currentTemporal = aliveTemporal.get(i);
                    finalCode.add("lw " + currentTemporal + ", -" + ((i + 1) * 4) + "($sp)" + "\n");
                    //newPosition+=4;
                }

            }
            generateFunctionCode(parent);
        }
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
                    Temporal.mapFakeTemporalToReal.put(tmp.getFirstValue(), tmpTemporal);
                }
            } else if (operationCheck instanceof ParamOperation) {
                ParamOperation tmp = (ParamOperation) operationCheck;
                String temporalString = getLoadTemporalValue(tmp.getTemporal());
                String A = Temporal.getNextA();
                if (A.equalsIgnoreCase("NULL")) {
                    /* STACK MAHOU */
                } else {
                    finalCode.add("move " + A + "," + temporalString + "\n");
                }
            } else if (operationCheck instanceof CallOperation) {
                CallOperation tmp = (CallOperation) operationCheck;
                Temporal beforeT = new Temporal();
                List<String> aliveTemporal = beforeT.getAliveTemporal();
                int newPosition = 0;
                for (int i = 0; i < aliveTemporal.size(); i++) {
                    String currentTemporal = aliveTemporal.get(i);
                    finalCode.add("sw " + currentTemporal + ", -" + ((i + 1) * 4) + "($sp)" + "\n");
                    newPosition += 4;
                }
                if (newPosition != 0) {
                    finalCode.add("sub $sp,$sp," + newPosition + "\n");
                }
                //Temporal.initAll();
                finalCode.add("jal _" + tmp.getNombre() + tmp.getParamnumber() + "\n");
                if (newPosition != 0) {
                    finalCode.add("add $sp,$sp," + newPosition + "\n");
                }
                for (int i = 0; i < aliveTemporal.size(); i++) {
                    String currentTemporal = aliveTemporal.get(i);
                    finalCode.add("lw " + currentTemporal + ", -" + ((i + 1) * 4) + "($sp)" + "\n");
                    //newPosition+=4;
                }

            }
            generateMainProcedure();
        }
    }

    private String getLoadTemporalValue(String value) {
        if (value.equalsIgnoreCase("RET") || value.equalsIgnoreCase("_RET")) {
            String retVal = Temporal.getFreeTemporal();
            //RESTORE
            finalCode.add("move " + retVal + ",$v0" + "\n");
            return retVal;
        }
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
