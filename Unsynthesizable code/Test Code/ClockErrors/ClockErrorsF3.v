module ClockErrorsF3(input wire clk,
                   input wire reset,
                   input wire a,
                   output reg d);
always@(posedge clk or posedge reset)begin
    if(reset)begin
      d <= 1'b0;
    end
    else if(clk) begin
      d <= a;
    end
  end
endmodule

