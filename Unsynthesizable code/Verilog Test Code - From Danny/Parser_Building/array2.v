module array2( input wire signed [1:0]in1,  input wire in2,
  output [2:0]out1, out2, reg [1:0] out3,
  output wire out4,
  output arr1,
  input arr2
);

  
//  reg bob1 [1:0];
//  reg bob2 [0:1];
  reg [2:0]bob3 [2:0], bob4, bob5[1:0];
  reg [3:0] bob6;
  reg bob7[3:0];
  
  assign out4 = 
    bob6[1] == bob3[
                  bob5[0][3-1:0+1]
                  ]
                  [1:0];
  
  always@(bob3 or in2 or in1)begin//bob3[in1])begin
//    bob6[1:0] = 
//        bob3[0][1:0];
//    bob6[1:0] = 
//        bob3  [in1[out2[0]]]  [1:0];
//    bob6[in1[0]-bob7[in1]] = bob3[
    if(in1[1] == bob5[1][1:0])begin
    end
    bob6[1] = bob3[
                  bob5[0][3-1:0+1]
                  ]
                  [1:0];
    bob7[1] = bob3[0][0];
    
  end
  
//  assign out1 = bob3[2]==1;
//  assign out2[2] = bob4[2]==1;
  
endmodule

