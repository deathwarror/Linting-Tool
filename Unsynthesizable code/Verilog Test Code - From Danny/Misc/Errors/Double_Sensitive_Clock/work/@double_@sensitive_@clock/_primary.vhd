library verilog;
use verilog.vl_types.all;
entity Double_Sensitive_Clock is
    port(
        clk             : in     vl_logic;
        reset           : in     vl_logic;
        d               : in     vl_logic;
        \out\           : out    vl_logic
    );
end Double_Sensitive_Clock;
