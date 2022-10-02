package org.ts.robot.instruction;

import org.ts.robot.model.Robot;

public class TurnLeft extends Instruction{

    @Override
    protected void processInstruction(Robot robot) {
        robot.setOrientation(robot.getOrientation().getLeft());
    }
}
