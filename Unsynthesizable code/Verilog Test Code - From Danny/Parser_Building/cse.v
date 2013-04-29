module cse(
  input wire tom,
  output reg bobby);
  reg [1:0] twosies;
  always@(tom)begin
    case(tom == (1+0))
//      0:bobby=0;
      {twosies[1],tom}: bobby=1'bx;
      (twosies[1:0]==1): bobby=0;
      tom: bobby = 1'bx;
      1:begin
        bobby=1;
        if(bobby == (1+0))
          bobby = 1;
        else
          bobby = 1;
      end
      (1+0): bobby=1;
      default:bobby=1'bx;
      
    endcase
  end  
endmodule
