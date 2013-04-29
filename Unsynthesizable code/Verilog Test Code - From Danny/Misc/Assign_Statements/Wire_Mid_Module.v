module Wire_Mid_Module(input wire a,b,c,output   reg out);
  wire mid;
  assign mid = a+b;
  always@(mid or c)
    out = mid+c;
endmodule
