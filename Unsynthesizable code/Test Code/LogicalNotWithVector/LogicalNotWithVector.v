module LogicalNotWithVector(input wire [2:0] vector, output wire [2:0] out);
 assign out[2:1] = (!vector[2:1]);
 assign out[0] = !vector[0];
endmodule
 