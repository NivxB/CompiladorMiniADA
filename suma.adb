procedure pruebaSuma is
    a,b,c: Integer;
    resFact: Integer;
    Function fact(num:integer) return Integer is
    begin
      if num = 0 then
        return 1;
      else
        PUT("RECURSIVE CALL: ");
        PUT(num);
        PUT("\n");
        return num * fact(num - 1);
      end if;

    end fact;
    Function d return Integer is
    begin
      return 0;
    end d;
    Function d(i:Integer) return Integer is
        f: Integer;
    begin
      f:= 5;
      i:= i + 1;
      i := f;
      f := i + 24;
      return f + a;
    end d;
    procedure test is
    begin
    end test;
begin
    --test := 0;
    a := 5;
    resFact:= fact(a);
    put("Resultado: ");
    put(resFact);
    -- a:= 3;
end pruebaSuma;
