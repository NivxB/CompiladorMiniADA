/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analizadorlexico.AST.Declaration;

/**
 *
 * @author Kevin Barahona
 */
public class ListDeclarationParameter{
    private Declaration Dec;
    private ListDeclarationParameter LDP;

    public ListDeclarationParameter() {
    }

    
    public ListDeclarationParameter(Declaration Dec, ListDeclarationParameter LDP) {
        this.Dec = Dec;
        this.LDP = LDP;
    }

    public Declaration getDec() {
        return Dec;
    }

    public void setDec(Declaration Dec) {
        this.Dec = Dec;
    }

    public ListDeclarationParameter getLDP() {
        return LDP;
    }

    public void setLDP(ListDeclarationParameter LDP) {
        this.LDP = LDP;
    }
    
    
}
