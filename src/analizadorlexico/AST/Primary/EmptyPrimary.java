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
public class EmptyPrimary extends Primary{

    @Override
    public String getStringType() {
        return "EMPTY";
    }
    
}
