module timingControl(
  input wire in,
  output reg out);
  reg bob;
  always@(posedge in)begin
    out <= in;
  end
  always@(bob)begin
    #1
    wait(in);
    @(posedge in);
  end
endmodule
