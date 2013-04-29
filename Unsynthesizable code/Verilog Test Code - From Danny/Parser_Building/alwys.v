module alwys (
  input wire in,
  input reg clk, output reg bob);
  
  reg boob;
  
  always@(posedge clk or negedge bob)begin
    bob <= 0;
    boob <= 1;
  end
  always@(bob)begin
    if( bob == boob)begin
      bob = 1;
    end
    else if(((bob+1)-1) != boob)
      bob = 0;
    else begin
      bob = 0;
    end
  end
  reg onesies;
endmodule
