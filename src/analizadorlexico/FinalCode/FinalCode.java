/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analizadorlexico.FinalCode;

import analizadorlexico.AST.InitProcedure;
import analizadorlexico.IntermediateCode.CallOperation;
import analizadorlexico.IntermediateCode.GetCall;
import analizadorlexico.IntermediateCode.GotoOperation;
import analizadorlexico.IntermediateCode.IfOperation;
import analizadorlexico.IntermediateCode.IntermediateCode;
import analizadorlexico.IntermediateCode.LabelOperation;
import analizadorlexico.IntermediateCode.Operation;
import analizadorlexico.IntermediateCode.ParamOperation;
import analizadorlexico.IntermediateCode.PutCall;
import analizadorlexico.IntermediateCode.ReturnOperation;
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
        MemoryControl.initAll();
        finalCode.add(".data\n");
        HashMap<String, Node> parentChilds = semanticAnalysis.getRoot().getHijos();
        HashMap<String, String> MessagesKey = MemoryControl.MessageMap;
        for (String key : parentChilds.keySet()) {
            Node tmp = parentChilds.get(key);
            if (tmp instanceof SimpleNode) {
                SimpleNode tmpSimple = (SimpleNode) tmp;
                finalCode.add("_" + tmpSimple.getNombre() + " .word 0\n");
            }
        }
        for (String key : MessagesKey.keySet()) {
            String Value=MessagesKey.get(key);
            finalCode.add("_"+Value+" .asciiz "+key+"\n");
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
        MemoryControl.initAll();
        String funName = "";
        Operation operationCheck = intermediateCode.getNextOperation();
        if (operationCheck instanceof LabelOperation) {
            LabelOperation tmp = (LabelOperation) operationCheck;
            finalCode.add(tmp.getLabel() + ":" + "\n");
            funName = tmp.getLabel();
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
        int argumentNumber = 0;
        for (int i = 0; i < parent.getHijos().keySet().size(); i++) {
            Node tmp = parent.getHijos().get((String) parent.getHijos().keySet().toArray()[i]);
            if (tmp instanceof SimpleNode) {
                if (((SimpleNode) tmp).getOffset() < 0) {
                    MemoryControl.argumentToTemporal.put(((SimpleNode) tmp).getId(), "$s" + (argumentNumber));
                    argumentNumber++;
                } else {
                    MemoryControl.argumentToTemporal.put(((SimpleNode) tmp).getId(), "-" + offset + "($fp)");
                    offset += 4;
                }

            }
        }
        finalCode.add("sub $sp,$sp," + (offset - 4) + "\n");
        for (int i = 0; i < argumentNumber; i++) {
            if (i < 3) {
                finalCode.add("move $s" + i + "," + "$a" + i + "\n");
            } else {
                /*MAHOU*/
            }
        }

        generateFunctionCode(parent, "returnCode" + funName);
        String exitLabel = "_returnCode" + funName;
        if (finalCode.get(finalCode.size() - 1).equalsIgnoreCase("b " + exitLabel + "\n")) {
            finalCode.remove(finalCode.size() - 1);
        }
        finalCode.add(exitLabel + ":" + "\n");
        finalCode.add("move $sp,$fp" + "\n");
        for (int i = argumentNumber; i > 0; i--) {
            finalCode.add("lw $s" + (i - 1) + ",-" + ((i * 4) + 8) + "($fp)" + "\n");

        }
        finalCode.add("lw $ra,-8($sp)" + "\n");
        finalCode.add("lw $fp,-4($sp)" + "\n");
        finalCode.add("jr $ra" + "\n");
    }

    public void generateFunctionCode(ComplexNode parent, String exitLabel) {
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
                    String firstVal = getLoadArgumentValue(tmp.getFirstValue());
                    String nextTemp = MemoryControl.getFreeTemporal();
                    finalCode.add("li " + nextTemp + ",1" + "\n");
                    finalCode.add("beq " + firstVal + "," + nextTemp + "," + tmp.getGotoLabel() + "\n");
                    if (firstVal.charAt(0) == '$') {
                        if (!MemoryControl.freeTemporal.contains(firstVal) && firstVal.matches("\\$t\\d+")) {
                            MemoryControl.freeTemporal.push(firstVal);
                        }
                    }
                    if (!MemoryControl.freeTemporal.contains(nextTemp) && nextTemp.matches("\\$t\\d+")) {
                        MemoryControl.freeTemporal.push(nextTemp);
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
                    String firstVal = getLoadArgumentValue(tmp.getFirstValue());
                    String nextTemp = getLoadArgumentValue(tmp.getSecondValue());
                    finalCode.add(compare + " " + firstVal + "," + nextTemp + "," + tmp.getGotoLabel() + "\n");
                    if (firstVal.charAt(0) == '$' && !MemoryControl.freeTemporal.contains(firstVal) && firstVal.matches("\\$t\\d+")) {
                        MemoryControl.freeTemporal.push(firstVal);
                    }
                    if (nextTemp.charAt(0) == '$' && !MemoryControl.freeTemporal.contains(nextTemp) && nextTemp.matches("\\$t\\d+")) {
                        MemoryControl.freeTemporal.push(nextTemp);
                    }

                }
            } else if (operationCheck instanceof LabelOperation) {
                LabelOperation tmp = (LabelOperation) operationCheck;
                if (tmp.getLabel().equalsIgnoreCase("ENDFUNCTION")) {
                    return;
                }
                finalCode.add(tmp.getLabel() + ":" + "\n");
            } else if (operationCheck instanceof ThreeOperation) {
                ThreeOperation tmp = (ThreeOperation) operationCheck;
                String firstVal = getLoadArgumentValue(tmp.getSecondValue());
                String secondVal = getLoadArgumentValue(tmp.getThirdValue());
                String toValue = getLoadArgumentValue(tmp.getFirstValue());
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
                if (firstVal.charAt(0) == '$' && !MemoryControl.freeTemporal.contains(firstVal) && firstVal.matches("\\$t\\d+")) {
                    MemoryControl.freeTemporal.push(firstVal);
                }
                if (secondVal.charAt(0) == '$' && !MemoryControl.freeTemporal.contains(secondVal) && secondVal.matches("\\$t\\d+")) {
                    MemoryControl.freeTemporal.push(secondVal);
                }
                /*if (tmp.getFirstValue().charAt(0) != '$') {
                    String innerFirstVal = getLoadArgumentValue(tmp.getFirstValue());
                    if (innerFirstVal.charAt(0) == '-') {
                        finalCode.add("sw " + toValue + ", _" + innerFirstVal + "\n");
                        if (!Temporal.freeTemporal.contains(toValue)) {
                            Temporal.freeTemporal.push(toValue);
                        }
                    }
                }*/
            } else if (operationCheck instanceof TwoOperation) {
                TwoOperation tmp = (TwoOperation) operationCheck;
                if (tmp.getFirstValue().charAt(0) != '$') {
                    String toVal = getLoadArgumentValue(tmp.getSecondValue());
                    String val = tmp.getFirstValue();
                    if (MemoryControl.argumentToTemporal.containsKey(val)) {
                        String argumentValue = MemoryControl.argumentToTemporal.get(val);
                        if (argumentValue.charAt(0) == '$') {
                            finalCode.add("move " + argumentValue + ", " + toVal +"\n");
                        } else {
                            finalCode.add("sw " + toVal + ", " + argumentValue +"\n");
                        }
                    } else {
                        finalCode.add("sw " + toVal + ", _" + tmp.getFirstValue() + "\n");
                    }
                    if (!MemoryControl.freeTemporal.contains(toVal) && toVal.matches("\\$t\\d+")) {
                        MemoryControl.freeTemporal.push(toVal);
                    }

                } else {
                    //???
                    String tmpTemporal = getLoadArgumentValue(tmp.getSecondValue());
                    MemoryControl.mapFakeTemporalToReal.put(tmp.getFirstValue(), tmpTemporal);
                }
            } else if (operationCheck instanceof ParamOperation) {
                ParamOperation tmp = (ParamOperation) operationCheck;
                String temporalString = getLoadArgumentValue(tmp.getTemporal());
                String A = MemoryControl.getNextA();
                if (A.equalsIgnoreCase("NULL")) {
                    /* STACK MAHOU */
                } else {
                    finalCode.add("move " + A + "," + temporalString + "\n");
                }
            } else if (operationCheck instanceof CallOperation) {
                CallOperation tmp = (CallOperation) operationCheck;
                MemoryControl beforeT = new MemoryControl();
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

            } else if (operationCheck instanceof ReturnOperation) {
                ReturnOperation tmp = (ReturnOperation) operationCheck;
                String temporalString = getLoadTemporalValue(tmp.getRetVal());
                finalCode.add("move $v0," + temporalString + "\n");
                finalCode.add("b _" + exitLabel + "\n");
            }else if(operationCheck instanceof PutCall){
                PutCall tmp= (PutCall)(operationCheck);
                if(MemoryControl.MessageMap.containsKey(tmp.getMensaje())){
                    finalCode.add("li $v0, 4\n");
                    finalCode.add("la $a0, "+MemoryControl.MessageMap.get(tmp.getMensaje())+"\n");
                    finalCode.add("syscall\n");
                }else{
                    int bandera=0;
                    HashMap<String, Node> parentChilds = semanticAnalysis.getRoot().getHijos();
                    for (String key : parentChilds.keySet()) {
                        Node tmp2 = parentChilds.get(key);
                        if (tmp2 instanceof SimpleNode) {
                            SimpleNode tmpSimple = (SimpleNode) tmp2;
                            if(tmpSimple.getNombre().equals(tmp.getMensaje())){
                                finalCode.add("li $v0, 4\n");
                                finalCode.add("la $a0, "+tmp.getMensaje()+"\n");
                                finalCode.add("syscall\n");
                                bandera++;
                                break;
                            }
                        }
                    }
                    if(bandera==0){
                        //Codigo para guardar en memoria
                    }
                }
            }else if(operationCheck instanceof GetCall){
                GetCall tmp=(GetCall)operationCheck;
                
                if(tmp.gettipo()==0){
                    int bandera=0;
                    HashMap<String, Node> parentChilds = semanticAnalysis.getRoot().getHijos();
                    for (String key : parentChilds.keySet()) {
                        Node tmp2 = parentChilds.get(key);
                        if (tmp2 instanceof SimpleNode) {
                            SimpleNode tmpSimple = (SimpleNode) tmp2;
                            if(tmpSimple.getNombre().equals(tmp.getVariable())){
                                finalCode.add("li $v0, 1\n");
                                finalCode.add("syscalls\n");
                                finalCode.add("sw $v0, "+tmp.getVariable()+"\n");
                                bandera++;
                                break;
                            }
                        }
                    }
                    if(bandera==0){
                        //Codigo para guardar en memoria
                    }
                    
                }else{
                    int bandera=0;
                    HashMap<String, Node> parentChilds = semanticAnalysis.getRoot().getHijos();
                    for (String key : parentChilds.keySet()) {
                        Node tmp2 = parentChilds.get(key);
                        if (tmp2 instanceof SimpleNode) {
                            SimpleNode tmpSimple = (SimpleNode) tmp2;
                            if(tmpSimple.getNombre().equals(tmp.getVariable())){
                                finalCode.add("li $v0, 2\n");
                                finalCode.add("syscalls\n");
                                finalCode.add("sw $v0, "+tmp.getVariable()+"\n");
                                bandera++;
                                break;
                            }
                        }
                    }
                    if(bandera==0){
                        //Codigo para guardar en memoria
                    }
                }
            }
            generateFunctionCode(parent, exitLabel);
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
                    String nextTemp = MemoryControl.getFreeTemporal();
                    finalCode.add("li " + nextTemp + ",1" + "\n");
                    finalCode.add("beq " + firstVal + "," + nextTemp + "," + tmp.getGotoLabel() + "\n");
                    if (firstVal.charAt(0) == '$') {
                        if (!MemoryControl.freeTemporal.contains(firstVal) && firstVal.matches("\\$t\\d+")) {
                            MemoryControl.freeTemporal.push(firstVal);
                        }
                    }
                    if (!MemoryControl.freeTemporal.contains(nextTemp) && nextTemp.matches("\\$t\\d+")) {
                        MemoryControl.freeTemporal.push(nextTemp);
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
                    if (firstVal.charAt(0) == '$' && !MemoryControl.freeTemporal.contains(firstVal) && firstVal.matches("\\$t\\d+")) {
                        MemoryControl.freeTemporal.push(firstVal);
                    }
                    if (nextTemp.charAt(0) == '$' && !MemoryControl.freeTemporal.contains(nextTemp) && nextTemp.matches("\\$t\\d+")) {
                        MemoryControl.freeTemporal.push(nextTemp);
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
                if (firstVal.charAt(0) == '$' && !MemoryControl.freeTemporal.contains(firstVal) && firstVal.matches("\\$t\\d+")) {
                    MemoryControl.freeTemporal.push(firstVal);
                }
                if (secondVal.charAt(0) == '$' && !MemoryControl.freeTemporal.contains(secondVal) && secondVal.matches("\\$t\\d+")) {
                    MemoryControl.freeTemporal.push(secondVal);
                }
                /*if (tmp.getFirstValue().charAt(0) != '$') {
                    finalCode.add("sw " + toValue + ", _" + tmp.getFirstValue() + "\n");
                    if (!Temporal.freeTemporal.contains(toValue)) {
                        Temporal.freeTemporal.push(toValue);
                    }
                }*/
            } else if (operationCheck instanceof TwoOperation) {
                TwoOperation tmp = (TwoOperation) operationCheck;
                if (tmp.getFirstValue().charAt(0) != '$') {
                    String toVal = MemoryControl.mapFakeTemporalToReal.get(tmp.getSecondValue());
                    //Temporal.mapFakeTemporalToReal.remove(tmp.getSecondValue());
                    finalCode.add("sw " + toVal + ", _" + tmp.getFirstValue() + "\n");
                    if (!MemoryControl.freeTemporal.contains(toVal) && toVal.matches("\\$t\\d+")) {
                        MemoryControl.freeTemporal.push(toVal);
                    }
                } else {
                    //???
                    String tmpTemporal = getLoadTemporalValue(tmp.getSecondValue());
                    MemoryControl.mapFakeTemporalToReal.put(tmp.getFirstValue(), tmpTemporal);
                }
            } else if (operationCheck instanceof ParamOperation) {
                ParamOperation tmp = (ParamOperation) operationCheck;
                String temporalString = getLoadTemporalValue(tmp.getTemporal());
                String A = MemoryControl.getNextA();
                if (A.equalsIgnoreCase("NULL")) {
                    /* STACK MAHOU */
                } else {
                    finalCode.add("move " + A + "," + temporalString + "\n");
                }
            } else if (operationCheck instanceof CallOperation) {
                CallOperation tmp = (CallOperation) operationCheck;
                MemoryControl beforeT = new MemoryControl();
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
                
            }else if(operationCheck instanceof PutCall){
                PutCall tmp= (PutCall)(operationCheck);
                if(MemoryControl.MessageMap.containsKey(tmp.getMensaje())){
                    finalCode.add("li $v0, 4\n");
                    finalCode.add("la $a0, "+MemoryControl.MessageMap.get(tmp.getMensaje())+"\n");
                    finalCode.add("syscall\n");
                }else{
                    finalCode.add("li $v0, 4\n");
                    finalCode.add("la $a0, "+tmp.getMensaje()+"\n");
                    finalCode.add("syscall\n");
                }
            }else if(operationCheck instanceof GetCall){
                GetCall tmp=(GetCall)operationCheck;
                if(tmp.gettipo()==0){
                    finalCode.add("li $v0, 1\n");
                    finalCode.add("syscalls\n");
                    finalCode.add("sw $v0, "+tmp.getVariable()+"\n");
                }else{
                    finalCode.add("li $vo, 2\n");
                    finalCode.add("syscalls\n");
                    finalCode.add("sw $v0, "+tmp.getVariable()+"\n");
                }
            }
            generateMainProcedure();
        }
    }

    private String getLoadTemporalValue(String value) {
        if (value.equalsIgnoreCase("RET") || value.equalsIgnoreCase("_RET")) {
            String retVal = MemoryControl.getFreeTemporal();
            //RESTORE
            finalCode.add("move " + retVal + ",$v0" + "\n");
            return retVal;
        }
        if (value.charAt(0) == '$') {
            return MemoryControl.getTempValue(value);
        } else {
            String retVal = MemoryControl.getTempValue(value);
            if (isInteger(value)) {
                finalCode.add("li " + retVal + "," + value + "\n");
                if (MemoryControl.mapFakeTemporalToReal.containsKey(retVal)) {
                    MemoryControl.currentTemporal.remove(MemoryControl.mapFakeTemporalToReal.get(retVal));
                }
            } else {
                finalCode.add("lw " + retVal + ", _" + value + "\n");
            }
            return retVal;
        }
    }

    private String getLoadArgumentValue(String value) {
        if (value.equalsIgnoreCase("RET") || value.equalsIgnoreCase("_RET")) {
            String retVal = MemoryControl.getFreeTemporal();
            //RESTORE
            finalCode.add("move " + retVal + ",$v0" + "\n");
            return retVal;
        }
        if (value.charAt(0) == '$') {
            return MemoryControl.getTempValue(value);
        } else {
            String retVal = MemoryControl.getTempValue(value);
            if (isInteger(value)) {
                finalCode.add("li " + retVal + "," + value + "\n");
                if (MemoryControl.mapFakeTemporalToReal.containsKey(retVal)) {
                    MemoryControl.currentTemporal.remove(MemoryControl.mapFakeTemporalToReal.get(retVal));
                }
            } else if (MemoryControl.argumentToTemporal.containsKey(value)) {
                String val = MemoryControl.argumentToTemporal.get(value);
                if (MemoryControl.currentTemporal.containsKey(val)) {
                    return MemoryControl.currentTemporal.get(val);
                } else if (val.charAt(0) == '$') {
                    return val;
                } else {
                    String innerRetVal = MemoryControl.getTempValue(val);
                    finalCode.add("lw " + innerRetVal + "," + val + "\n");
                    return innerRetVal;
                }
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
