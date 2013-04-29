module ClockErrorsF2(input wire clk,
                   input wire reset,
                   input wire a,
                   output reg d);
always@(posedge clk or posedge reset)begin
  d <= a;
end
endmodule
