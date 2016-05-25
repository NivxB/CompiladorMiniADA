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
public class Temporal {
    private static int count = 0;
    private String name;

    public Temporal() {
        name = "t"+count;
        count++;
    }

    @Override
    public String toString() {
        return name;
    }    
}
