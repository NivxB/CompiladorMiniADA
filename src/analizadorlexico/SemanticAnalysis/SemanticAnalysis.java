/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analizadorlexico.SemanticAnalysis;

import analizadorlexico.AST.Declaration.AsignationDeclaration;
import analizadorlexico.AST.Declaration.Declaration;
import analizadorlexico.AST.Declaration.FunctionDeclaration;
import analizadorlexico.AST.Declaration.ProcedureDeclaration;
import analizadorlexico.AST.Declaration.SequenceDeclaration;
import analizadorlexico.AST.Declaration.SimpleDeclaration;
import analizadorlexico.AST.InitProcedure;
import analizadorlexico.SymbolTable.FunctionNode;
import analizadorlexico.SymbolTable.Node;
import analizadorlexico.SymbolTable.SimpleNode;
import analizadorlexico.TypeCheck.Type;
import analizadorlexico.TypeCheck.VoidType;

/**
 *
 * @author Kevin Barahona
 */
public class SemanticAnalysis {
    FunctionNode root;

    public SemanticAnalysis(String id, Type tipo) {
        this.root = new FunctionNode(id,tipo);
    }
    
    public SemanticAnalysis(InitProcedure Proc) {
        this.root = new FunctionNode(Proc.getBeginId(),new VoidType());
        checkDeclaration(Proc.getDec(),root);
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
        }else if (declarationCheck instanceof SimpleDeclaration){
            SimpleDeclaration simple = (SimpleDeclaration) declarationCheck;
            for (int i = 0; i < simple.getIDs().size(); i++) {
                Parent.addHijo(simple.getIDs().get(i), new SimpleNode(simple.getIDs().get(i), simple.getType()));
            }
        }else if (declarationCheck instanceof ProcedureDeclaration){
            ProcedureDeclaration tmp = (ProcedureDeclaration)declarationCheck;
            FunctionNode newScope = new FunctionNode(tmp.getId(),new VoidType(),null);
            checkDeclaration(tmp.getDec(),newScope);
            Parent.addHijo(tmp.getId(), newScope);
        }else if (declarationCheck instanceof FunctionDeclaration){
            FunctionDeclaration tmp = (FunctionDeclaration)declarationCheck;
            FunctionNode newScope = new FunctionNode(tmp.getId(),tmp.getRetType(),null);
            checkDeclaration(tmp.getDec(),newScope);
            Parent.addHijo(tmp.getId(), newScope);
        }
        
        if (nextCheck != null){
            checkDeclaration(nextCheck,Parent);
        }
        
    }
;
}
