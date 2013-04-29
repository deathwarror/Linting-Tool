module VectorArrayInSensList(input wire A,B, output reg C,output reg out);
  reg [2:0] Array [2:0];
  reg [2:0] vector;
  
  always @(Array or vector)begin
    C = A;
  end
  always @(Array[1][1]or vector[1])begin
    out = B;
  end
endmodule