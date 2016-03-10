package analizadorlexico;
import java_cup.runtime.Symbol;
%%
%class Lexer
%unicode
%line
%column
%caseless
%ignorecase
%type Symbol
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
literalInteger     = {number}+
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

%state STRING
%state COMMENT

%%

<YYINITIAL>{
  {procedure}           {return Symbol(sym.PROCEDURE);}
  {with}                {return Symbol(sym.WITH);}
  {is}                  {return Symbol(sym.IS);}
  {begin}               {return Symbol(sym.BEGIN);}
  {end}                 {return Symbol(sym.END);}
  {put}                 {return Symbol(sym.PUT);}
  {newLine}             {return Symbol(sym.NEW_LINE);}
  {declaration}         {return Symbol(sym.DECLARATION);}
  {asig}                {return Symbol(sym.ASIGNATION);}
  {exit}                {return Symbol(sym.EXIT);}
  {when}                {return Symbol(sym.WHEN);}
  {comma}               {return Symbol(sym.COMMA);}
  {openParenthesis}     {return Symbol(sym.OPEN_PARENTHESIS);}
  {closeParenthesis}    {return Symbol(sym.CLOSE_PARENTHESIS);}
  {sumOperator}         {return Symbol(sym.SUM_OPERATOR,yytext());}
  {multOperator}        {return Symbol(sym.MULT_OPERATOR,yytext());}
  {relationOperator}    {return Symbol(sym.RELATION_OPERATOR,yytext());}
  {beginComment}          { yybegin(COMMENT); }
  {type}                {return Symbol(sym.TYPE,yytext());}
  {literalBoolean}      {return Symbol(sym.LITERAL_BOOLEAN,yytext());}
  {literalInteger}      {return Symbol(sym.LITERAL_INT,yytext());}
  {endInstruction}      {return Symbol(sym.END_INSTRUCTION);}
  {literalChar}         {return Symbol(sym.LITERAL_CHAR,yytext());}
  {if}                  {return Symbol(sym.IF);}
  {else}                {return Symbol(sym.ELSE);}
  {elseif}              {return Symbol(sym.ELSEIF);}
  {then}                {return Symbol(sym.THEN);}
  {for}                 {return Symbol(sym.FOR);}
  {in}                  {return Symbol(sym.IN);}
  {loop}                {return Symbol(sym.LOOP);}
  {while}               {return Symbol(sym.WHILE);}
  {id}                  {return Symbol(sym.ID,yytext());}

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
  \"  {yybegin(YYINITIAL); return Symbol(sym.LITERAL_STRING,string.toString()); }
  \\\" {string.append('\"');}
  \\  {string.append('\\');}
  {ignoreChar} {string.append(yytext());}
  . {string.append(yytext());}
}

 <<EOF>>  { return Symbol(sym.EOF); }
