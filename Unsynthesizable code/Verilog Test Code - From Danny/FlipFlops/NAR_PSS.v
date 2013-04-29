module NAR_PSS (input wire clock, reset, set, in, output reg out);
  always@(posedge clock or negedge reset)begin
    if(!reset)
      out <= 1'b0;
    else if(set)
      out <= 1'b1;
    else
      out <= in;
  end
endmodule


