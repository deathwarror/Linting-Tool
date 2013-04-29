module latches(
  input wire one, two,
  output reg three, four, five, six);
  
  always@(one)begin
    if(one)begin
      three=1;
      four=1;
    end
    else if(!one)begin
      four=0;
    end
  end
  
  always@(two)begin
    case(two)
      0:begin
        five=1;
      end
      1:begin
        six=1;
      end
      
    endcase
  end
  
endmodule
