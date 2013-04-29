module bnbp4(input wire clk,input wire reset,input wire data1,input wire data2,input wire order,
             output reg[1:0]out);
  always @(posedge clk or posedge reset)begin
    if(reset == 1'b1) begin
      out = 2'b00;
    end
    else if(order == 1'b0) begin
      out[1] = data1;
      out[0] = data2;
    end
    else begin
      out[0] = data1;
      out[1] = data2;
    end
  end//end always
endmodule
