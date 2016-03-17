                                       -- Chapter 16 - Program 2
with Ada.Text_IO, Ada.Integer_Text_IO;
use Ada.Text_IO, Ada.Integer_Text_IO;
with CharStak;
use CharStak;

procedure TryStak is

   Example : constant STRING := "This is the first test.";
   Another : constant STRING :=
                  "This is another test and this should not fit.";

   procedure Fill_The_Stack(Input_Line : STRING) is
   begin
      Clear_Stack;
      for Index in 1..Input_Line'LAST loop
         if Is_Full then
            Put_Line("The stack is full, no more added.");
            exit;
         else
            Push(Input_Line(Index));
         end if;
      end loop;
   end Fill_The_Stack;

   procedure Empty_The_Stack is
   Char : CHARACTER;
   begin
      loop
         if Is_Empty then
            New_Line;
            Put_Line("The stack is empty.");
            exit;
         else
            Pop(Char);
            Put(Char);
         end if;
      end loop;
   end Empty_The_Stack;

begin

   Put_Line(Example);
   Fill_The_Stack(Example);
   Empty_The_Stack;

   New_Line;
   Put_Line(Another);
   Fill_The_Stack(Another);
   Empty_The_Stack;

end TryStak;




-- Result of execution

-- This is the first test.
-- .tset tsrif eht si sihT
-- The stack is empty.
--
-- This is another test and should not fit.
-- The stack is full, no more added.
--  dna tset rehtona si sihT
-- The stack is empty.

