/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analizadorlexico.SemanticAnalysis;

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
import analizadorlexico.AST.Statement.ForStatement;
import analizadorlexico.AST.Statement.FunctionCallStatement;
import analizadorlexico.AST.Statement.IfStatement;
import analizadorlexico.AST.Statement.Statement;
import analizadorlexico.SymbolTable.FunctionNode;
import analizadorlexico.SymbolTable.ListFunctionNode;
import analizadorlexico.SymbolTable.Node;
import analizadorlexico.SymbolTable.SimpleNode;
import analizadorlexico.TypeCheck.BooleanType;
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
public class SemanticAnalysis {

    private FunctionNode root;
    private FunctionNode currentScope;
    public static boolean hasError = false;

    public SemanticAnalysis(String id, Type tipo) {
        this.root = new FunctionNode(id, tipo, new ArrayList<>());
    }

    public SemanticAnalysis(InitProcedure Proc) {
        this.root = new FunctionNode(Proc.getBeginId(), new VoidType(), new ArrayList<>());
        checkDeclaration(Proc.getDec(), root);
    }

    public FunctionNode getRoot() {
        return root;
    }

    public void setRoot(FunctionNode root) {
        this.root = root;
    }

    public void checkDeclaration(Declaration Dec, FunctionNode Parent) {
        Declaration declarationCheck = Dec;
        Declaration nextCheck = null;
        if (Dec instanceof SequenceDeclaration) {
            declarationCheck = ((SequenceDeclaration) Dec).getThisDeclaration();
            nextCheck = ((SequenceDeclaration) Dec).getNextDeclarations();
        }
        if (declarationCheck instanceof AsignationDeclaration) {
            SimpleDeclaration simple = (SimpleDeclaration) ((AsignationDeclaration) declarationCheck).getSimpleDeclaration();
            for (int i = 0; i < simple.getIDs().size(); i++) {
                Parent.addHijo(simple.getIDs().get(i), new SimpleNode(simple.getIDs().get(i), simple.getType()));
            }
        } else if (declarationCheck instanceof SimpleDeclaration) {
            SimpleDeclaration simple = (SimpleDeclaration) declarationCheck;
            for (int i = 0; i < simple.getIDs().size(); i++) {
                Parent.addHijo(simple.getIDs().get(i), new SimpleNode(simple.getIDs().get(i), simple.getType()));
            }
        } else if (declarationCheck instanceof ProcedureDeclaration) {
            ProcedureDeclaration tmp = (ProcedureDeclaration) declarationCheck;
            //CHANGE NULL ON FINAL
            FunctionNode newScope = new FunctionNode(tmp.getId(), new VoidType(), new ArrayList<>(), null);
            checkParametersFunction(tmp.getLDP(), newScope);

            if (Parent.getHijos().get(tmp.getId()) == null) {
                ListFunctionNode tmpList = new ListFunctionNode(tmp.getId(), new ArrayList());
                tmpList.addFunction(newScope);
                if (!Parent.addHijo(tmp.getId(), tmpList)) {
                    hasError = true;
                }
            } else {
                Node keyNode = Parent.getHijos().get(tmp.getId());
                if (keyNode instanceof ListFunctionNode) {
                    if (!((ListFunctionNode) keyNode).addFunction(newScope)) {
                        hasError = true;
                    }
                } else {
                    hasError = true;
                }
            }
            checkDeclaration(tmp.getDec(), newScope);
        } else if (declarationCheck instanceof FunctionDeclaration) {
            FunctionDeclaration tmp = (FunctionDeclaration) declarationCheck;
            //CHANGE NULL ON FINAL
            FunctionNode newScope = new FunctionNode(tmp.getId(), tmp.getRetType(), new ArrayList<>(), null);
            checkParametersFunction(tmp.getLDP(), newScope);
            if (Parent.getHijos().get(tmp.getId()) == null) {
                ListFunctionNode tmpList = new ListFunctionNode(tmp.getId(), new ArrayList());
                tmpList.addFunction(newScope);
                if (!Parent.addHijo(tmp.getId(), tmpList)) {
                    hasError = true;
                }
            } else {
                Node keyNode = Parent.getHijos().get(tmp.getId());
                if (keyNode instanceof ListFunctionNode) {
                    if (!((ListFunctionNode) keyNode).addFunction(newScope)) {
                        hasError = true;
                    }
                } else {
                    hasError = true;
                }
            }
            checkDeclaration(tmp.getDec(), newScope);
        }
    }

