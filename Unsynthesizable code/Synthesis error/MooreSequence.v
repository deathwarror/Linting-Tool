module MooreSeq_orig(input clk, reset,A, output reg B);
  
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
  
  
  always @(posedge reset)begin
    current = s_0;
    next = s_0;
  end
    
  always @(posedge clk)begin//current state next state logic
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
  
  
// synopsys_translate off 
reg [119:0] ascii_current, ascii_next; 
  always @(current) begin//Current State ASCII Encoding 
    case(current) 
       s_0: ascii_current = "S_0"; 
       s_1: ascii_current = "S_1"; 
       s_2: ascii_current = "S_2"; 
       s_3: ascii_current = "S_3"; 
       s_4: ascii_current = "S_4";
       s_5: ascii_current = "S_5";
       s_6: ascii_current = "S_6";
       s_7: ascii_current = "S_7";
    default:  ascii_current = "ERROR"; 
    endcase 
end//End Current State ASCII Encoding

always @(next) begin // Next State Ascii encoding
    case(next) 
       s_0: ascii_next = "S_0"; 
       s_1: ascii_next = "S_1"; 
       s_2: ascii_next = "S_2"; 
       s_3: ascii_next = "S_3"; 
       s_4: ascii_next = "S_4";
       s_5: ascii_next = "S_5";
       s_6: ascii_next = "S_6";
       s_7: ascii_next = "S_7";
    default:  ascii_next = "ERROR"; 
    endcase 
end //end Next state ASCII Encoding

// synopsys_translate on 
  
endmodule