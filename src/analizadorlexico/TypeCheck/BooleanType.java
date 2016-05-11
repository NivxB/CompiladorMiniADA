/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analizadorlexico.TypeCheck;

/**
 *
 * @author Carlos
 */
public class BooleanType extends Type{

    public BooleanType() {
        //1 byte
        super("BOOLEAN");
    }

    @Override
    public boolean compare(Type value) {
        return (value.TYPE.equals("BOOLEAN"));
    }
    
    @Override
    public int getSIZE(){
        return super.BOOLEAN_SIZE;
    }
    
}
