package org.ts.robot.instruction;

import org.ts.robot.model.Grid;
import org.ts.robot.model.GridPoint;
import org.ts.robot.model.Orientation;
import org.ts.robot.model.Robot;

public class MoveForward extends Instruction {

    @Override
    protected void processInstruction(Robot robot) {
        GridPoint currentPoint = robot.getCurrentPoint();
        Orientation orientation = robot.getOrientation();
        Grid grid = robot.getGrid();

        GridPoint nextPoint = getNextPoint(currentPoint, orientation);
        if (grid.isOffTheGrid(nextPoint)) {
            if (!grid.isScented(currentPoint)) {
                grid.markScented(currentPoint);
                robot.setLost(true);
            }
        } else {
            robot.setCurrentPoint(nextPoint);
        }
    }

    private GridPoint getNextPoint(GridPoint currentPoint, Orientation orientation) {
        GridPoint nextPoint = null;
        switch (orientation) {
            case NORTH:
                nextPoint = new GridPoint(currentPoint.getX(), currentPoint.getY() + 1);
                break;
            case EAST:
                nextPoint = new GridPoint(currentPoint.getX() + 1, currentPoint.getY());
                break;
            case WEST:
                nextPoint = new GridPoint(currentPoint.getX() - 1, currentPoint.getY());
                break;
            case SOUTH:
                nextPoint = new GridPoint(currentPoint.getX(), currentPoint.getY() - 1);
                break;
        }
        return nextPoint;
    }
}
