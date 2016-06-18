procedure pruebaSuma is
    a,b,c: Integer;
    resFact: Integer;
    Function fact(num:integer) return Integer is
    begin
      if num = 0 then
        return 1;
      else
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
    put(a);

    put("test");
    if(a>c or b>c and a=0 or a<c) then
      c:=8;
    end if;
    for a:=0 to 10
      b:= 3;
    end for;
    while a > 5 loop
      b:=3;
    end loop;
    -- a:= 3;
end pruebaSuma;
