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
    private ListDeclarationParameter LDP;
    private Declaration Dec;
    private Statement Stat;
    private String RetType;

    public FunctionDeclaration(String Id,ListDeclarationParameter LDP, Declaration Dec, Statement Stat,String RetType, Declaration NextDeclaration) {
        super(NextDeclaration);
        this.Id = Id;
        this.LDP = LDP;
        this.Dec = Dec;
        this.Stat = Stat;
        this.RetType = RetType;
    }

    public FunctionDeclaration(String Id,ListDeclarationParameter LDP, Declaration Dec, Statement Stat,String RetType) {
        super(null);
        this.Id = Id;
        this.LDP = LDP;
        this.Dec = Dec;
        this.Stat = Stat;
        this.RetType = RetType;
    }

    public FunctionDeclaration(Declaration D, Declaration Next) {
        this(((FunctionDeclaration) D).Id,((FunctionDeclaration) D).LDP, ((FunctionDeclaration) D).Dec, ((FunctionDeclaration) D).Stat,((FunctionDeclaration) D).RetType, Next);
    }
}
