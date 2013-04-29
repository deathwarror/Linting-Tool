library verilog;
use verilog.vl_types.all;
entity NAR_PSS is
    port(
        clock           : in     vl_logic;
        reset           : in     vl_logic;
        set             : in     vl_logic;
        \in\            : in     vl_logic;
        \out\           : out    vl_logic
    );
end NAR_PSS;
