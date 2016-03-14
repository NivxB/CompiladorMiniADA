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
is          = "is"
begin       = "begin"
end         = "end"
put         = "Put"
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
in          = "in"
loop        = "loop"
while       = "while"
exit        = "exit"
when        = "when"
constant    = "constant"

%state STRING
%state COMMENT

%%

<YYINITIAL>{
  {procedure}           {return symbol(sym.PROCEDURE);}
  {constant}            {return symbol(sym.CONSTANT);}
  {conditionelement}    {return symbol(sym.CONDITION_ELEMENT,yytext());}
  {with}                {return symbol(sym.WITH);}
  {is}                  {return symbol(sym.IS);}
  {begin}               {return symbol(sym.BEGIN);}
  {end}                 {return symbol(sym.END);}
  {put}                 {return symbol(sym.PUT);}
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
  {in}                  {return symbol(sym.IN);}
  {loop}                {return symbol(sym.LOOP);}
  {while}               {return symbol(sym.WHILE);}
  {id}                  {return symbol(sym.ID,yytext());}

  {ignoreChar} {/* ignore */}
  \"        {string.setLength(0); yybegin(STRING);}
  . {return symbol(-1,yytext());}

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
