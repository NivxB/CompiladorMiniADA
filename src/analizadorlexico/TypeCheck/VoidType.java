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
public class VoidType extends Type{

    public VoidType() {
        super("VOID");
    }

    @Override
    public boolean compare(Type value) {
         return (value.TYPE.equals("INTEGER"));
    }
    
}
