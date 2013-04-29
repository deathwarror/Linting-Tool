module Double_Sensitive_Clock(input wire clk, reset, d, output reg out);
  always@(posedge clk or negedge clk or posedge reset)begin
    if(reset)
      out<=0;
    else
      out<=d;
  end
endmodule
