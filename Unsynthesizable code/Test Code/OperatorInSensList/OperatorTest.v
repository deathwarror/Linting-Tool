module OperatorTest(input wire a,input wire b, output reg c);
  always @(a*b)begin
    c = a*b;
  end
  always @(a/b)begin
    c = a*b;
  end
  always @(a+b)begin
    c = a*b;
  end
  always @(a-b)begin
    c = a*b;
  end
  always @(a%b||a*b)begin
    c = a*b;
  end
  always @(!a)begin
    c = a*b;
  end
  always @(a&&b)begin
    c = a*b;
  end
  always @(a||b)begin
    c = a*b;
  end
  always @(a<b)begin
    c = a*b;
  end
  always @(a>b)begin
    c = a*b;
  end
  always @(a==b)begin
    c = a*b;
  end
  always @(~b)begin
    c = a*b;
  end
  always @(!b)begin
    c = a*b;
  end
  always @(^b)begin
    c = a*b;
  end
  always @(a ? 1:2)begin
    c = a*b;
  end
endmodule