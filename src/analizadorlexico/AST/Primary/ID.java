/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analizadorlexico.AST.Primary;

/**
 *
 * @author kbarahona
 */
public class ID extends Primary{
    private String iD;

    public ID(String iD) {
        this.iD = iD;
    }

    public String getID() {
        return iD;
    }

    public void setID(String iD) {
        this.iD = iD;
    }
    
    @Override
    public String getStringType() {
        return "ID";
    }
    
}
