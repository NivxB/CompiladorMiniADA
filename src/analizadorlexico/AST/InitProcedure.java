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
    private Declaration Dec;
    private Statement Stat;

    public InitProcedure(Declaration Dec, Statement Stat) {
        this.Dec = Dec;
        this.Stat = Stat;
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
