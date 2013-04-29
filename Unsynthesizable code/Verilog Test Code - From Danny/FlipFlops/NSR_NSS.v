module NSR_NSS (input wire clock, reset, set, in, output reg out);
  always@(posedge clock)begin
    if(!reset)
      out <= 1'b0;
    else if(!set)
      out <= 1'b1;
    else
      out <= in;
  end
endmodule


