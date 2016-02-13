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
integer     = "INTEGER"
float       = "FLOAT"
asig        = "="
declaration = ":"
beginComment = "--"
endInstruction  = ";"
id          ={letter}({letter}*{number}*{especialChar}*)*
letter      = [a-zA-Z]
number      = [0-9]
especialChar = [_-]
ignoreChar = [ \t\r\n\f]

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
  {asig}        {return symbol(Symbol.ASIGNATION);}
  {declaration} {return symbol(Symbol.DECLARATION);}
  {beginComment}          { yybegin(COMMENT); }
  {integer}     {return symbol(Symbol.INTEGER);}
  {float}     {return symbol(Symbol.FLOAT);}
  {endInstruction}  {return symbol(Symbol.END_INSTRUCTION);}
  {id}          {return symbol(Symbol.ID);}
  {ignoreChar} {/* ignore */}
}

<COMMENT> {
    \\n { yybegin(YYINITIAL); }
    {ignoreChar} {/* ignore */}
    . {/* Ignore */}
}

 <<EOF>>  { return symbol(Symbol.EOF); }
