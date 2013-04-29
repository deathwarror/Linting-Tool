module cse1(
  input wire one,
  output reg two);
  
  always@(one)begin
    case(one)
      0:two=0;
      1:two=1;
    endcase
  end
endmodule
