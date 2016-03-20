/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analizadorlexico.AST.Primary;

import java.util.List;

/**
 *
 * @author kbarahona
 */
public class FunctionCall extends Primary{
    private String ID;
    private ListPrimary Params;

    public FunctionCall(String ID, ListPrimary Params) {
        this.ID = ID;
        this.Params = Params;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public ListPrimary getParams() {
        return Params;
    }

    public void setParams(ListPrimary Params) {
        this.Params = Params;
    }
    
    
    
    
    
}
