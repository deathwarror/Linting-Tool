module Vending_Machine_TB;
  reg clk, reset, N,D;
  wire OK;
  
  Vending_Machine vm(.clk(clk),.reset(reset),.N(N),.D(D));
  
  initial begin
    clk=0; reset=0; N=0; D=0;
    forever begin
      #5 clk=!clk;
    end
  end
  
  initial begin
    #10 reset=0; N=0; D=0;
    #10 reset=1; N=0; D=0;
    #10 reset=0; N=1; D=0;
    #10 reset=0; N=1; D=0;
    #10 reset=0; N=1; D=0;
    #10 reset=0; N=1; D=0;
    #10 reset=0; N=1; D=0;
    #10 reset=0; N=0; D=1;
    #10 reset=0; N=0; D=1;
    #10 reset=0; N=0; D=1;
    #10 reset=0; N=0; D=1;
    #10 reset=0; N=1; D=0;
    #10 reset=0; N=1; D=0;
  end
endmodule