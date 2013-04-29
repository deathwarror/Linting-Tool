library verilog;
use verilog.vl_types.all;
entity Output_Assigned_From_Reg_Inputs is
    port(
        clk             : in     vl_logic;
        reset           : in     vl_logic;
        inA             : in     vl_logic;
        \out\           : out    vl_logic
    );
end Output_Assigned_From_Reg_Inputs;
