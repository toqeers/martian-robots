package org.ts.robot.input;

import lombok.Value;
import org.ts.robot.instruction.Instruction;

import java.util.List;

@Value
public class RobotInput {
    RobotState initialState;
    List<Instruction> instructions;
}
