module Vending_Machine(input wire clk, reset,N,D, output wire OK);
  parameter zero=3'b000;
  parameter five=3'b001;
  parameter ten=3'b010;
  parameter fifteen=3'b011;
  parameter twenty=3'b100;
  
  reg [2:0] current; reg [2:0] next;
  
  always@(N or D or current)begin
    case(current)
      zero:begin
        if(N)
          next=five;
        else if(D)
          next=ten;
        else
          next=current;
      end
     five:begin
       if(N)
         next=ten;
       else if(D)
         next=fifteen;
       else
         next=current;
     end
    ten:begin
       if(N)
         next=fifteen;
       else if(D)
         next=twenty;
       else
         next=current;
    end
    fifteen:begin
      if(N)
        next=twenty;
      else if(D)
        next=zero;
      else
        next=current;
    end
    twenty:begin
      if(N)
        next=zero;
      else if(D)
        next=five;
      else
        next=current;
    end
    default:begin
      next=current;
    end
  endcase
end

always@(posedge clk or posedge reset)begin
  if(reset)begin
    current<=zero;
  end
  else begin
    current<=next;
  end
end

assign OK = ((current==fifteen)&&D)||((current==twenty)&&N)||((current==twenty)&&D);

endmodule        

