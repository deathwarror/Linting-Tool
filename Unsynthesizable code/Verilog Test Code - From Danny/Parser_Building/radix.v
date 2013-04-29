module radix(
  input wire in,
  output reg out
  );
  
//  localparam john = 3'h0007;
//  localparam john2 = 4'b   1111;
  localparam john2 = 4'b1111;
  localparam john3 = 'o13;
  localparam john4 = 2'bxx;
  localparam john5 = 2'bzz;
  localparam john6 = 0_2;

  reg [john2: 4'b1110] bob1; // 15:14
//  reg [john4+1:john4-1] bob2;
//  reg [john5: 1'bz] bob3;
  reg [john2:john3] bob4; //15:11

//  reg [7:0] mem1 [3:0];
//  wire [7:0] mem2 [3:0];
  
    
  always@(in)begin
    if(in == 4'b1111)
      out = 1'b1;
    
  end
  
endmodule