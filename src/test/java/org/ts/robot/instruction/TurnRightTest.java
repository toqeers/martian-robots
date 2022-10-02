package org.ts.robot.instruction;

import org.junit.jupiter.api.Test;
import org.ts.robot.model.Grid;
import org.ts.robot.model.GridPoint;
import org.ts.robot.model.Orientation;
import org.ts.robot.model.Robot;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class TurnRightTest {

    private final TurnRight instruction = new TurnRight();
    private final Grid grid = new Grid(new GridPoint(5, 5));

    @Test
    void turnLeftFromNorth() {
        // Given
        Robot robot = new Robot(new GridPoint(3, 3), Orientation.NORTH, grid, false);

        // When
        instruction.processInstruction(robot);

        // Then
        assertEquals(Orientation.EAST, robot.getOrientation());
        assertFalse(robot.isLost());
        assertFalse(grid.isScented(new GridPoint(3, 3)));
        assertEquals(3, robot.getCurrentPoint().getX());
        assertEquals(3, robot.getCurrentPoint().getY());
    }

    @Test
    void turnLeftFromEast() {
        // Given
        Robot robot = new Robot(new GridPoint(3, 3), Orientation.EAST, grid, false);

        // When
        instruction.processInstruction(robot);

        // Then
        assertEquals(Orientation.SOUTH, robot.getOrientation());
        assertFalse(robot.isLost());
        assertFalse(grid.isScented(new GridPoint(3, 3)));
        assertEquals(3, robot.getCurrentPoint().getX());
        assertEquals(3, robot.getCurrentPoint().getY());
    }

    @Test
    void turnLeftFromSouth() {
        // Given
        Robot robot = new Robot(new GridPoint(3, 3), Orientation.SOUTH, grid, false);

        // When
        instruction.processInstruction(robot);

        // Then
        assertEquals(Orientation.WEST, robot.getOrientation());
        assertFalse(robot.isLost());
        assertFalse(grid.isScented(new GridPoint(3, 3)));
        assertEquals(3, robot.getCurrentPoint().getX());
        assertEquals(3, robot.getCurrentPoint().getY());
    }

    @Test
    void turnLeftFromWest() {
        // Given
        Robot robot = new Robot(new GridPoint(3, 3), Orientation.WEST, grid, false);

        // When
        instruction.processInstruction(robot);

        // Then
        assertEquals(Orientation.NORTH, robot.getOrientation());
        assertFalse(robot.isLost());
        assertFalse(grid.isScented(new GridPoint(3, 3)));
        assertEquals(3, robot.getCurrentPoint().getX());
        assertEquals(3, robot.getCurrentPoint().getY());
    }
}