/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analizadorlexico.IntermediateCode;

import analizadorlexico.AST.Declaration.AsignationDeclaration;
import analizadorlexico.AST.Declaration.Declaration;
import analizadorlexico.AST.Declaration.FunctionDeclaration;
import analizadorlexico.AST.Declaration.InOutDeclaration;
import analizadorlexico.AST.Declaration.ListDeclarationParameter;
import analizadorlexico.AST.Declaration.ProcedureDeclaration;
import analizadorlexico.AST.Declaration.SequenceDeclaration;
import analizadorlexico.AST.Declaration.SimpleDeclaration;
import analizadorlexico.AST.Expression.AddExpression;
import analizadorlexico.AST.Expression.ConditionExpression;
import analizadorlexico.AST.Expression.Expression;
import analizadorlexico.AST.Expression.MultExpression;
import analizadorlexico.AST.Expression.PrimaryExpression;
import analizadorlexico.AST.Expression.RelationExpression;
import analizadorlexico.AST.InitProcedure;
import analizadorlexico.AST.Primary.FunctionCall;
import analizadorlexico.AST.Primary.ID;
import analizadorlexico.AST.Primary.LiteralBoolean;
import analizadorlexico.AST.Primary.LiteralFloat;
import analizadorlexico.AST.Primary.LiteralInt;
import analizadorlexico.AST.Primary.Primary;
import analizadorlexico.AST.Statement.AsignationStatement;
import analizadorlexico.AST.Statement.CaseStatement;
import analizadorlexico.AST.Statement.EmptyStatement;
import analizadorlexico.AST.Statement.ForStatement;
import analizadorlexico.AST.Statement.FunctionCallStatement;
import analizadorlexico.AST.Statement.IfStatement;
import analizadorlexico.AST.Statement.ReturnStatement;
import analizadorlexico.AST.Statement.SequenceStatement;
import analizadorlexico.AST.Statement.Statement;
import analizadorlexico.AST.Statement.WhileStatement;
import analizadorlexico.SemanticAnalysis.SemanticAnalysis;
import static analizadorlexico.SemanticAnalysis.SemanticAnalysis.hasError;
import analizadorlexico.SymbolTable.ComplexNode;
import analizadorlexico.SymbolTable.ListComplexNode;
import analizadorlexico.SymbolTable.Node;
import analizadorlexico.SymbolTable.SimpleNode;
import analizadorlexico.TypeCheck.BooleanType;
import analizadorlexico.TypeCheck.ErrorType;
import analizadorlexico.TypeCheck.FloatType;
import analizadorlexico.TypeCheck.IntType;
import analizadorlexico.TypeCheck.Type;
import analizadorlexico.TypeCheck.VoidType;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Kevin Barahona
 */
public class IntermediateCode {

    private List<Operation> codeOperations;
    private SemanticAnalysis semanticAnalysis;
    private InitProcedure treeRoot;

    public IntermediateCode(InitProcedure IP, SemanticAnalysis sm) {
        codeOperations = new ArrayList<>();
        this.semanticAnalysis = sm;
        this.treeRoot = IP;
    }

    public List<Operation> getCodeOperations() {
        return codeOperations;
    }

    public void setCodeOperations(List<Operation> codeOperations) {
        this.codeOperations = codeOperations;
    }

    private void generateThreeOperation(String toValue, String firstOperation, String secondOperation, String typeOperation) {
        // t1 = t2 + 5
        /*
        t1 == toValue
        t2 == firstOperation
        5 == secondOperation
        + = typeOperation
         */
        ThreeOperation newOp = new ThreeOperation(toValue, firstOperation, secondOperation, "=", typeOperation);
        codeOperations.add(newOp);
    }

    private void generateCall(String toValue, int paramnum) {
        CallOperation tmp = new CallOperation(toValue, paramnum);
        codeOperations.add(tmp);
    }

    private void generateParamOperation(String toValue) {
        ParamOperation tmp = new ParamOperation(toValue);
        codeOperations.add(tmp);
    }

    private void generateTwoOperation(String toValue, String firstOperation) {
        TwoOperation tmp = new TwoOperation(toValue, firstOperation, "=");
        codeOperations.add(tmp);
    }
    