    private void checkParametersFunction(ListDeclarationParameter List, FunctionNode function) {
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
    
    private void checkStatement(Statement Stat,FunctionNode Parent){
        if (Stat instanceof AsignationStatement){
            AsignationStatement tmp = (AsignationStatement)Stat;
            Type firstType = Parent.searchTypeById(tmp.getID());
            Type secondType = getExpressionType(tmp.getExp(),Parent);
            if (!firstType.compare(secondType)){
                hasError = true;
            }
        }else if (Stat instanceof CaseStatement){
            //TODO:
        }else if (Stat instanceof ForStatement){
            ForStatement tmp = (ForStatement)Stat;
            checkStatement(tmp.getAsig(),Parent);
            checkStatement(tmp.getStat(),Parent);
            //TODO: Expression?
        }else if (Stat instanceof FunctionCallStatement){
            getPrimaryType(((FunctionCallStatement)Stat).getCall(),Parent);
        }else if (Stat instanceof IfStatement){
            //TODO:
        }
        //TODO:
    }
    
    private Type getExpressionType(Expression Exp,FunctionNode Parent){
        if (Exp instanceof AddExpression){
            AddExpression tmp = (AddExpression)Exp;
            Type firstType = getExpressionType(tmp.getExp1(),Parent);
            Type secondType = getExpressionType(tmp.getExp2(),Parent);
            //AND INT, FLOAT TYPE?
            if (firstType.compare(secondType)){
                return firstType;
            }else{
                //CHANGE NULL TO ERRORTYPE
                return null;
            }
        }else if(Exp instanceof MultExpression){ 
            MultExpression tmp = (MultExpression)Exp;
            Type firstType = getExpressionType(tmp.getExp1(),Parent);
            Type secondType = getExpressionType(tmp.getExp2(),Parent);
            //ADD INT, FLOAT CHECK?
            if (firstType.compare(secondType)){
                return firstType;
            }else{
                //CHANGE NULL TO ERRORTYPE
                return null;
            }
        }else if(Exp instanceof ConditionExpression){ 
            ConditionExpression tmp = (ConditionExpression)Exp;
            Type firstType = getExpressionType(tmp.getExp1(),Parent);
            Type secondType = getExpressionType(tmp.getExp2(),Parent);
            //ADD BOOLEAN CHECK?
            if (firstType.compare(secondType)){
                return firstType;
            }else{
                //CHANGE NULL TO ERRORTYPE
                return null;
            }
        }else if(Exp instanceof RelationExpression){ 
            RelationExpression tmp = (RelationExpression)Exp;
            Type firstType = getExpressionType(tmp.getExp1(),Parent);
            Type secondType = getExpressionType(tmp.getExp2(),Parent);
            //ADD BOOLEAN CHECK?
            if (firstType.compare(secondType)){
                return firstType;
            }else{
                //CHANGE NULL TO ERRORTYPE
                return null;
            }
        }else if (Exp instanceof PrimaryExpression){
            return getPrimaryType(((PrimaryExpression)Exp).getValue(),Parent);
        }
        //CHANGE NULL TO ERRORTYPE
        return null;
    }
    
    private Type getPrimaryType(Primary Prim,FunctionNode Parent){
        if (Prim instanceof FunctionCall){
            FunctionCall tmp = (FunctionCall)Prim;
            List<Type> tmpParams = new ArrayList();
            for (int i = 0 ; i<tmp.getParams().getValues().size() ; i++){
                tmpParams.add(getPrimaryType(tmp.getParams().getValues().get(i),Parent));
            }
            return Parent.searchFunctionNodeTypeById(tmp.getID(),tmpParams);
        }else if (Prim instanceof ID){
            ID tmp = (ID)Prim;
            return Parent.searchTypeById(tmp.getID());
        }else if (Prim instanceof LiteralBoolean){
            return new BooleanType();
        }else if (Prim instanceof LiteralFloat){
            return new FloatType();
        }else if (Prim instanceof LiteralInt){
            return new IntType();
        }
        return null;
    }
}
