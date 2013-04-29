module Twice_Assigned_Output(input wire a, b, c, output wire out);
  reg [2:0] two_input;
  assign out = a+b;
  assign out = b+c;  
endmodule
