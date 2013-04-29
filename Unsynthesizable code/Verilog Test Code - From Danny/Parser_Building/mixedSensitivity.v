module mixedSensitivity( input wire in1, 
input wire in2,
output reg out);
  always@(posedge in1 or in2)begin
    out <= in1;
  end
  always@(in1 or negedge in2)begin
    out <= in1;
  end
  always@(in1 or in2)begin
    out <= in1;
  end
  always@(posedge in1 or negedge in2)begin
    out <= in1;
  end
endmodule