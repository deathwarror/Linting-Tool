module ClockErrorsF1(input wire clk,
                   input wire reset,
                   input wire a,
                   output reg d);
always@(posedge clk)begin
    if(reset)begin
      d <= 1'b0;
    end
    else begin
      d <= a;
    end
  end
endmodule
