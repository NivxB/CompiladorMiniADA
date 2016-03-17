                                       -- Chapter 16 - Program 1
package CharStak is

procedure Push(In_Char : in CHARACTER);  -- In_Char is added to the
                                         -- stack if there is room.

procedure Pop(Out_Char : out CHARACTER); -- Out_Char is removed from
                                         -- stack and returned if a
                                         -- character is on stack.
                                         -- else a blank is returned

function Is_Empty return BOOLEAN;        -- TRUE if stack is empty

function Is_Full return BOOLEAN;         -- TRUE if stack is full

function Current_Stack_Size return INTEGER;

procedure Clear_Stack;                   -- Reset the stack to empty

end CharStak;





package body CharStak is

Maximum_Size : constant := 25;
Stack_List : STRING(1..Maximum_Size); -- The stack itself, purposely
                                      -- defined very small.
Top_Of_Stack : INTEGER := 0;          -- This will always point to
                                      -- the top entry on the stack.

procedure Push(In_Char : in CHARACTER) is
begin
   if not Is_Full then
      Top_Of_Stack := Top_Of_Stack + 1;
      Stack_List(Top_Of_Stack) := In_Char;
   end if;
end Push;


procedure Pop(Out_Char : out CHARACTER) is
begin
   if Is_Empty then
      Out_Char := ' ';
   else
      Out_Char := Stack_List(Top_Of_Stack);
      Top_Of_Stack := Top_Of_Stack - 1;
   end if;
end Pop;


function Is_Empty return BOOLEAN is
begin
   return Top_Of_Stack = 0;
end Is_Empty;


function Is_Full return BOOLEAN is
begin
   return Top_Of_Stack = Maximum_Size;
end Is_Full;


function Current_Stack_Size return INTEGER is
begin
   return Top_Of_Stack;
end Current_Stack_Size;


procedure Clear_Stack is
begin
   Top_Of_Stack := 0;
end Clear_Stack;

end CharStak;

