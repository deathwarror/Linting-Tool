module vector(
  input wire one,
  output reg two
  );
  reg [2:0] three;
  wire [  3:    0] four;
  reg [2:1] five;
//  reg [7:0] ram[0:4095];      // 4096 memory cells that are 8 bits wide
/*
  always@(one)begin
    three[1] = 1;
    two = four[1];
  end
*/  
endmodule
