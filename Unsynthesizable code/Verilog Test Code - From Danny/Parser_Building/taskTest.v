module taskTest(input wire in);
  reg [7:0] temp_out;
  reg [7:0] temp_in;
  
  convert();
  bob_func(1);
  
  always@(in)begin
    convert;
    convert();
    temp_out[0] = bob_func(1);
    if(in)begin
      convert;
    end
    else if(!in) begin
      convert;
    end
    else begin
      convert;
    end
    if(in)
      convert;
    else if(!in)
      convert;
    else
      convert;
  end
  
  always@(in)
    convert;
    
  task convert;
    begin
      temp_out = (9/5) *( temp_in + 32);
    end
  endtask
  
  function bob_func;
    input charles;
    bob_func = charles;
  endfunction
  
endmodule
