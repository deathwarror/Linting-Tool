`default_nettype none
module negedff(input wire clk,reset,set,data_in,
               output reg data_out);
always @(negedge clk or negedge set)begin
  if (~reset)
    data_out = data_in;
  else if(~set)
    data_out = 1'b0;
  else
    data_out = 1'b0;
  end
endmodule
