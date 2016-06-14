/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analizadorlexico.IntermediateCode;

/**
 *
 * @author Carlos
 */
public class ParamOperation extends Operation{
    String temporal;
    ParamOperation(String name){
        this.temporal=name;
    }

    public String getTemporal() {
        return temporal;
    }

    public void setTemporal(String temporal) {
        this.temporal = temporal;
    }

    @Override
    public String getStringValue() {
        return "param "+temporal+"";
    }
    
}
