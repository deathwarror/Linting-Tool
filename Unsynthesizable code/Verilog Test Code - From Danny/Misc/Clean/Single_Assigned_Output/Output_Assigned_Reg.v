module Output_Assigned_Reg(input wire clk, reset, output wire out);
  reg [2:0]bob;
  always@(posedge clk or negedge reset)begin
    if(!reset)
      bob<=0;
    else
      bob= bob+1;
  end
  
  assign out = (bob==3'b110);
endmodule
