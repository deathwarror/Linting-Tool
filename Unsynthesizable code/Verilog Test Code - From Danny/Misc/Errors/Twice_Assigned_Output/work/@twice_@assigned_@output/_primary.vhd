library verilog;
use verilog.vl_types.all;
entity Twice_Assigned_Output is
    port(
        a               : in     vl_logic;
        b               : in     vl_logic;
        c               : in     vl_logic;
        \out\           : out    vl_logic
    );
end Twice_Assigned_Output;
