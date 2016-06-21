procedure Seno is
   function pow(base:Integer ; exp:Integer) return Integer is
      retVal: Integer;
      i:Integer;
   begin
      retVal := base;
      for i:=1 to exp
         retVal := retVal * base;
      end for;
      return retVal;
   end pow;
   function yas(x:Integer; n:Integer) return Integer is
      result:Integer;
      num:Integer;
      den:Integer;
      i:Integer;
   begin
      for i:= 0 to n 
         num := pow(x,(2*i) + 1);
         den := fact((2*i) + 1);
         result := result + (num / den);
      end for;
   end yas;
   function fact(n:Integer) return Integer is
      retVal: Integer;
      i:Integer;
   begin
      retVal := 1;
      for i:=2 to n
         retVal := retVal * i;
      end for;
      return retVal;
   end fact;
   val:Integer;
begin
   Put("Seno yas ");
   val := yas(2,2);
   put(val);

end Numbers;