    private void generateReturn(String toRet){
        ReturnOperation tmp = new ReturnOperation(toRet);
        codeOperations.add(tmp);
    }

    private void generateLabelOperation(String label) {
        //LABEL:
        LabelOperation newOp = new LabelOperation(label);
        codeOperations.add(newOp);
    }

    private void generateIfOperation(String firstValue, String secondValue, String compareOperator, String gotoLabel) {
        IfOperation newOp = new IfOperation(firstValue, secondValue, compareOperator, gotoLabel);
        codeOperations.add(newOp);
    }

    private void generateGotoOperation(String label) {
        GotoOperation newOp = new GotoOperation(label);
        codeOperations.add(newOp);
    }

    public void generate() {
        generateDeclaration(treeRoot.getDec(), null);
        //generateDeclarationOnly(treeRoot.getDec());
        generateLabelOperation("main");
        generateStatement(treeRoot.getDec(), treeRoot.getStat(), null);
    }

    public String getStringRepresentation() {
        StringBuilder SB = new StringBuilder();
        for (Operation tmp : codeOperations) {
            SB.append(tmp.getStringValue());
            SB.append('\n');
        }
        return SB.toString();
    }

    private void generateDeclaration(Declaration Dec, ComplexNode Parent) {
        Declaration declarationCheck = Dec;
        Declaration nextCheck = null;
        if (Dec instanceof SequenceDeclaration) {
            declarationCheck = ((SequenceDeclaration) Dec).getThisDeclaration();
            nextCheck = ((SequenceDeclaration) Dec).getNextDeclarations();
        } 
        
        if (declarationCheck instanceof ProcedureDeclaration) {
            ProcedureDeclaration tmp = (ProcedureDeclaration) declarationCheck;
            generateDeclaration(tmp.getDec(), null);
            int size = tmp.getParamsType(tmp.getLDP(), new ArrayList<>()).size();
            generateLabelOperation("_" + tmp.getId() + "V" + size);
            generateStatement(tmp.getStat(), null);
        } else if (declarationCheck instanceof FunctionDeclaration) {
            FunctionDeclaration tmp = (FunctionDeclaration) declarationCheck;
            generateDeclaration(tmp.getDec(), null);
            int size = tmp.getParamsType(tmp.getLDP(), new ArrayList<>()).size();
            generateLabelOperation("_" + tmp.getId() + tmp.getRetType().getTYPE().charAt(0) + size);
            generateStatement(tmp.getStat(), null);
        }

        if (nextCheck != null) {
            generateDeclaration(nextCheck, Parent);
        }
    }

