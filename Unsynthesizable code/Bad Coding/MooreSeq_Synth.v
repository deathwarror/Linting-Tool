module MooreSeq_final(input clk, reset,A, output reg B);
  
  parameter s_0 = 3'b000;
  parameter s_1 = 3'b001;
  parameter s_2 = 3'b010;
  parameter s_3 = 3'b011;
  parameter s_4 = 3'b100;
  parameter s_5 = 3'b101;
  parameter s_6 = 3'b110;
  parameter s_7 = 3'b111;
  reg [2:0] current;
  reg [2:0] next;
  
      
  always @(posedge clk or posedge reset)begin//current state next state logic    
    if (reset == 1'b1)begin
      current = s_0;
      next = s_0;
    end   
    else begin
    current = next;
    case (current)//check the current state
      s_0:begin
        if(A == 1'b1)//if input was a 1
          next = s_1;
        else
          next = s_0;
      end//end state s_0
      s_1:begin
        if(A == 1'b1)//if input was a 1
          next = s_3;
        else
          next = s_2;
      end//end state s_0
      s_2:begin
        if(A == 1'b1)//if input was a 1
          next = s_5;
        else
          next = s_4;
      end//end state s_0
      s_3:begin
        if(A == 1'b1)//if input was a 1
          next = s_7;
        else
          next = s_6;
      end//end state s_0
      s_4:begin
        if(A == 1'b1)//if input was a 1
          next = s_1;
        else
          next = s_0;
      end//end state s_4
      s_5:begin
        if(A == 1'b1)//if input was a 1
          next = s_3;
        else
          next = s_2;
      end//end state s_5
      s_6:begin
        if(A == 1'b1)//if input was a 1
          next = s_5;
        else
          next = s_4;
      end//end state s_6
      s_7:begin
        if(A == 1'b1)//if input was a 1
          next = s_7;
        else
          next = s_6;
      end//end state s_7
    endcase
    end
  end
  always @(current)begin//output logic
   case (current)
    s_0:
      B = 0;
    s_1:
      B = 0;  
    s_2:
      B = 0;
    s_3:
      B = 0;
    s_4:
      B = 0;
    s_5:
      B = 1;
    s_6:
      B = 0;
    s_7:
      B = 0;
    endcase
  end//end output logic
  
  


// synopsys_translate on 
  
endmodule
