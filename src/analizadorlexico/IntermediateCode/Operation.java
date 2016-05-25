/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analizadorlexico.IntermediateCode;

/**
 *
 * @author Kevin Barahona
 */
public abstract class Operation {
    public static final int SIMPLE_OPERATION = 0;
    public static final int LABEL_OPERATION = 1;
    public static final int IF_OPERATION = 2;
    public static final int GOTO_OPERATION = 3;
    
    public abstract String getStringValue();
}