    private void generateStatement(Statement Stat, ComplexNode Parent) {

        Statement checkNext = null;
        Statement thisStatement = Stat;
        if (Stat instanceof SequenceStatement) {
            SequenceStatement tmp = (SequenceStatement) thisStatement;
            checkNext = tmp.getNextSequenceStatement();
            thisStatement = tmp.getThisStatement();
        }

        if (thisStatement instanceof AsignationStatement) {
            AsignationStatement tmp = (AsignationStatement) thisStatement;
            Temporal T = generateExpression(tmp.getExp(), Parent);
            generateTwoOperation(tmp.getID(), T.toString());
        } else if (thisStatement instanceof CaseStatement) {
            //TODO:
        } else if (thisStatement instanceof ForStatement) {
            ForStatement tmp = (ForStatement) thisStatement;
            Temporal asigTemporal = generateExpression(tmp.getAsig().getExp(), Parent);
            generateTwoOperation(tmp.getAsig().getID(), asigTemporal.toString());
            Label checkExpression = new Label();
            Label trueLabel = new Label();
            Label nextLabel = new Label();

            generateLabelOperation(checkExpression.toString());
            Temporal expressionTemporal = generateExpression(tmp.getExp(), Parent);

            generateIfOperation(asigTemporal.toString(), expressionTemporal.toString(), "<", trueLabel.toString());
            generateGotoOperation(nextLabel.toString());

            generateLabelOperation(trueLabel.toString());
            generateStatement(tmp.getStat(), Parent);
            generateThreeOperation(asigTemporal.toString(), asigTemporal.toString(), "1", "+");
            generateGotoOperation(checkExpression.toString());

            generateLabelOperation(nextLabel.toString());

        } else if (thisStatement instanceof FunctionCallStatement) {
            FunctionCallStatement tmp = (FunctionCallStatement) thisStatement;
            Temporal temp = generatePrimary(tmp.getCall(), Parent);
            
        } else if (thisStatement instanceof IfStatement) {
            IfStatement tmp = (IfStatement) thisStatement;
            Label nextLabel = new Label();
            Label trueLabel = new Label();
            Label falseLabel = new Label();
            Expression toCheckExp = tmp.getCon().getExp();
            if (toCheckExp instanceof ConditionExpression) {
                generateConditionCode((ConditionExpression) toCheckExp, trueLabel, falseLabel);
            } else {
                Temporal temporal = generateExpression(toCheckExp, Parent);
                generateIfOperation(temporal.toString(), "", "", trueLabel.toString());
                generateGotoOperation(falseLabel.toString());
            }
            generateLabelOperation(trueLabel.toString());
            generateStatement(tmp.getStat(), Parent);
            generateGotoOperation(nextLabel.toString());
            generateLabelOperation(falseLabel.toString());
            generateStatement(tmp.getElsIf(), Parent);
            generateLabelOperation(nextLabel.toString());

        } else if (thisStatement instanceof WhileStatement) {
            WhileStatement tmp = (WhileStatement) thisStatement;
            Expression toCheckExp = tmp.getCon().getExp();
            Label nextLabel = new Label();
            Label trueLabel = new Label();
            if (toCheckExp instanceof ConditionExpression) {
                generateConditionCode((ConditionExpression) toCheckExp, trueLabel, nextLabel);
            } else {
                Temporal temporal = generateExpression(toCheckExp, Parent);
                generateIfOperation(temporal.toString(), "", "", trueLabel.toString());
                generateGotoOperation(nextLabel.toString());
            }
            generateLabelOperation(trueLabel.toString());
            generateStatement(tmp.getStat(), Parent);
            generateLabelOperation(nextLabel.toString());
        } else if (thisStatement instanceof ReturnStatement){
            ReturnStatement tmp = (ReturnStatement)thisStatement;
            Temporal tmpTemp = generateExpression(tmp.getRetVal(),null);
            generateReturn(tmpTemp.toString());
        }

        if (checkNext != null || checkNext instanceof EmptyStatement) {
            generateStatement(checkNext, Parent);
        }

    }

    private void generateStatement(Declaration Dec, Statement Stat, ComplexNode Parent) {

        Declaration declarationCheck = Dec;
        Declaration nextCheck = null;
        if (Stat != null) {
            generateStatement(Stat, Parent);
        }

        if (Dec instanceof SequenceDeclaration) {
            declarationCheck = ((SequenceDeclaration) Dec).getThisDeclaration();
            nextCheck = ((SequenceDeclaration) Dec).getNextDeclarations();
        }
        if (declarationCheck instanceof FunctionDeclaration) {
            FunctionDeclaration tmp = (FunctionDeclaration) declarationCheck;
        } else if (declarationCheck instanceof ProcedureDeclaration) {
            ProcedureDeclaration tmp = (ProcedureDeclaration) declarationCheck;
        }

        if (nextCheck != null) {
            generateStatement(nextCheck, null, Parent);
        }
    }

