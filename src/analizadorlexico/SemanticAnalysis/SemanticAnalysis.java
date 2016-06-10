/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analizadorlexico.SemanticAnalysis;

import analizadorlexico.AST.Declaration.AsignationDeclaration;
import analizadorlexico.AST.Declaration.Declaration;
import analizadorlexico.AST.Declaration.EmptyDeclaration;
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
import analizadorlexico.IntermediateCode.IntermediateCode;
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
public final class SemanticAnalysis {

    private ComplexNode root;
    private ComplexNode currentScope;
   
    public static boolean hasError = false;


    public SemanticAnalysis(InitProcedure Proc) {
        this.root = new ComplexNode(Proc.getBeginId(), new VoidType(), new ArrayList<>());
        checkDeclaration(Proc.getDec(), root);
        System.out.println("");
        checkStatement(Proc.getDec(), Proc.getStat(), root);
    }

    public ComplexNode getRoot() {
        return root;
    }

    public void setRoot(ComplexNode root) {
        this.root = root;
    }

    public void checkDeclaration(Declaration Dec, ComplexNode Parent) {
        Declaration declarationCheck = Dec;
        Declaration nextCheck = null;
        if (Dec instanceof SequenceDeclaration) {
            declarationCheck = ((SequenceDeclaration) Dec).getThisDeclaration();
            nextCheck = ((SequenceDeclaration) Dec).getNextDeclarations();
        }
        System.out.println(declarationCheck.getClass().toString());
        if (declarationCheck instanceof AsignationDeclaration) {
            SimpleDeclaration simple = (SimpleDeclaration) ((AsignationDeclaration) declarationCheck).getSimpleDeclaration();
            for (int i = 0; i < simple.getIDs().size(); i++) {
                if (!Parent.addHijo(simple.getIDs().get(i), new SimpleNode(simple.getIDs().get(i), simple.getType()))) {
                    hasError = true;
                    System.err.println(simple.getIDs().get(i) + ": already exists, duplicated value");
                }
            }
        } else if (declarationCheck instanceof SimpleDeclaration) {
            SimpleDeclaration simple = (SimpleDeclaration) declarationCheck;
            for (int i = 0; i < simple.getIDs().size(); i++) {
                if (!Parent.addHijo(simple.getIDs().get(i), new SimpleNode(simple.getIDs().get(i), simple.getType()))) {
                    hasError = true;
                    System.err.println(simple.getIDs().get(i) + ": already exists, duplicated value");
                }
            }
        } else if (declarationCheck instanceof ProcedureDeclaration) {
            ProcedureDeclaration tmp = (ProcedureDeclaration) declarationCheck;
            //CHANGE NULL ON FINAL
            ComplexNode newScope = new ComplexNode(tmp.getId(), new VoidType(), new ArrayList<>(), Parent);
            checkParametersFunction(tmp.getLDP(), newScope);

            if (Parent.getHijos().get(tmp.getId()) == null) {
                ListComplexNode tmpList = new ListComplexNode(tmp.getId(), new ArrayList());
                tmpList.addFunction(newScope);
                if (!Parent.addHijo(tmp.getId(), tmpList)) {
                    hasError = true;
                    System.err.println(tmp.getId() + ": already exists, duplicated value");
                }
            } else {
                Node keyNode = Parent.getHijos().get(tmp.getId());
                if (keyNode instanceof ListComplexNode) {
                    if (!((ListComplexNode) keyNode).addFunction(newScope)) {
                        hasError = true;
                        System.err.println(tmp.getId() + ": already exists, duplicated value");
                    }
                } else {
                    hasError = true;
                    System.err.println(tmp.getId() + ": already exists, duplicated value");
                }
            }
            System.out.println("");
            System.out.println("Checking Declarations On: " + tmp.getId());
            checkDeclaration(tmp.getDec(), newScope);
            System.out.println("Finish Declarations On: " + tmp.getId());
            System.out.println();
        } else if (declarationCheck instanceof FunctionDeclaration) {
        System.out.println("Es un FunctionDeclaration");
            FunctionDeclaration tmp = (FunctionDeclaration) declarationCheck;
            //CHANGE NULL ON FINAL
            ComplexNode newScope = new ComplexNode(tmp.getId(), tmp.getRetType(), new ArrayList<>(), Parent);
            checkParametersFunction(tmp.getLDP(), newScope);
            if (Parent.getHijos().get(tmp.getId()) == null) {
                ListComplexNode tmpList = new ListComplexNode(tmp.getId(), new ArrayList());
                tmpList.addFunction(newScope);
                if (!Parent.addHijo(tmp.getId(), tmpList)) {
                    hasError = true;
                    System.err.println(tmp.getId() + ": already exists, duplicated value");
                }
            } else {
                Node keyNode = Parent.getHijos().get(tmp.getId());
                if (keyNode instanceof ListComplexNode) {
                    if (((ListComplexNode) keyNode).addFunction(newScope)) {
                        hasError = true;
                        System.err.println(tmp.getId() + ": already exists, duplicated value");
                    }
                } else {
                    hasError = true;
                    System.err.println(tmp.getId() + ": already exists, duplicated value");
                }
            }
            System.out.println("");
            System.out.println("Checking Declarations On: " + tmp.getId());
            checkDeclaration(tmp.getDec(), newScope);
            System.out.println("Finish Declarations On: " + tmp.getId());
            System.out.println("");
        }

        if (nextCheck != null) {
            checkDeclaration(nextCheck, Parent);
        }
    }

