module Output_Assigned_From_Reg_Inputs(input wire clk, reset, inA, output wire out);
  reg [2:0]bob;
  always@(posedge clk or negedge reset)begin
    if(!reset)
      bob<=0;
    else
      bob= bob+1;
  end
  
  assign out = (bob==3'b110)||inA;
endmodule
