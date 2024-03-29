/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analizadorlexico.AST.Declaration;

import analizadorlexico.AST.Statement.Statement;
import analizadorlexico.TypeCheck.Type;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Kevin Barahona
 */
public class FunctionDeclaration extends Declaration{
    private String Id;
    private String EndId;
    private ListDeclarationParameter LDP;

    public String getId() {
        return Id;
    }

    public void setId(String Id) {
        this.Id = Id;
    }

    public String getEndId() {
        return EndId;
    }

    public ListDeclarationParameter getLDP() {
        return LDP;
    }

    public void setLDP(ListDeclarationParameter LDP) {
        this.LDP = LDP;
    }
    
    

    public void setEndId(String EndId) {
        this.EndId = EndId;
    }

    public Declaration getDec() {
        return Dec;
    }

    public void setDec(Declaration Dec) {
        this.Dec = Dec;
    }
    
       public List<Type> getParamsType(ListDeclarationParameter LDP,List<Type> parent){
        List<Type> tmpRetVal = new ArrayList<>();
        Declaration tmpDec = LDP.getDec();
        ListDeclarationParameter tmpCheck = LDP.getLDP();
        if (tmpDec instanceof AsignationDeclaration){
            AsignationDeclaration tmp = (AsignationDeclaration)tmpDec;
            SimpleDeclaration tmpSimple = (SimpleDeclaration) tmp.getSimpleDeclaration();
            for (String id: tmpSimple.getIDs()){
                tmpRetVal.add(tmpSimple.getType());
            }
        }else if (tmpDec instanceof SimpleDeclaration){
            SimpleDeclaration tmpSimple = (SimpleDeclaration) tmpDec;
            for (String id: tmpSimple.getIDs()){
                tmpRetVal.add(tmpSimple.getType());
            }
        }else if (tmpDec instanceof InOutDeclaration){
            InOutDeclaration tmp = (InOutDeclaration)tmpDec;
            tmpRetVal.add(tmp.getType());
        }
        if (tmpCheck != null){
            tmpRetVal.addAll(getParamsType(tmpCheck,tmpRetVal));
        }
        return tmpRetVal;
    }
    
    public Statement getStat() {
        return Stat;
    }

    public void setStat(Statement Stat) {
        this.Stat = Stat;
    }

    public Type getRetType() {
        return RetType;
    }

    public void setRetType(Type RetType) {
        this.RetType = RetType;
    }
    private Declaration Dec;
    private Statement Stat;
    private Type RetType;

    public FunctionDeclaration(String Id, String EndId, ListDeclarationParameter LDP, Declaration Dec, Statement Stat, Type RetType) {
        this.Id = Id+RetType.getTYPE().charAt(0);
        this.EndId = EndId;
        this.LDP = LDP;
        this.Dec = Dec;
        this.Stat = Stat;
        this.RetType = RetType;
    }

    

}
