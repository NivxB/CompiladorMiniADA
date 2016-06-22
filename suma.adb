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
    put("Con FOR");
    a := 5;
    for b:=0 to 5
      resFact:= fact(b);
      put("Resultado: ");
      put(resFact);
      put("\n");
      put("\n");
    end for;
    
    put("Con While");
    put("\n");
    b:=0;
    while b <= 5 loop
      resFact:= fact(b);
      put("Resultado: ");
      put(resFact);
      put("\n");
      put("\n");
      b := b + 1;
    end loop;


    put("Escriba num iteraciones");
    get(a);
    if a > 5 then
      for b:=0 to a
        resFact:= fact(b);
        put("Resultado: ");
        put(resFact);
        put("\n");
        put("\n");
      end for;
    elsif a = 5 then
      resFact:= fact(10);
      put("Resultado: ");
      put(resFact);
    else
      put("ELSE");
    end if;
    -- a:= 3;
end pruebaSuma;
