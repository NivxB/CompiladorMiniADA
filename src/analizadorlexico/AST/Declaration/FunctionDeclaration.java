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
    private String Id;
    private String EndId;
    private ListDeclarationParameter LDP;
    private Declaration Dec;
    private Statement Stat;
    private String RetType;

    public FunctionDeclaration(String Id, String EndId, ListDeclarationParameter LDP, Declaration Dec, Statement Stat, String RetType) {
        this.Id = Id;
        this.EndId = EndId;
        this.LDP = LDP;
        this.Dec = Dec;
        this.Stat = Stat;
        this.RetType = RetType;
    }

    

}
