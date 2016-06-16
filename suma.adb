procedure pruebaSuma is
   	a,b,c: Integer;
    e: Boolean;
    Function d return Integer is
    begin
      return 0;
    end d;
    Function d(i:Integer) return Integer is
    begin
      return i + 2;
    end d;
    Function d(i:Integer) return Boolean is
    begin
      return i + 2;
    end d;
    procedure test is
    begin
    end test;
begin
   	--test := 0;
    test();
   	b:= 2 + 3;
   	a:= (b - 3 * 2) / 3 - 5;
   	c:= d(5);
    e:= d(5);
   	if(a>c or b>c and a=0 or a<c) then
   		c:=8;
   	end if;
    for a:=0 to 10
      b:= 3;
    end for;
    while e loop
      b:=3;
    end loop;
   	-- a:= 3;
end pruebaSuma;
