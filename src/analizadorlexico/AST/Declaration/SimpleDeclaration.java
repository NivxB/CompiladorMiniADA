/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analizadorlexico.AST.Declaration;

import analizadorlexico.AST.Statement.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author kbarahona
 */
public class SimpleDeclaration extends Declaration {
    private List<String> IDs;
    private String Type;
    private Boolean isConstant;

    public SimpleDeclaration(List<String> IDs, String Type, Boolean isConstant, Declaration NextDeclaration) {
        super(NextDeclaration);
        this.IDs = IDs;
        this.Type = Type;
        this.isConstant = isConstant;
    }

    public SimpleDeclaration(Declaration D,Declaration Next){
        this(((SimpleDeclaration)D).IDs,((SimpleDeclaration)D).Type,((SimpleDeclaration)D).isConstant,Next);
    }
    public SimpleDeclaration(String Id, String Type, Boolean isConstant) {
        super(null);
        this.IDs = new ArrayList<>();
        IDs.add(Id);
        this.Type = Type;
        this.isConstant = isConstant;
    }

    public SimpleDeclaration(String ID, Declaration Previous) {
        super(Previous.NextDeclaration);
        this.IDs = ((SimpleDeclaration)Previous).IDs;
        IDs.add(ID);
        this.Type = ((SimpleDeclaration)Previous).Type;
        this.isConstant = ((SimpleDeclaration)Previous).isConstant;
    }
    
    
}
