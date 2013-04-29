always@ (posedge clk or posedge reset) begin
    if(reset) begin
      Load = 0;
      Led_wait = 0;
      Led_rdy = 0;
      Led_idle = 1;
    end
    else if(clk)  begin
      if(Go) begin
        Load = 1;
        Led_wait = 1;
        Led_rdy = 0;
        Led_idle = 0;
      end
      else begin
        if(Led_idle) begin
        Load = 0;
        Led_wait = 0;
        Led_rdy = 0;
        Led_idle = 1;
        end
        else begin
        Load = 0;
        Led_wait = 0;
        Led_rdy = 1;
        Led_idle = 0;
       	end
      end
    end
  end
endmodule
