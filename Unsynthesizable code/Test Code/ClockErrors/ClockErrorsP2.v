module ClockErrorsP2(input wire clk,
					input wire clk2,
                   input wire reset,
                   input wire a,
                   output reg d,
				   output reg d2);
always@(posedge clk or posedge reset)begin
    if(reset)begin
      d <= 1'b0;
    end
    else if(clk) begin
      d <= a;
    end
  end
  always@(posedge clk or posedge reset)begin
    if(reset)begin
      d2 <= 1'b0;
    end
    else if(clk) begin
      d2 <= a;
    end
  end
endmodule

