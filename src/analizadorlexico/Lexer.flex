package analizadorlexico;
%%
%class Lexer
%unicode
%line
%column
%type Symbol

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
conditionelement = "and"|"or"


condition   = {id}|{id}{ignoreChar}*{relationOperator}{ignoreChar}*{id}{ignoreChar}*({conditionelement}{ignoreChar}*{id}{ignoreChar}*{relationOperator}{ignoreChar}*{id})*

beginif     = "if"
endif       = "end if"
then        = "then"
else        = "else"
elseif      = "elseif"
if          = {beginif}{ignoreChar}*{then}{ignoreChar}*({else}{0,1}{ignoreChar}*|({elseif}{ignoreChar}*{then}{ignoreChar}*)*){ignoreChar}*{endif}

for         = "for"
in          = "in"
inreverse   = "in reverse"
loop        = "loop"
endloop     = "end loop"

while       = "while"




%state STRING
%state COMMENT

%%

<YYINITIAL>{
  {procedure}   {return symbol(Symbol.PROCEDURE);}
  {is}          {return symbol(Symbol.IS);}
  {begin}       {return symbol(Symbol.BEGIN);}
  {end}         {return symbol(Symbol.END);}
  {put}         {return symbol(Symbol.PUT);}
  {newLine}     {return symbol(Symbol.NEW_LINE);}
  {declaration} {return symbol(Symbol.DECLARATION);}
  {asig}        {return symbol(Symbol.ASIGNATION);}
  {sumOperator} {return symbol(Symbol.SUM_OPERATOR,yytext());}
  {multOperator} {return symbol(Symbol.MULT_OPERATOR,yytext());}
  {relationOperator} {return symbol(Symbol.RELATION_OPERATOR,yytext());}
  {beginComment}          { yybegin(COMMENT); }
  {type}     {return symbol(Symbol.TYPE,yytext());}
  {literalBoolean}  {return symbol(Symbol.LITERAL_BOOLEAN,yytext());}
  {literalInteger} {return symbol(Symbol.LITERAL_INT,yytext());}
  {endInstruction}  {return symbol(Symbol.END_INSTRUCTION);}
  {beginif}     {return symbol(Symbol.BEGINIF);}
  {else}        {return symbol(Symbol.ELSE);}
  {elseif}      {return symbol(Symbol.ELSEIF);}
  {endif}       {return symbol(Symbol.ENDIF);}
  {then}        {return symbol(Symbol.THEN);}
  {for}         {return symbol(Symbol.FOR);}
  {in}          {return symbol(Symbol.IN);}
  {inreverse}   {return symbol(Symbol.INREVERSE);}
  {loop}        {return symbol(Symbol.LOOP);}
  {endloop}     {return symbol(Symbol.ENDLOOP);}
  {while}       {return symbol(Symbol.WHILE);}  
  {id}          {return symbol(Symbol.ID,yytext());}
  {conditionelement}    {return symbol(Symbol.CONDITIONELEMENT);}
  {ignoreChar} {/* ignore */}
  . {return symbol(-1,yytext());}
}

<COMMENT> {
    \n { yybegin(YYINITIAL); }
    {ignoreChar} {/* ignore */}
    . {/* Ignore */}
}

 <<EOF>>  { return symbol(Symbol.EOF); }
