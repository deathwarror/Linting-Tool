module Double_Sensitive_Clock_TB;
  reg clk, reset, d;
  wire out;
  
  Double_Sensitive_Clock dsc(.clk(clk), .reset(reset), .d(d), .out(out));
  
  initial begin
    clk=0; reset=0; d=0;
    forever begin
      #5 clk=!clk;
    end
  end
  
  initial begin
    #10 reset=0; d=0;
    #10 reset=1; d=0;
    #10 reset=0; d=0;
    #10 reset=0; d=1;
    #10 reset=0; d=0;
    #10 reset=0; d=0;
    #5 reset=0; d=1;
    #5 reset=0; d=0;
  end
endmodule