    private void checkParametersFunction(ListDeclarationParameter List, ComplexNode function) {
        Declaration declarationCheck = List.getDec();
        ListDeclarationParameter nextCheck = List.getLDP();
        if (declarationCheck instanceof AsignationDeclaration) {
            SimpleDeclaration simple = (SimpleDeclaration) ((AsignationDeclaration) declarationCheck).getSimpleDeclaration();
            for (int i = 0; i < simple.getIDs().size(); i++) {
                function.addParameter(simple.getType());
                function.addHijo(simple.getIDs().get(i), new SimpleNode(simple.getIDs().get(i), simple.getType()));
            }
        } else if (declarationCheck instanceof InOutDeclaration) {
            InOutDeclaration tmp = (InOutDeclaration) declarationCheck;
            function.addParameter(tmp.getType());
            function.addHijo(tmp.getId(), new SimpleNode(tmp.getId(), tmp.getType()));
        }
        if (nextCheck != null) {
            checkParametersFunction(nextCheck, function);
        }
    }

    private void checkStatement(Statement Stat, ComplexNode Parent) {

        Statement checkNext = null;
        Statement thisStatement = Stat;
        if (Stat instanceof SequenceStatement) {
            SequenceStatement tmp = (SequenceStatement) thisStatement;
            checkNext = tmp.getNextSequenceStatement();
            thisStatement = tmp.getThisStatement();
        }
        
        if (thisStatement != null){
            System.out.println(thisStatement.getClass().toString());
        }
        if (thisStatement instanceof AsignationStatement) {
            AsignationStatement tmp = (AsignationStatement) thisStatement;
            Type firstType = Parent.searchTypeById(tmp.getID());
            if(tmp.getExp() instanceof PrimaryExpression){
                PrimaryExpression tmpprimary = (PrimaryExpression)tmp.getExp();
                if(tmpprimary.getValue() instanceof FunctionCall){
                    System.out.println("TmpDebug Proval:"+tmpprimary.getValue().getStringType());
                    List<Type> rettypes= getReturnValues(((FunctionCall)tmpprimary.getValue()), Parent);
                    for (int i = 0; i < rettypes.size(); i++) {
                        if(!firstType.compare(rettypes.get(i))){
                            hasError = true;
                            System.err.println("Invalid Type operation on: ");
                            break;
                        }
                    }
                }else{
                    Type secondType = getExpressionType(tmp.getExp(), Parent);
                    if (!firstType.compare(secondType)) {
                        hasError = true;
                        System.err.println("Invalid Type operation on: ");
                    }
                }
            }else{
            Type secondType = getExpressionType(tmp.getExp(), Parent);
            if (!firstType.compare(secondType)) {
                hasError = true;
                System.err.println("Invalid Type operation on: ");
            }
            }
            
        } else if (thisStatement instanceof CaseStatement) {
            //TODO:
        } else if (thisStatement instanceof ForStatement) {
            ForStatement tmp = (ForStatement) thisStatement;
            checkStatement(tmp.getAsig(), Parent);
            checkStatement(tmp.getStat(), Parent);
            Type AsigType = Parent.searchTypeById(tmp.getAsig().getID());
            Type ExpType = this.getExpressionType(tmp.getExp(), Parent);
            if (!AsigType.compare(ExpType) || ExpType == null) {
                hasError = true;
                System.err.println("Invalid Type operation on: ");
            }
        } else if (thisStatement instanceof FunctionCallStatement) {
            getPrimaryType(((FunctionCallStatement) thisStatement).getCall(), Parent);
        } else if (thisStatement instanceof IfStatement) {
            IfStatement tmp = (IfStatement) thisStatement;
            checkStatement(tmp.getStat(), Parent);
            Type tmpType = this.getExpressionType(tmp.getCon().getExp(), Parent);
            if (tmpType == null || tmpType instanceof IntType) {
                hasError = true;
                System.err.println("Invalid Type operation on: ");
            }
            checkStatement(tmp.getElsIf(), Parent);
        } else if (thisStatement instanceof WhileStatement) {
            WhileStatement tmp = (WhileStatement) thisStatement;
            checkStatement(tmp.getStat(), Parent);
            Type ExpType = this.getExpressionType(tmp.getCon().getExp(), Parent);
            if (ExpType == null || ExpType instanceof IntType) {
                hasError = true;
                System.err.println("Invalid Type operation on: ");
            }
        }

        if (checkNext != null || checkNext instanceof EmptyStatement) {
            checkStatement(checkNext, Parent);
        }

    }

