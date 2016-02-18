package analizadorlexico;
%%
%class Lexer
%unicode
%line
%column
%caseless
%ignorecase
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
  {procedure}   {return symbol(Symbol.PROCEDURE);}
  {with}        {return symbol(Symbol.WITH);}
  {is}          {return symbol(Symbol.IS);}
  {begin}       {return symbol(Symbol.BEGIN);}
  {end}         {return symbol(Symbol.END);}
  {put}         {return symbol(Symbol.PUT);}
  {newLine}     {return symbol(Symbol.NEW_LINE);}
  {declaration} {return symbol(Symbol.DECLARATION);}
  {asig}        {return symbol(Symbol.ASIGNATION);}
  {exit}        {return symbol(Symbol.EXIT);}
  {when}        {return symbol(Symbol.WHEN);}
  {comma}       {return symbol(Symbol.COMMA);}
  {openParenthesis} {return symbol(Symbol.OPEN_PARENTHESIS);}
  {closeParenthesis} {return symbol(Symbol.CLOSE_PARENTHESIS);}
  {sumOperator} {return symbol(Symbol.SUM_OPERATOR,yytext());}
  {multOperator} {return symbol(Symbol.MULT_OPERATOR,yytext());}
  {relationOperator} {return symbol(Symbol.RELATION_OPERATOR,yytext());}
  {beginComment}          { yybegin(COMMENT); }
  {type}     {return symbol(Symbol.TYPE,yytext());}
  {literalBoolean}  {return symbol(Symbol.LITERAL_BOOLEAN,yytext());}
  {literalInteger} {return symbol(Symbol.LITERAL_INT,yytext());}
  {endInstruction}  {return symbol(Symbol.END_INSTRUCTION);}
  {literalChar} {return symbol(Symbol.LITERAL_CHAR,yytext());}
  {if}     {return symbol(Symbol.IF);}
  {else}        {return symbol(Symbol.ELSE);}
  {elseif}      {return symbol(Symbol.ELSEIF);}
  {then}        {return symbol(Symbol.THEN);}
  {for}         {return symbol(Symbol.FOR);}
  {in}          {return symbol(Symbol.IN);}
  {loop}        {return symbol(Symbol.LOOP);}
  {while}       {return symbol(Symbol.WHILE);}
  {id}          {return symbol(Symbol.ID,yytext());}

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
  \"  {yybegin(YYINITIAL); return symbol(Symbol.LITERAL_STRING,string.toString()); }
  \\\" {string.append('\"');}
  \\  {string.append('\\');}
  {ignoreChar} {string.append(yytext());}
  . {string.append(yytext());}
}

 <<EOF>>  { return symbol(Symbol.EOF); }
