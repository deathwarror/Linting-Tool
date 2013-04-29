module NAR_NSS (input wire clock, reset, in, output reg out);
  always@(posedge clock or negedge reset)begin
    if(!reset)
      out <= 1'b0;
    else
      out <= in;
  end
endmodule


