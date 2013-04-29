module Wire_Tests(input wire a,b,c,output   reg out);
  wire mid;
  reg t;
  always@(a or b)begin:Named_Block
    begin:Declaration_Block
      reg blk;
    end:Declaration_Block if(a) t=a; else if(!a) t=0; else begin t=1; end 
  end:Named_Block
  reg bob;
  
  begin
    wire charles;
  end
  begin:Declaration_Block_2
    reg block3;
  end
    
  always@(posedge c)begin
    t<=b;
  end
endmodule

