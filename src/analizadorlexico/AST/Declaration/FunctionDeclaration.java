/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analizadorlexico.AST.Declaration;

import analizadorlexico.AST.Statement.Statement;
import analizadorlexico.TypeCheck.Type;

/**
 *
 * @author Kevin Barahona
 */
public class FunctionDeclaration extends Declaration{
    private String Id;
    private String EndId;
    private ListDeclarationParameter LDP;

    public String getId() {
        return Id;
    }

    public void setId(String Id) {
        this.Id = Id;
    }

    public String getEndId() {
        return EndId;
    }

    public void setEndId(String EndId) {
        this.EndId = EndId;
    }

    public Declaration getDec() {
        return Dec;
    }

    public void setDec(Declaration Dec) {
        this.Dec = Dec;
    }

    public Statement getStat() {
        return Stat;
    }

    public void setStat(Statement Stat) {
        this.Stat = Stat;
    }

    public Type getRetType() {
        return RetType;
    }

    public void setRetType(Type RetType) {
        this.RetType = RetType;
    }
    private Declaration Dec;
    private Statement Stat;
    private Type RetType;

    public FunctionDeclaration(String Id, String EndId, ListDeclarationParameter LDP, Declaration Dec, Statement Stat, Type RetType) {
        this.Id = Id;
        this.EndId = EndId;
        this.LDP = LDP;
        this.Dec = Dec;
        this.Stat = Stat;
        this.RetType = RetType;
    }

    

}
