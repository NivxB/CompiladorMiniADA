/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analizadorlexico.AST.Statement;

import analizadorlexico.AST.Primary.Primary;

/**
 *
 * @author kbarahona
 */
public class CaseStatement extends Statement{
    Primary Val;
    CaseBody Body;

    public Primary getVal() {
        return Val;
    }

    public void setVal(Primary Val) {
        this.Val = Val;
    }

    public CaseBody getBody() {
        return Body;
    }

    public void setBody(CaseBody Body) {
        this.Body = Body;
    }

    public CaseStatement(Primary Val, CaseBody Body,Statement Next) {
        super(Next);
        this.Val = Val;
        this.Body = Body;
    }
    
    public CaseStatement(Primary Val, CaseBody Body) {
        super(null);
        this.Val = Val;
        this.Body = Body;
    }
}
