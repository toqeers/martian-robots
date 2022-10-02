package org.ts.robot.instruction;

import java.util.HashMap;
import java.util.Map;

public final class InstructionFactory {

    private static final Map<String, Instruction> MAPPING = new HashMap<>();

    static {
        MAPPING.put("L", new TurnLeft());
        MAPPING.put("R", new TurnRight());
        MAPPING.put("F", new MoveForward());
    }

    public static Instruction getInstruction(String value) {
        Instruction instruction = MAPPING.get(value);
        if (instruction == null) {
            throw new IllegalArgumentException("Invalid instruction: " + value);
        }

        return instruction;
    }
}
