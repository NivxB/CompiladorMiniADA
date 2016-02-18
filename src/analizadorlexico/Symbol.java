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
    public static final int SUM_OPERATOR = 14;
    public static final int MULT_OPERATOR = 15;
    public static final int RELATION_OPERATOR = 16;
    public static final int LITERAL_BOOLEAN = 17;
    public static final int IF = 18;
    public static final int COMMA=19;
    public static final int FOR=20;
    public static final int IN=21;
    public static final int WITH = 22;
    public static final int LOOP=23;
    public static final int WHILE=25;
    public static final int EXIT=26;
    public static final int WHEN=27;
    public static final int THEN=28;
    public static final int ELSE=29;
    public static final int ELSEIF=30;
    public static final int LITERAL_STRING=32;
    public static final int OPEN_PARENTHESIS = 33;
    public static final int CLOSE_PARENTHESIS = 34;
    public static final int LITERAL_CHAR = 35;
    
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
            case SUM_OPERATOR:
                return "SUM OPERATOR";
            case MULT_OPERATOR:
                return "MULT OPERATOR";
            case RELATION_OPERATOR:
                return "RELATION OPER";
            case LITERAL_BOOLEAN:
                return "LITERAL BOOLEAN";
            case IF:
                return "IF";
            case COMMA:
                return "COMMA";
            case FOR:
                return "FOR";
            case IN:
                return "IN";
            case LOOP:
                return "LOOP";
            case WHILE:
                return "WHILE";
            
            case THEN:
                return "THEN";
            case ELSE:
                return "ELSE";
            case ELSEIF:
                return "ELSEIF";
           
            case LITERAL_STRING:
                return "LITERAL_STRING";
            case OPEN_PARENTHESIS:
                return "OPEN PARENTHESIS";
            case CLOSE_PARENTHESIS:
                return "CLOSE PARENTHESIS";
            case LITERAL_CHAR:
                return "LITERAL CHAR";
            case WHEN:
                return "WHEN";
            case EXIT:
                return "EXIT";
                            
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
    
    public boolean isUnknown(){
        return type == -1;
    }
    
    @Override
    public String toString() {
        if (value == null){
            return "Type: " + getTypeName(type) + ", Line=" + line + ", Column=" + column +"\n";
        }
        return "Type: " + getTypeName(type) + ", Line=" + line + ", Column=" + column + ", Value=\"" + value+"\"\n";
    }
    
    
}
