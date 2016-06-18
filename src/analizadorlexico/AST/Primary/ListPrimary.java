/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analizadorlexico.AST.Primary;

import analizadorlexico.AST.Expression.Expression;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author kbarahona
 */
public class ListPrimary {
    private List<Expression> values;
    

    public ListPrimary(Expression Val) {
        this.values = new ArrayList<>();
        this.values.add(Val);
    }
    
    public ListPrimary(Expression Val, ListPrimary Previous){
        this.values = Previous.values;
        this.values.add(Val);
    };

    public ListPrimary() {
        values = new ArrayList<>();
    }

    public List<Expression> getValues() {
        return values;
    }

    public void setValues(List<Expression> values) {
        this.values = values;
    }
    
    public void addValue(Expression val){
        values.add(val);
    }
}
