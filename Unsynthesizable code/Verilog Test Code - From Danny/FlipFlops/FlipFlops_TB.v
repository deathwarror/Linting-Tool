module FlipFlops_TB;
  reg clock, reset, set, in;
  wire nar_nss_out, nar_noSet_out, nsr_nss_out, nar_pss_out, nar_nas_out;
  NAR_NSS nar_nss (.clock(clock),.reset(reset),.set(set),.in(in),.out(nar_nss_out));
  NAR_NoSet nar_noSet (.clock(clock),.reset(reset),.set(set),.in(in),.out(nar_noSet_out));
  NSR_NSS nsr_nss (.clock(clock),.reset(reset),.set(set),.in(in),.out(nsr_nss_out));
  NAR_PSS nar_pss (.clock(clock),.reset(reset),.set(set),.in(in),.out(nar_pss_out));
  NAR_NAS nar_nas (.clock(clock),.reset(reset),.set(set),.in(in),.out(nar_nas_out));
  
  initial begin
    clock=0; reset=1; set=1; in=0;
    forever begin
      #5 clock = !clock;
    end
  end
  
  initial begin
    #10 reset=0; set=1; in=0;
    #10 reset=1; set=1; in=1;
    #10 reset=1; set=1; in=0;
    #10 reset=1; set=0; in=0;
    #10 reset=1; set=1; in=0;
    #10 reset=1; set=1; in=1;
    #10 reset=1; set=1; in=0;
  end
endmodule