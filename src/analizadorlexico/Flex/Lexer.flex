package analizadorlexico;
import java_cup.runtime.Symbol;
import analizadorlexico.TypeCheck.*;
%%
%class Lexer
%unicode
%line
%column
%caseless
%ignorecase
%cupsym sym
%cup
%{
     StringBuffer string = new StringBuffer();

     private Symbol symbol(int type) {
       return new Symbol(type, yyline, yycolumn);
      }
      private Symbol symbol(int type, Object value) {
        return new Symbol(type, yyline, yycolumn, value);
      }
      private void lexic_error(){
        System.err.print("Lexic Error:");
        System.err.print(" line:" + yyline);
        System.err.print(" column:" + yycolumn);
        System.err.println();
        System.err.print("Unknown Symbol: " + yytext());
        System.err.println();
        System.err.println();
      }
%}

procedure   = "procedure"
to          = "to"
null        = "null"
function    = "function"
is          = "is"
begin       = "begin"
end         = "end"
newLine     = "New_Line"
with        = "With"
openParenthesis = "("
closeParenthesis = ")"
comma       = ","
type        = "INTEGER" | "FLOAT" | "BOOLEAN"
sumOperator = "+" | "-"
multOperator = "*" | "/"
relationOperator = ">" | "<" | ">=" | "<=" | "=" | "/="
asig        = ":="
caseasig    = "=>"
declaration = ":"
beginComment = "--"
endInstruction  = ";"
id          ={letter}({letter}*{number}*{especialChar}*)*
letter      = [a-zA-Z]
literalBoolean = "true" | "false"
literalFloat      = {number}+"."{number}+
literalInteger     = {number}+
number      = [0-9]
especialChar = [_-]
ignoreChar = [ \t\r\n\f]
conditionelement = "or" | "and"
literalChar = \'.\'
if     = "if"
then        = "then"
else        = "else"
elseif      = "elsif"
for         = "for"
out         = "out"
loop        = "loop"
while       = "while"
exit        = "exit"
when        = "when"
constant    = "constant"
package     = "package"
body        = "body"
return      = "return"
case        = "case"
is          = "is"
others  = "others"
reverse     = "reverse"

%state STRING
%state COMMENT

%%

<YYINITIAL>{
  {to}                  {return symbol(sym.TO);}
  {conditionelement}    {return symbol(sym.CONDITION_ELEMENT,yytext());}
  {package}             {return symbol(sym.PACKAGE);}
  {others}              {return symbol(sym.OTHERS);}
  {return}              {return symbol(sym.RETURN);}
  {null}                {return symbol(sym.NULL);}
  {body}                {return symbol(sym.BODY);}
  {procedure}           {return symbol(sym.PROCEDURE);}
  {function}            {return symbol(sym.FUNCTION);}
  {constant}            {return symbol(sym.CONSTANT);}
  {with}                {return symbol(sym.WITH);}
  {is}                  {return symbol(sym.IS);}
  {begin}               {return symbol(sym.BEGIN);}
  {end}                 {return symbol(sym.END);}
  {newLine}             {return symbol(sym.NEW_LINE);}
  {declaration}         {return symbol(sym.DECLARATION);}
  {asig}                {return symbol(sym.ASIGNATION);}
  {exit}                {return symbol(sym.EXIT);}
  {when}                {return symbol(sym.WHEN);}
  {comma}               {return symbol(sym.COMMA);}
  {openParenthesis}     {return symbol(sym.OPEN_PARENTHESIS);}
  {closeParenthesis}    {return symbol(sym.CLOSE_PARENTHESIS);}
  {sumOperator}         {return symbol(sym.SUM_OPERATOR,yytext());}
  {multOperator}        {return symbol(sym.MULT_OPERATOR,yytext());}
  {relationOperator}    {return symbol(sym.RELATION_OPERATOR,yytext());}
  {beginComment}          { yybegin(COMMENT); }
  {type}                {
                          String text = yytext().toUpperCase();
                          Type retType = null;
                          if (text.equals("INTEGER")){
                            retType = new IntType();
                          }else if (text.equals("FLOAT")){
                            retType = new FloatType();
                          }else if (text.equals("BOOLEAN")){
                            retType = new BooleanType();
                          }
                          return symbol(sym.TYPE,retType);
                        }
  {literalFloat}        {return symbol(sym.LITERAL_FLOAT,yytext());}
  {literalBoolean}      {return symbol(sym.LITERAL_BOOLEAN,yytext());}
  {literalInteger}      {return symbol(sym.LITERAL_INT,yytext());}
  {endInstruction}      {return symbol(sym.END_INSTRUCTION);}
  {literalChar}         {return symbol(sym.LITERAL_CHAR,yytext());}
  {if}                  {return symbol(sym.IF);}
  {else}                {return symbol(sym.ELSE);}
  {elseif}              {return symbol(sym.ELSEIF);}
  {then}                {return symbol(sym.THEN);}
  {for}                 {return symbol(sym.FOR);}
  {out}                 {return symbol(sym.OUT);}
  {loop}                {return symbol(sym.LOOP);}
  {while}               {return symbol(sym.WHILE);}
  {id}                  {return symbol(sym.ID,yytext());}
  {case}                {return symbol(sym.CASE);}
  {caseasig}            {return symbol(sym.CASEASIG);}
  {reverse}             {return symbol(sym.REVERSE);}
  {ignoreChar} {/* ignore */}
  \"        {string.setLength(0); yybegin(STRING);}
    .   { lexic_error(); }


}

<COMMENT> {
    \n { yybegin(YYINITIAL); }
    {ignoreChar} {/* ignore */}
    . {/* Ignore */}
}

<STRING>{
  \"  {yybegin(YYINITIAL); return symbol(sym.LITERAL_STRING,string.toString()); }
  \\\" {string.append('\"');}
  \\  {string.append('\\');}
  {ignoreChar} {string.append(yytext());}
  . {string.append(yytext());}
}

 <<EOF>>  { return symbol(sym.EOF); }