    private void checkStatement(Declaration Dec, Statement Stat, ComplexNode Parent) {

        Declaration declarationCheck = Dec;
        Declaration nextCheck = null;
        if (Stat != null) {
            System.out.println("");
            System.out.println("Staring CheckStatement On: " + Parent.getId());
            checkStatement(Stat, Parent);
            System.out.println("Ending CheckStatement On:  " + Parent.getId());
            System.out.println("");
        }
        if (Dec instanceof SequenceDeclaration) {
            declarationCheck = ((SequenceDeclaration) Dec).getThisDeclaration();
            nextCheck = ((SequenceDeclaration) Dec).getNextDeclarations();
        }
        if (declarationCheck instanceof FunctionDeclaration) {
            FunctionDeclaration tmp = (FunctionDeclaration) declarationCheck;
            List<Type> checkParams = tmp.getParamsType(tmp.getLDP(), new ArrayList());
            ComplexNode tmpParent = (ComplexNode) Parent.searchFunctionNodeById(tmp.getId(), checkParams);
            System.out.println("");
            System.out.println("Entering Function: " + tmpParent.getId());
            checkStatement(tmp.getDec(), tmp.getStat(), tmpParent);
            System.out.println("Exit Function " + tmpParent.getId());
            System.out.println("");
        } else if (declarationCheck instanceof ProcedureDeclaration) {
            FunctionDeclaration tmp = (FunctionDeclaration) declarationCheck;
            List<Type> checkParams = tmp.getParamsType(tmp.getLDP(), new ArrayList());
            System.out.println("");
            ComplexNode tmpParent = (ComplexNode) Parent.searchFunctionNodeById(tmp.getId(), checkParams);
            System.out.println("Entering Procedure: " + tmpParent.getId());
            checkStatement(tmp.getDec(), tmp.getStat(), tmpParent);
            System.out.println("Exit Function " + tmpParent.getId());
            System.out.println("");
        }

        if (nextCheck != null) {
            checkStatement(nextCheck, null, Parent);
        }
    }