    private Temporal generateExpression(Expression Exp, ComplexNode Parent) {
        if (Exp instanceof AddExpression) {
            AddExpression tmp = (AddExpression) Exp;
            Temporal firstTemp;
            Temporal secondTemp;
            if (tmp.getExp1() instanceof PrimaryExpression) {
                PrimaryExpression primaryTemp = (PrimaryExpression) tmp.getExp1();
                firstTemp = new Temporal(getPrimary(primaryTemp.getValue()));
            } else {
                firstTemp = generateExpression(tmp.getExp1(), Parent);
            }
            if (tmp.getExp2() instanceof PrimaryExpression) {
                PrimaryExpression primaryTemp = (PrimaryExpression) tmp.getExp2();
                secondTemp = new Temporal(getPrimary(primaryTemp.getValue()));
            } else {
                secondTemp = generateExpression(tmp.getExp2(), Parent);
            }
            Temporal retVal = new Temporal();
            generateThreeOperation(retVal.toString(), firstTemp.toString(), secondTemp.toString(), tmp.getOperator());
            return retVal;

        } else if (Exp instanceof MultExpression) {
            MultExpression tmp = (MultExpression) Exp;
            Temporal firstTemp;
            Temporal secondTemp;
            if (tmp.getExp1() instanceof PrimaryExpression) {
                PrimaryExpression primaryTemp = (PrimaryExpression) tmp.getExp1();
                firstTemp = new Temporal(getPrimary(primaryTemp.getValue()));
            } else {
                firstTemp = generateExpression(tmp.getExp1(), Parent);
            }
            if (tmp.getExp2() instanceof PrimaryExpression) {
                PrimaryExpression primaryTemp = (PrimaryExpression) tmp.getExp2();
                secondTemp = new Temporal(getPrimary(primaryTemp.getValue()));
            } else {
                secondTemp = generateExpression(tmp.getExp2(), Parent);
            }
            Temporal retVal = new Temporal();
            generateThreeOperation(retVal.toString(), firstTemp.toString(), secondTemp.toString(), tmp.getOperator());
            return retVal;

        } else if (Exp instanceof ConditionExpression) {
            Label trueLabel = new Label();
            Label falseLabel = new Label();
            generateConditionCode((ConditionExpression) Exp, trueLabel, falseLabel);
            generateLabelOperation(trueLabel.toString());
            Temporal retVal = new Temporal();
            generateTwoOperation(retVal.toString(), "true");
            generateLabelOperation(falseLabel.toString());
            generateTwoOperation(retVal.toString(), "false");
            return retVal;

        } else if (Exp instanceof RelationExpression) {
            RelationExpression tmp = (RelationExpression) Exp;
            Temporal firstTemp;
            Temporal secondTemp;
            if (tmp.getExp1() instanceof PrimaryExpression) {
                PrimaryExpression primaryTemp = (PrimaryExpression) tmp.getExp1();
                firstTemp = new Temporal(getPrimary(primaryTemp.getValue()));
            } else {
                firstTemp = generateExpression(tmp.getExp1(), Parent);
            }
            if (tmp.getExp2() instanceof PrimaryExpression) {
                PrimaryExpression primaryTemp = (PrimaryExpression) tmp.getExp2();
                secondTemp = new Temporal(getPrimary(primaryTemp.getValue()));
            } else {
                secondTemp = generateExpression(tmp.getExp2(), Parent);
            }
            Temporal retVal = new Temporal();
            generateThreeOperation(retVal.toString(), firstTemp.toString(), secondTemp.toString(), tmp.getRelationOperator());
            return retVal;
            //???????????????????????????????????////

        } else if (Exp instanceof PrimaryExpression) {
            PrimaryExpression tmp = (PrimaryExpression) Exp;
            Temporal retVal = new Temporal();
            Temporal primaryVal = generatePrimary(tmp.getValue(), Parent);
            generateTwoOperation(retVal.toString(), primaryVal.toString());
            return retVal;
        }

        return null;
    }

    private Temporal generatePrimary(Primary Prim, ComplexNode Parent) {
        if (Prim instanceof FunctionCall) {
            FunctionCall tmp = (FunctionCall) Prim;
            for (Primary tmpPrimary : tmp.getParams().getValues()) {
                generateParamOperation(getPrimary(tmpPrimary));
            }
            generateCall(tmp.getID(), tmp.getParams().getValues().size());
            return new Temporal("RET");
        } else if (Prim instanceof ID) {
            ID tmp = (ID) Prim;
            return new Temporal(tmp.getID());
        } else if (Prim instanceof LiteralBoolean) {
            LiteralBoolean tmp = (LiteralBoolean) Prim;
            return new Temporal(Boolean.toString(tmp.isValue()));
        } else if (Prim instanceof LiteralFloat) {

            LiteralFloat tmp = (LiteralFloat) Prim;
            return new Temporal(Float.toString(tmp.getValue()));
        } else if (Prim instanceof LiteralInt) {

            LiteralInt tmp = (LiteralInt) Prim;
            return new Temporal(Integer.toString(tmp.getValue()));
        }
        return null;
    }

