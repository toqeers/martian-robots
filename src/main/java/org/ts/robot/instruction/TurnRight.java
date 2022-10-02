package org.ts.robot.instruction;

import org.ts.robot.model.Robot;

public class TurnRight extends Instruction{

    @Override
    protected void processInstruction(Robot robot) {
        robot.setOrientation(robot.getOrientation().getRight());
    }
}
