module IncompleteSensListP1(input wire A,B,C, output reg D);
  always @(A or B)begin
    D = A*B;
  end
  always @(C)begin
    D = A*B;
  end
endmodule
