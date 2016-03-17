                                     -- Chapter 33 - Program 1
-- This is a generic package to generate random numbers in the
--  range of 0.00000 to just less than 1.00000 with as many
--  significant digits as the type FLOAT_ITEM.  This package uses
--  the Linear Congruential Method of random number generation as
--  discussed in "The Art of Computer Programming" volume 2 by
--  Donald Knuth.  The method used follows;
--
--  X(n + 1) = (A * X(n) + C) mod M
--
--  X(n + 1) is the new random number
--  X(n)     is the previous random number or the seed
--  M        is 1.0 for the floating point system
--  A        is 7.0 for the floating point system
--  C        is 13.0 / 31.0 for the floating point system
--  X(0)     is zero by default
--  X(0)     is the number provided if forced with Force_Seed
--  X(0)     is generated as follows when Set_Seed is called;
--        1.   The real time clock is read from the system
--        2.   The hours and minutes are stripped off
--        3.   The resulting number of seconds are divided by
--              60.0 to get the fraction of a minute that has
--              elapsed since midnight

generic
   type FLOAT_ITEM is digits <>;
package Random is

        -- This procedure uses the system clock to set the seed.
   procedure Set_Seed;

        -- This procedure sets the seed to the input value.
   procedure Force_Seed(Start_Seed : FLOAT_ITEM);

        -- This Function returns the current seed which is also
        --  the value of the previous random number returned.
   function Get_Seed return FLOAT_ITEM;

        -- This function returns a random number from 0.0 to 1.0
   function Random_Number return FLOAT_ITEM;

end Random;




with Ada.Text_IO, Calendar;
use Ada.Text_IO, Calendar;

package body Random is

X_initial : FLOAT_ITEM := 0.0;
M         : FLOAT_ITEM := 1.0;
A         : FLOAT_ITEM := 7.0;
C         : FLOAT_ITEM := 13.0 / 31.0;


procedure Set_Seed is
   Time_And_Date    : TIME;
   All_Day          : DAY_DURATION;
   Minutes          : FLOAT_ITEM;
   Int_Minutes      : INTEGER;
   Part_Of_A_Minute : FLOAT_ITEM;
begin
   Time_And_Date := Clock;                -- Get the time and date
   All_Day := Seconds(Time_And_Date);     -- Seconds since midnight
   Minutes := FLOAT_ITEM(All_Day) / 60.0;   -- Floating type Minutes
   Int_Minutes := INTEGER(Minutes - 0.5); -- Integer type minutes
   Part_Of_A_Minute := FLOAT_ITEM(All_Day)
                               - 60.0 * FLOAT_ITEM(Int_Minutes);
   X_Initial := Part_Of_A_Minute / 60.0;
end Set_Seed;


procedure Force_Seed(Start_Seed : FLOAT_ITEM) is
Temp : FLOAT_ITEM;
Natural_Temp : NATURAL;
begin
   Natural_Temp := NATURAL(Start_Seed - 0.5); -- Subtract 0.5 because
                                              -- the type conversion
                                              -- rounds the result.
   Temp := Start_Seed - FLOAT_ITEM(Natural_Temp);
   X_Initial := Start_Seed;
exception
   when Constraint_Error =>
      Put_Line("Seed out of range, ignored");
end Force_Seed;


function Get_Seed return FLOAT_ITEM is
begin
   return X_Initial;
end Get_Seed;


function Random_Number return FLOAT_ITEM is
   Temp         : FLOAT_ITEM;
   Natural_Temp : NATURAL;   -- Cannot exceed (7 + 13/31)
begin
   Temp := A * X_Initial + C;
   Natural_Temp := NATURAL(Temp - 0.5);     -- Subtract 0.5 because
                                            -- the type conversion
                                            -- rounds the result.
   Temp := Temp - FLOAT_ITEM(Natural_Temp);
   X_Initial := Temp;
   return Temp;
end Random_Number;

end Random;