    private Type getExpressionType(Expression Exp, ComplexNode Parent) {
        System.out.println(Exp.getClass().toString());
        if (Exp instanceof AddExpression) {
            AddExpression tmp = (AddExpression) Exp;
            Type firstType = getExpressionType(tmp.getExp1(), Parent);
            Type secondType = getExpressionType(tmp.getExp2(), Parent);
            //AND INT, FLOAT TYPE?
            if (firstType.compare(secondType)) {
                return firstType;
            } else {
                //CHANGE NULL TO ERRORTYPE
                hasError = true;
                System.err.println("Invalid Type operation on: ");
                return firstType;
            }
        } else if (Exp instanceof MultExpression) {
            MultExpression tmp = (MultExpression) Exp;
            Type firstType = getExpressionType(tmp.getExp1(), Parent);
            Type secondType = getExpressionType(tmp.getExp2(), Parent);
            //ADD INT, FLOAT CHECK?
            if (firstType.compare(secondType)) {
                return firstType;
            } else {
                //CHANGE NULL TO ERRORTYPE
                hasError = true;
                System.err.println("Invalid Type operation on: ");
                return firstType;
            }
        } else if (Exp instanceof ConditionExpression) {
            ConditionExpression tmp = (ConditionExpression) Exp;
            Type firstType = getExpressionType(tmp.getExp1(), Parent);
            Type secondType = getExpressionType(tmp.getExp2(), Parent);
            //ADD BOOLEAN CHECK?
            if (firstType.compare(secondType)) {
                return firstType;
            } else {
                //CHANGE NULL TO ERRORTYPE
                hasError = true;
                System.err.println("Invalid Type operation on: ");
                return firstType;
            }
        } else if (Exp instanceof RelationExpression) {
            RelationExpression tmp = (RelationExpression) Exp;
            Type firstType = getExpressionType(tmp.getExp1(), Parent);
            Type secondType = getExpressionType(tmp.getExp2(), Parent);
            //ADD BOOLEAN CHECK?
            if (firstType.compare(secondType)) {
                return firstType;
            } else {
                //CHANGE NULL TO ERRORTYPE
                hasError = true;
                System.err.println("Invalid Type operation on: ");
                return firstType;
            }
        } else if (Exp instanceof PrimaryExpression) {
            return getPrimaryType(((PrimaryExpression) Exp).getValue(), Parent);
        }
        //CHANGE NULL TO ERRORTYPE
        return new ErrorType();
    }
    private List<Type> getReturnValues(FunctionCall tmp, ComplexNode Parent){
            List<Type> retval = new ArrayList();
            List<Type> tmpParams = new ArrayList();
            for (int i = 0; i < tmp.getParams().getValues().size(); i++) {
                tmpParams.add(getPrimaryType(tmp.getParams().getValues().get(i), Parent));
            }
            List<Node> nodes= Parent.searchFunctionNodesById(tmp.getID(), tmpParams);
            for (int i = 0; i < nodes.size(); i++) {
                retval.add(((ComplexNode)nodes).getRetType());
            }
            if(retval.size()>0){
            return retval;
            }else{
                retval.add(new ErrorType());
                return retval;
            }
    }
    private Type getPrimaryType(Primary Prim, ComplexNode Parent) {
        System.out.println(Prim.getClass().toString());
        if (Prim instanceof FunctionCall) {
            FunctionCall tmp = (FunctionCall) Prim;
            List<Type> tmpParams = new ArrayList();
            for (int i = 0; i < tmp.getParams().getValues().size(); i++) {
                tmpParams.add(getPrimaryType(tmp.getParams().getValues().get(i), Parent));
            }
            return Parent.searchFunctionNodeTypeById(tmp.getID(), tmpParams);
        } else if (Prim instanceof ID) {
            //System.out.println("id");
            ID tmp = (ID) Prim;
            return Parent.searchTypeById(tmp.getID());
        } else if (Prim instanceof LiteralBoolean) {
            return new BooleanType();
        } else if (Prim instanceof LiteralFloat) {
            return new FloatType();
        } else if (Prim instanceof LiteralInt) {
            return new IntType();
        }
        return new ErrorType();
    }
}
