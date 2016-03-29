/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analizadorlexico.AST;

import analizadorlexico.AST.Declaration.Declaration;
import analizadorlexico.AST.Statement.Statement;

/**
 *
 * @author Kevin Barahona
 */
public class InitProcedure {
    private String BeginId;
    private String EndId;
    private Declaration Dec;
    private Statement Stat;

    public InitProcedure(String BeginId, String EndId, Declaration Dec, Statement Stat) {
        this.BeginId = BeginId;
        this.EndId = EndId;
        this.Dec = Dec;
        this.Stat = Stat;
    }

    public String getBeginId() {
        return BeginId;
    }

    public void setBeginId(String BeginId) {
        this.BeginId = BeginId;
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
    
    
}
