module NAR_NAS (input wire clock, reset, set, in, output reg out);
  always@(posedge clock or negedge reset or negedge set)begin
    if(!reset)
      out <= 1'b0;
    else if(!set)
      out <= 1'b1;
    else
      out <= in;
  end
endmodule


