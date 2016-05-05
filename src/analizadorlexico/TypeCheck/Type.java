/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analizadorlexico.TypeCheck;

/**
 *
 * @author Kevin Barahona
 */
public abstract class Type {
    protected String TYPE;
    protected Type(String TYPE) {
        this.TYPE = TYPE;
    }

    public String getTYPE() {
        return TYPE;
    }

    public void setTYPE(String TYPE) {
        this.TYPE = TYPE;
    }
    
    public abstract boolean compare(Type value);
}
