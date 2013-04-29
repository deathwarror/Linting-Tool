module vector3(
  input wire one,
  input wire one2,
  output reg two
  );
  parameter bob = 5;
  parameter charles = 0;
  
  reg [bob:0] three;
  reg [bob-1:0] four;
  reg [bob-1:charles+1] five;
  
  always@(one)begin
    two = 0;
    three[1] = 0;
    four[2:0] = 0;
    five[2:0] = 1; // will need to be checked
  end
  
  always@(one2)begin
    four[1] = 1;
  end

endmodule




