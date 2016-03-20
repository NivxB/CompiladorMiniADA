/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analizadorlexico.AST.Primary;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author kbarahona
 */
public class ListPrimary {
    private List<Primary> values;

    public ListPrimary(List<Primary> values) {
        this.values = values;
    }

    public ListPrimary() {
        values = new ArrayList<>();
    }

    public List<Primary> getValues() {
        return values;
    }

    public void setValues(List<Primary> values) {
        this.values = values;
    }
    
    public void addValue(Primary val){
        values.add(val);
    }
}
