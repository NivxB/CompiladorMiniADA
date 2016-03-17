                                       -- Chapter 16 - Program 5
-- This program will calculate the number of days old you are.
-- It is a rather dumb program, but illustrates some interesting
-- programming techniques.  It checks all input to see that they
-- are in the correct range before continuing.  Since the number
-- of days can easily exceed the limits of type INTEGER, and we
-- cannot count on LONG_INTEGER being available, a fixed point
-- variable is used for the total number of days since Jan 1, 1880.
-- This program also passes a record to a procedure, where it is
-- modified and returned.

with Ada.Text_IO, Ada.Integer_Text_IO;
use ADa.Text_IO, Ada.Integer_Text_IO;

procedure Age is

   LOW_YEAR    : constant := 1880;
   MAX         : constant := 365.0 * (2100 - LOW_YEAR);
   type AGES is delta 1.0 range -MAX..MAX;
   Present_Age : AGES;

   package Fix_IO is new Ada.Text_IO.Fixed_IO(AGES);
   use Fix_IO;

   type DATE is record
      Month : INTEGER range 1..12;
      Day   : INTEGER range 1..31;
      Year  : INTEGER range LOW_YEAR..2100;
      Days  : AGES;
   end record;

   Today       : DATE;
   Birth_Day   : DATE;

   procedure Get_Date(Date_To_Get : in out DATE) is
   Temp : INTEGER;
   begin
      Put(" month --> ");
      loop
         Get(Temp);
         if Temp in 1..12 then
            Date_To_Get.Month := Temp;
            exit;                       -- month OK
         else
            Put_Line(" Month must be in the range of 1 to 12");
            Put("                    ");
            Put(" month --> ");
         end if;
      end loop;

      Put("                    ");
      Put(" day ----> ");
      loop
         Get(Temp);
         if Temp in 1..31 then
            Date_To_Get.Day := Temp;
            exit;                       -- day OK
         else
            Put_Line(" Day must be in the range of 1 to 31");
            Put("                    ");
            Put(" day ----> ");
         end if;
      end loop;

      Put("                    ");
      Put(" year ---> ");
      loop
         Get(Temp);
         if Temp in LOW_YEAR..2100 then
            Date_To_Get.Year := Temp;
            exit;                       -- year OK
         else
            Put_Line(" Year must be in the range of 1880 to 2100");
            Put("                    ");
            Put(" year ---> ");
         end if;
      end loop;
      Date_To_Get.Days := 365 * AGES(Date_To_Get.Year - LOW_YEAR)
                  + AGES(31 * Date_To_Get.Month + Date_To_Get.Day);

   end Get_Date;

begin
   Put("Enter Today's date; ");
   Get_Date(Today);
   New_Line;

   Put("Enter your birthday;");
   Get_Date(Birth_Day);
   New_Line(2);

   Present_Age := Today.Days - Birth_Day.Days;
   if Present_Age < 0.0 then
      Put("You will be born in ");
      Present_Age := abs(Present_Age);
      Put(Present_Age, 6, 0, 0);
      Put_Line(" days.");
    elsif Present_Age = 0.0 then
      Put_Line("Happy birthday, you were just born today.");
    else
      Put("You are now ");
      Put(Present_Age, 6, 0, 0);
      Put_Line(" days old.");
   end if;

end Age;

