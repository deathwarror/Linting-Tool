module bnbf1(input wire clk, input wire reset,input wire data1,input wire data2,input wire select,output reg out);
  assign data1 = 1'b1;
  always @(posedge clk or posedge reset)begin
    if(reset == 1'b1)begin
      out <= 1'b0;
    end
    else if(select ==1'b0)begin
      out <= out;
    end
    else begin
      data2 <= data2;
    end
  end//end always
endmodule