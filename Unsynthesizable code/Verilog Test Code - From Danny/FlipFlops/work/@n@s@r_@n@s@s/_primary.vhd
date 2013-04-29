library verilog;
use verilog.vl_types.all;
entity NSR_NSS is
    port(
        clock           : in     vl_logic;
        reset           : in     vl_logic;
        set             : in     vl_logic;
        \in\            : in     vl_logic;
        \out\           : out    vl_logic
    );
end NSR_NSS;
