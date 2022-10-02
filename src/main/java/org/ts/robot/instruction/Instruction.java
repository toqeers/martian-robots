package org.ts.robot.instruction;

import lombok.NonNull;
import org.ts.robot.model.Robot;

public abstract class Instruction {

    public Robot apply(@NonNull Robot robot) {
        if (!robot.isLost()) {
            processInstruction(robot);
        }

        return robot;
    }

    protected abstract void processInstruction(Robot robot);
}
