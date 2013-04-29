module VectorMultiplDriven(input wire a,input wire clk,input wire reset, input wire [1:0]b, output reg [1:0] out);
  always @(posedge clk or posedge reset)begin
    if(reset) begin
      out <= 'b0;
    end
    else begin
      out[1] <= a;
    end
  end
  always @(b[1] or b[0])begin
    out = b;
  end
endmodule