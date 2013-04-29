library verilog;
use verilog.vl_types.all;
entity Wire_Mid_Module is
    port(
        a               : in     vl_logic;
        b               : in     vl_logic;
        c               : in     vl_logic;
        \out\           : out    vl_logic
    );
end Wire_Mid_Module;
