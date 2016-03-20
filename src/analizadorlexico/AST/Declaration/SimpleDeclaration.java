/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analizadorlexico.AST.Declaration;

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

    public SimpleDeclaration(String Id, String Type, Boolean isConstant) {
        this.IDs = new ArrayList<>();
        IDs.add(Id);
        this.Type = Type;
        this.isConstant = isConstant;
    }

    public SimpleDeclaration(String ID, SimpleDeclaration Previous) {
        this.IDs = Previous.IDs;
        IDs.add(ID);
        this.Type = Previous.Type;
        this.isConstant = Previous.isConstant;
    }
    
    
}
