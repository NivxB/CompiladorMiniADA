/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analizadorlexico.TypeCheck;

/**
 *
 * @author kbarahona
 */
public class ErrorType extends Type{

    public ErrorType() {
        super("ERROR");
    }

    @Override
    public int getSIZE() {
        return 0;
    }

    @Override
    public boolean compare(Type value) {
        return false;
    }
    
}
