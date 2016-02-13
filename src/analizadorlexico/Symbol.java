/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analizadorlexico;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Kevin Barahona
 */
public class Symbol {
    int type;
    int line;
    int column;
    Object value;
    
    public static final int PROCEDURE = 1;
    public static final int IS = 2;
    public static final int BEGIN = 3;
    public static final int END = 4;
    public static final int PUT = 5;
    public static final int NEW_LINE = 6;
    public static final int ASIGNATION = 7;
    public static final int END_INSTRUCTION = 8;
    public static final int ID = 9;
    public static final int EOF = 10;
    public static final int DECLARATION = 11;
    public static final int TYPE = 12;
    public static final int LITERAL_INT = 13;
    
    public static String getTypeName(int type){
        switch (type){
            case PROCEDURE:
                return "PROCEDURE";
            case IS:
                return "IS";
            case BEGIN:
                return "BEGIN";
            case END:
                return "END";
            case PUT:
                return "PUT";
            case NEW_LINE:
                return "NEW_LINE";
            case ASIGNATION:
                return "ASIGNATION";
            case END_INSTRUCTION:
                return "END_INSTRUCTION";
            case ID:
                return "ID";
            case EOF:
                return "EOF";
            case DECLARATION: 
                return "DECLARATION";
            case TYPE:
                return "TYPE";
            case LITERAL_INT:
                return "LITERAL INTEGER";
        }
        return "NOT KNOWN";
    }
    
    public Symbol(int type, int line, int column, Object value) {
        this.type = type;
        this.line = line;
        this.column = column;
        this.value = value;
    }

    public Symbol(int type, int line, int column) {
        this.type = type;
        this.line = line;
        this.column = column;
    }
    
    public boolean isEOF(){
        return type == Symbol.EOF;
    }
    
    
    
    @Override
    public String toString() {
        return "Type=" + getTypeName(type) + ", line=" + line + ", column=" + column + ", value=" + value;
    }
    
    
}
