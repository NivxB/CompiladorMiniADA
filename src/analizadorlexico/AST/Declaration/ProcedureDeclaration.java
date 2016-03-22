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
public class ProcedureDeclaration extends Declaration {

    private String Id;
    private ListDeclarationParameter LDP;
    private Declaration Dec;
    private Statement Stat;

    public ProcedureDeclaration(String Id,ListDeclarationParameter LDP, Declaration Dec, Statement Stat, Declaration NextDeclaration) {
        super(NextDeclaration);
        this.Id = Id;
        this.LDP = LDP;
        this.Dec = Dec;
        this.Stat = Stat;
    }

    public ProcedureDeclaration(String Id,ListDeclarationParameter LDP, Declaration Dec, Statement Stat) {
        super(null);
        this.Id = Id;
        this.LDP = LDP;
        this.Dec = Dec;
        this.Stat = Stat;
    }

    public ProcedureDeclaration(Declaration D, Declaration Next) {
        this(((ProcedureDeclaration) D).Id,((ProcedureDeclaration) D).LDP, ((ProcedureDeclaration) D).Dec, ((ProcedureDeclaration) D).Stat, Next);
    }
}
