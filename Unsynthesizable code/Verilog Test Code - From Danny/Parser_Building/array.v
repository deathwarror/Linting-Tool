module array(in1, in2, 
  out1,
  out2,
  arr1, arr2
);
  input wire signed [1:0]in1;
  input wire in2;
  output [1:0]out1;
  wire [1:0]out1;
  output wire out2;
  output arr1;
  output arr2;
  
//  reg bob1 [1:0];
//  reg bob2 [0:1];
  reg [2:0]bob3 [2:0], bob4, bob5[1:0];
  
//  assign out1 = bob3[2]==1;
//  assign out2 = bob4[2]==1;
  
endmodule
