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

    private void generateTwoOperation(String toValue, String firstOperation) {
        TwoOperation tmp = new TwoOperation(toValue, firstOperation, "=");
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

        if (declarationCheck instanceof AsignationDeclaration) {
            SimpleDeclaration simple = (SimpleDeclaration) ((AsignationDeclaration) declarationCheck).getSimpleDeclaration();
            for (int i = 0; i < simple.getIDs().size(); i++) {

            }
        } else if (declarationCheck instanceof SimpleDeclaration) {
            SimpleDeclaration simple = (SimpleDeclaration) declarationCheck;
            for (int i = 0; i < simple.getIDs().size(); i++) {
            }
        } else if (declarationCheck instanceof ProcedureDeclaration) {
            ProcedureDeclaration tmp = (ProcedureDeclaration) declarationCheck;

        } else if (declarationCheck instanceof FunctionDeclaration) {
            FunctionDeclaration tmp = (FunctionDeclaration) declarationCheck;
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
        } else if (thisStatement instanceof FunctionCallStatement) {

        } else if (thisStatement instanceof IfStatement) {
            IfStatement tmp = (IfStatement) thisStatement;

        } else if (thisStatement instanceof WhileStatement) {
            WhileStatement tmp = (WhileStatement) thisStatement;
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
            FunctionDeclaration tmp = (FunctionDeclaration) declarationCheck;
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
            ConditionExpression tmp = (ConditionExpression) Exp;
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
            generateThreeOperation(retVal.toString(), firstTemp.toString(), secondTemp.toString(), tmp.getConditionOperator());
            return retVal;
            //?????????????????????????????

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
}
