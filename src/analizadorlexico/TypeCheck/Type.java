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
    protected int SIZE;
    
    protected final int INTEGER_SIZE = 4;//bytes
    protected final int FLOAT_SIZE = 8;//bytes
    protected final int BOOLEAN_SIZE = 1;//bytes
    protected final int VOID_SIZE = 0;//bytes?
    
    protected Type(String TYPE) {
        this.TYPE = TYPE;
    }

    public String getTYPE() {
        return TYPE;
    }

    public void setTYPE(String TYPE) {
        this.TYPE = TYPE;
    }

    public abstract int getSIZE();
    
    
    public abstract boolean compare(Type value);
}
