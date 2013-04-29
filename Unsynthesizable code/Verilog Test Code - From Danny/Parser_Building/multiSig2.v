module multiSig(
  input wire one,
  output reg two,
  output wire three
  );
  wire four;
  reg five;
  
  always@(one)begin
    two = 0;
  end
  
  always@(two)begin
    five = 0;
    two = 1;
  end
  
  always@(three)begin
    five = 1;
  end
  
  assign three = 0;
//  assign three = 1;
  assign four = 0;
  
endmodule


