/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analizadorlexico.AST.Declaration;

import analizadorlexico.TypeCheck.Type;

/**
 *
 * @author kbarahona
 */
public class InOutDeclaration extends Declaration {
    String Id;
    Type Type;
    String InOutType;

    public String getId() {
        return Id;
    }

    public void setId(String Id) {
        this.Id = Id;
    }

    public Type getType() {
        return Type;
    }

    public void setType(Type Type) {
        this.Type = Type;
    }

    public String getInOutType() {
        return InOutType;
    }

    public void setInOutType(String InOutType) {
        this.InOutType = InOutType;
    }

  
    
    public InOutDeclaration(String Id, Type Type, String InOutType) {

        this.Id = Id;
        this.Type = Type;
        this.InOutType = InOutType;
    }
    

}
