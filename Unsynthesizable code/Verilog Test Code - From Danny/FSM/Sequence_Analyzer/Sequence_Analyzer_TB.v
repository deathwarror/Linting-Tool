module Sequence_Analyzer_TB;
  reg serialInput, clk, reset;
  wire out;
  
  Sequence_Analyzer sa(.serialInput(serialInput), .clk(clk), .reset(reset), .out(out));
  
  initial begin
    serialInput=0; clk=0; reset=0;
    forever begin
      #5 clk=!clk;
    end
  end
  
  initial begin
    #10 reset=0; serialInput=0;
    #10 reset=1; serialInput=0;
    #10 reset=0; serialInput=0;
    #10 reset=0; serialInput=1;
    #10 reset=0; serialInput=1;
    #10 reset=0; serialInput=0;
    #10 reset=0; serialInput=0;
    #10 reset=0; serialInput=1;
    #10 reset=0; serialInput=0;
    #10 reset=0; serialInput=1;
    #10 reset=0; serialInput=0;
    #10 reset=1; serialInput=0;
    #10 reset=0; serialInput=0;
  end
endmodule