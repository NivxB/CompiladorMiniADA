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
public class CaseBody {
    Primary val;
    Statement Stat;
    CaseBody NextCase;

    public CaseBody(Primary val, Statement Stat, CaseBody NextCase) {
        this.val = val;
        this.Stat = Stat;
        this.NextCase = NextCase;
    }

    public CaseBody() {
    }
}
