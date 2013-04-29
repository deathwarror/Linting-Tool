library verilog;
use verilog.vl_types.all;
entity Output_Assigned_Reg is
    port(
        clk             : in     vl_logic;
        reset           : in     vl_logic;
        \out\           : out    vl_logic
    );
end Output_Assigned_Reg;
