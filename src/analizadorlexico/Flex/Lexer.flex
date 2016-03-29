package analizadorlexico;
import java_cup.runtime.Symbol;
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
literalInteger     = {number}+ | {number}+"#"({number}| [A-F])+"#"
number      = [0-9]
especialChar = [_-]
ignoreChar = [ \t\r\n\f]
conditionelement = "and"|"or";
literalChar = \'.\'
if     = "if"
then        = "then"
else        = "else"
elseif      = "elseif"
for         = "for"
out         = "out"
loop        = "loop"
while       = "while"
exit        = "exit"
when        = "when"
constant    = "constant",
package     = "package",
body        = "body",
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
  {package}             {return symbol(sym.PACKAGE);}
  {others}              {return symbol(sym.OTHERS);}
  {return}              {return symbol(sym.RETURN);}
  {null}                {return symbol(sym.NULL);}
  {body}                {return symbol(sym.BODY);}
  {procedure}           {return symbol(sym.PROCEDURE);}
  {function}            {return symbol(sym.FUNCTION);}
  {constant}            {return symbol(sym.CONSTANT);}
  {conditionelement}    {return symbol(sym.CONDITION_ELEMENT,yytext());}
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
  {type}                {return symbol(sym.TYPE,yytext());}
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
