                                   -- Chapter 33 - Program 2
with Ada.Text_IO, Random;
use Ada.Text_IO;

procedure TestRan is

   package My_Random is new Random(FLOAT);
   use My_Random;

   package Int_IO is new Ada.Text_IO.Integer_IO(INTEGER);
   use Int_IO;
   package Flt_IO is new Ada.Text_IO.Float_IO(FLOAT);
   use Flt_IO;

   SIZE : constant := 100;
   type MY_ARRAY is array(1..SIZE) of INTEGER;
   Events   : MY_ARRAY;
   Int_Rand : INTEGER;

begin
   Set_Seed;
   Put("The starting value of the seed is ");
   Put(Get_Seed, 3, 6, 0);
   New_Line;
   for Index in 1..12 loop
      Put("The random number is ");
      Put(Random_Number, 3, 6, 0);
      New_Line;
   end loop;

   for Index in 1..SIZE loop
      Events(Index) := 0;
   end loop;

   for Index in 1..10000 loop
      Int_Rand := INTEGER(0.5 + (FLOAT(SIZE) * Random_Number));
      Events(Int_Rand) := Events(Int_Rand) + 1;
   end loop;
   New_Line(2);

   for Index in 1..Size loop
      Put(Events(Index), 4);
   end loop;
end TestRan;




-- Result of execution

-- The starting value of the seed is   0.195333
-- The random number is   0.786685
-- The random number is   0.926148
-- The random number is   0.902392
-- The random number is   0.736096
-- The random number is   0.572030
-- The random number is   0.423565
-- The random number is   0.384310
-- The random number is   0.109521
-- The random number is   0.186004
-- The random number is   0.721385
-- The random number is   0.469050
-- The random number is   0.702704

--  109  97 102  96 107  98  85  87 104 111 113 115 108  99 112 ...
--   97  93 107  91 101  85  91 103  95 101 102  98  95 118 110 ...
--   87 106 103 102  93 129 112  94 102  89  95 104  98  94  98 ...
--   93 106  96 104 119  95  82  97 112  82 104 103  97 107 112 ...
--   93  96 101  98 109  95  94  99  80  74  99  85  76 117 124 ...

-- (Note; only fifteen are listed in each row to stay within
--        70 columns.)

