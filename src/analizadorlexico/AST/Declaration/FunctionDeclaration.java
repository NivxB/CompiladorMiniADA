/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analizadorlexico.AST.Declaration;

import analizadorlexico.AST.Statement.Statement;

/**
 *
 * @author Kevin Barahona
 */
public class FunctionDeclaration extends Declaration{
    private ListDeclarationParameter LDP;
    private Declaration Dec;
    private Statement Stat;

    public FunctionDeclaration(ListDeclarationParameter LDP, Declaration Dec, Statement Stat, Declaration NextDeclaration) {
        super(NextDeclaration);
        this.LDP = LDP;
        this.Dec = Dec;
        this.Stat = Stat;
    }
}
