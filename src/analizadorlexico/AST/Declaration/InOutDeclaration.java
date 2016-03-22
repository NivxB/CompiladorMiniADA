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
public class InOutDeclaration extends Declaration {
    String Id;
    String Type;
    String InOutType;

    public InOutDeclaration(String Id, String Type, String InOutType, Declaration NextDeclaration) {
        super(NextDeclaration);
        this.Id = Id;
        this.Type = Type;
        this.InOutType = InOutType;
    }
    
    public InOutDeclaration(String Id, String Type, String InOutType) {
        super(null);
        this.Id = Id;
        this.Type = Type;
        this.InOutType = InOutType;
    }
    
    public InOutDeclaration(Declaration D,Declaration Next){
        this(((InOutDeclaration)D).Id,((InOutDeclaration)D).Type,((InOutDeclaration)D).InOutType,Next);
    }
    
}
