module vector(
  input wire one,
  output reg two
  );
  parameter bob = 5;
  parameter charles = 0;
  
  reg [bob:0] three;
  reg [bob-1:0] four;
  reg [bob-1:charles+1] five;

endmodule


