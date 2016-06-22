procedure piramide is
      x:Integer;
   	function validarPositivo(x:Integer) return Integer is
      begin
         while x < 0 loop
            put("Ingrese un numero positivo: ");
            get(x);
         end loop;
         return x;
      end validarPositivo;
      procedure printPiramide(num: Integer) is
         i,j:Integer;
      begin
         num := validarPositivo(num);
         num := 2*(num - 1);
         for i:=0 to (num/2) 
            for j:=0 to (num - i) 
               put(" ");
            end for; 
            for j:=0 to (i*2) 
               put(" ");
            end for; 
            put("\n");
         end for;
         for i:=0 to (num/2) 
            for j:=(num/2) to (num - i) 
               put(" ");
            end for; 
            for j:=0 to (i*2) 
               put("*");
            end for; 
            for j:=1 to (num - i*2) 
               put(" ");
            end for; 
            for j:=0 to (i*2) 
               put("*");
            end for; 
            put("\n");
         end for;
      end printPiramide;
begin
      put("Ingrese un numero positivo: ");
      get(x);
      printPiramide(x);
end calificacion;