/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analizadorlexico.AST.Declaration;

/**
 *
 * @author kbarahona
 */
public class SequenceDeclaration extends Declaration{
    Declaration ThisDeclaration;
    Declaration NextDeclarations;

    public SequenceDeclaration(Declaration ThisDeclaration, Declaration NextDeclarations) {
        this.ThisDeclaration = ThisDeclaration;
        this.NextDeclarations = NextDeclarations;
    }

    public Declaration getThisDeclaration() {
        return ThisDeclaration;
    }

    public void setThisDeclaration(Declaration ThisDeclaration) {
        this.ThisDeclaration = ThisDeclaration;
    }

    public Declaration getNextDeclarations() {
        return NextDeclarations;
    }

    public void setNextDeclarations(SequenceDeclaration NextDeclarations) {
        this.NextDeclarations = NextDeclarations;
    }
    
    
    
}
