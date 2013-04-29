module AssignmentTest(input wire a,input wire b, output reg c);
  always@(a or b)begin
    c = a;
  end
  always@(a or b)begin
    c <= a;
  end
  always@(posedge a or posedge b)begin
    c <= a;
  end
  always@(posedge a or posedge b)begin
    c = a;
  end
  
endmodule