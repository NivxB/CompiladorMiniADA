/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analizadorlexico.AST.Declaration;

import analizadorlexico.AST.Statement.Statement;
import analizadorlexico.TypeCheck.Type;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author kbarahona
 */
public class SimpleDeclaration extends Declaration {
    private List<String> IDs;
    private Type Type;
    private Boolean isConstant;


    public SimpleDeclaration(String Id, Type Type, Boolean isConstant) {
        this.IDs = new ArrayList<>();
        IDs.add(Id);
        this.Type = Type;
        this.isConstant = isConstant;
    }

    public SimpleDeclaration(String ID, Declaration Previous) {
        this.IDs = ((SimpleDeclaration)Previous).IDs;
        IDs.add(ID);
        this.Type = ((SimpleDeclaration)Previous).Type;
        this.isConstant = ((SimpleDeclaration)Previous).isConstant;
    }

    public List<String> getIDs() {
        return IDs;
    }

    public void setIDs(List<String> IDs) {
        this.IDs = IDs;
    }

    public Type getType() {
        return Type;
    }

    public void setType(Type Type) {
        this.Type = Type;
    }

    public Boolean getIsConstant() {
        return isConstant;
    }

    public void setIsConstant(Boolean isConstant) {
        this.isConstant = isConstant;
    }
    
    
    
    
}
