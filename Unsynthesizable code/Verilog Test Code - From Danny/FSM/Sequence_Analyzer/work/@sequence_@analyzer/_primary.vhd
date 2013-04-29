library verilog;
use verilog.vl_types.all;
entity Sequence_Analyzer is
    generic(
        start           : vl_logic_vector(0 to 1) := (Hi0, Hi0);
        got1            : vl_logic_vector(0 to 1) := (Hi0, Hi1);
        got10           : vl_logic_vector(0 to 1) := (Hi1, Hi0);
        got100          : vl_logic_vector(0 to 1) := (Hi1, Hi1)
    );
    port(
        serialInput     : in     vl_logic;
        clk             : in     vl_logic;
        reset           : in     vl_logic;
        \out\           : out    vl_logic
    );
    attribute mti_svvh_generic_type : integer;
    attribute mti_svvh_generic_type of start : constant is 1;
    attribute mti_svvh_generic_type of got1 : constant is 1;
    attribute mti_svvh_generic_type of got10 : constant is 1;
    attribute mti_svvh_generic_type of got100 : constant is 1;
end Sequence_Analyzer;