    private String getPrimary(Primary Prim) {
        if (Prim instanceof FunctionCall) {
            FunctionCall tmp = (FunctionCall) Prim;
            for (Primary tmpPrimary : tmp.getParams().getValues()) {
                generateParamOperation(getPrimary(tmpPrimary));
            }
            generateCall(tmp.getID(), tmp.getParams().getValues().size());
            return "RET";
        } else if (Prim instanceof ID) {

            ID tmp = (ID) Prim;
            return tmp.getID();
        } else if (Prim instanceof LiteralBoolean) {
            LiteralBoolean tmp = (LiteralBoolean) Prim;
            return (Boolean.toString(tmp.isValue()));
        } else if (Prim instanceof LiteralFloat) {

            LiteralFloat tmp = (LiteralFloat) Prim;
            return (Float.toString(tmp.getValue()));
        } else if (Prim instanceof LiteralInt) {

            LiteralInt tmp = (LiteralInt) Prim;
            return (Integer.toString(tmp.getValue()));
        }
        return null;
    }

    private void generateConditionCode(ConditionExpression element, Label trueLabel, Label falseLabel) {
        if (element.getConditionOperator().equalsIgnoreCase("and")) {
            Label nextFirstCheck = new Label();
            if (element.getExp1() instanceof ConditionExpression) {
                generateConditionCode((ConditionExpression) element.getExp1(), nextFirstCheck, falseLabel);
            } else {
                generateRelationCode(element.getExp1(), nextFirstCheck, falseLabel);
            }
            generateLabelOperation(nextFirstCheck.toString());

            Label nextSecondCheck = new Label();
            if (element.getExp2() instanceof ConditionExpression) {
                generateConditionCode((ConditionExpression) element.getExp2(), nextSecondCheck, falseLabel);
                generateLabelOperation(nextSecondCheck.toString());
            } else {
                generateRelationCode(element.getExp2(), trueLabel, falseLabel);
            }

        } else if (element.getConditionOperator().equalsIgnoreCase("or")) {
            Label nextFirstCheck = new Label();
            if (element.getExp1() instanceof ConditionExpression) {
                generateConditionCode((ConditionExpression) element.getExp1(), trueLabel, nextFirstCheck);
            } else {
                generateRelationCode(element.getExp1(), trueLabel, nextFirstCheck);
            }
            generateLabelOperation(nextFirstCheck.toString());

            Label nextSecondCheck = new Label();
            if (element.getExp2() instanceof ConditionExpression) {
                generateConditionCode((ConditionExpression) element.getExp2(), trueLabel, nextSecondCheck);
                generateLabelOperation(nextSecondCheck.toString());
            } else {
                generateRelationCode(element.getExp2(), trueLabel, falseLabel);
            }

        }
    }

    private void generateRelationCode(Expression element, Label trueLabel, Label falseLabel) {
        Temporal tOne;
        Temporal tTwo;
        String compareType = "";
        if (element instanceof PrimaryExpression) {
            PrimaryExpression tmp = (PrimaryExpression) element;
            tOne = new Temporal(getPrimary(tmp.getValue()));
            tTwo = new Temporal("");
        } else if (element instanceof RelationExpression) {
            RelationExpression tmp = (RelationExpression) element;
            compareType = tmp.getRelationOperator();
            if (tmp.getExp1() instanceof PrimaryExpression) {
                PrimaryExpression tmpRelation = (PrimaryExpression) tmp.getExp1();
                tOne = new Temporal(getPrimary(tmpRelation.getValue()));
            } else {
                tOne = generateExpression(tmp.getExp1(), null);
            }

            if (tmp.getExp2() instanceof PrimaryExpression) {
                PrimaryExpression tmpRelation = (PrimaryExpression) tmp.getExp2();
                tTwo = new Temporal(getPrimary(tmpRelation.getValue()));
            } else {
                tTwo = generateExpression(tmp.getExp2(), null);
            }
        } else {
            System.out.println("WUT");
            return;
        }

        generateIfOperation(tOne.toString(), tTwo.toString(), compareType, trueLabel.toString());
        generateGotoOperation(falseLabel.toString());

    }

    public Operation getNextOperation() {
        Operation retVal;
        //System.out.println(codeOperations.size() + " LENGH");
        if (codeOperations.size() > 0) {
            retVal = codeOperations.get(0);
            codeOperations.remove(0);
            return retVal;
        }
        return null;
    }
}
