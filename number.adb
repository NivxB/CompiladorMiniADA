procedure Numbers is
   Mike, Alice: Integer;
   John_Smith: Integer;
begin
   Put("Enter a number Mike: ");
   Get(Mike);
   Put("Enter a number Alice: ");
   Get(Alice);
   John_Smith := 3*Mike + 2*Alice + 11;
   Put("3*Mike + 2*Alice + 11 is ");
   Put(John_Smith);
   put("\n");

   John_Smith := Mike + Alice + 1000000;
   Put("A million more than Mike and Alice ");
   Put(John_Smith);
   put("\n");



end Numbers;