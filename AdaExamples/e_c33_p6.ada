                                    -- Chapter 33 - Program 6

package One_Man is

   type AVAILABILITY is (AVAILABLE, IN_USE);
   Fork_Usage : array(1..5) of AVAILABILITY;

   type ACTIVITY is (THINKING, HAS_LEFT_FORK, HAS_BOTH_FORKS);
   Philosopher_Activity : array(1..5) of ACTIVITY;

   task type EATING_OR_THINKING is
      entry Start(Left_Fork, Right_Fork : INTEGER);
   end EATING_OR_THINKING;

end One_Man;




with Ada.Text_IO, Ada.Integer_Text_IO, Ada.Calendar, Random;
use Ada.Text_IO, Ada.Integer_Text_IO;

package body One_Man is

   package My_Random is new Random(FLOAT);
   use My_Random;

   procedure Get_Fork(Identifier    : INTEGER;
                      Left_Or_Right : INTEGER) is
   begin
      Fork_Usage(Identifier) := IN_USE;
   end Get_Fork;

   procedure Return_Fork(Identifier : INTEGER) is
   begin
      Fork_Usage(Identifier) := AVAILABLE;
   end Return_Fork;

   task body EATING_OR_THINKING is
      Left, Right : INTEGER;
      Ident : INTEGER renames Left;
   begin
      accept Start(Left_Fork, Right_Fork : INTEGER) do
         Left := Left_Fork;
         Right := Right_Fork;
         Philosopher_Activity(Ident) := THINKING;
      end Start;
      loop
         Put("Philosopher"); 
         Put(Ident, 2);
         Put_Line(" is thinking.");
         delay Calendar.DAY_DURATION(Random_Number);
         loop
            delay 0.10;
            exit when Fork_Usage(Left) = AVAILABLE;
         end loop;
         Get_Fork(Ident, Left);
         Philosopher_Activity(Ident) := HAS_LEFT_FORK;
         Put("Philosopher");  
         Put(Ident, 2);
         Put_Line(" has his left fork");
         delay Calendar.DAY_DURATION(Random_Number);
         loop
            delay 0.10;
            exit when Fork_Usage(Right) = AVAILABLE;
         end loop;
         Get_Fork(Ident,Right);
         Philosopher_Activity(Ident) := HAS_BOTH_FORKS;
         Put("Philosopher"); 
         Put(Ident, 2);
         Put_Line(" has his right fork and is eating");
         delay Calendar.DAY_DURATION(Random_Number);
         Return_Fork(Left);
         Return_Fork(Right);
         Philosopher_Activity(Ident) := THINKING;
      end loop;
   end EATING_OR_THINKING;

begin

   Set_Seed;           -- Initialize the random number generator

   for Index in 1.. 5 loop
      Fork_Usage(Index) := AVAILABLE;
      Philosopher_Activity(Index) := THINKING;
   end loop;

end One_Man;




with Ada.Text_IO, One_Man;
use Ada.Text_IO, One_Man;

procedure Philos is

                             -- Declare all 5 tasks
   Philosopher_1 : One_Man.EATING_OR_THINKING;
   Philosopher_2 : One_Man.EATING_OR_THINKING;
   Philosopher_3 : One_Man.EATING_OR_THINKING;
   Philosopher_4 : One_Man.EATING_OR_THINKING;
   Philosopher_5 : One_Man.EATING_OR_THINKING;

begin

                             -- Assign forks to Philosophers & start
   Philosopher_1.Start(Left_Fork => 1, Right_Fork => 2);
   Philosopher_2.Start(Left_Fork => 2, Right_Fork => 3);
   Philosopher_3.Start(Left_Fork => 3, Right_Fork => 4);
   Philosopher_4.Start(Left_Fork => 4, Right_Fork => 5);
   Philosopher_5.Start(Left_Fork => 5, Right_Fork => 1);

   loop                      -- Watch for deadlock to occur
      delay 0.01;
      if Philosopher_Activity(1) = HAS_LEFT_FORK and
         Philosopher_Activity(2) = HAS_LEFT_FORK and
         Philosopher_Activity(3) = HAS_LEFT_FORK and
         Philosopher_Activity(4) = HAS_LEFT_FORK and
         Philosopher_Activity(5) = HAS_LEFT_FORK then exit;
      end if;
   end loop;

   Put_Line("Deadlock detected, program operation aborted.");

   abort Philosopher_1, Philosopher_2, Philosopher_3,
         Philosopher_4, Philosopher_5;

end Philos;

