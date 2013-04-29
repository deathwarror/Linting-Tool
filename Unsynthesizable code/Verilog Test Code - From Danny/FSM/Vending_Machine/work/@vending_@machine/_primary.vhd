library verilog;
use verilog.vl_types.all;
entity Vending_Machine is
    generic(
        zero            : vl_logic_vector(0 to 2) := (Hi0, Hi0, Hi0);
        five            : vl_logic_vector(0 to 2) := (Hi0, Hi0, Hi1);
        ten             : vl_logic_vector(0 to 2) := (Hi0, Hi1, Hi0);
        fifteen         : vl_logic_vector(0 to 2) := (Hi0, Hi1, Hi1);
        twenty          : vl_logic_vector(0 to 2) := (Hi1, Hi0, Hi0)
    );
    port(
        clk             : in     vl_logic;
        reset           : in     vl_logic;
        N               : in     vl_logic;
        D               : in     vl_logic;
        OK              : out    vl_logic
    );
    attribute mti_svvh_generic_type : integer;
    attribute mti_svvh_generic_type of zero : constant is 1;
    attribute mti_svvh_generic_type of five : constant is 1;
    attribute mti_svvh_generic_type of ten : constant is 1;
    attribute mti_svvh_generic_type of fifteen : constant is 1;
    attribute mti_svvh_generic_type of twenty : constant is 1;
end Vending_Machine;
