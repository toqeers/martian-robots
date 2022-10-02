package org.ts.robot.instruction;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.ts.robot.model.Grid;
import org.ts.robot.model.GridPoint;
import org.ts.robot.model.Orientation;
import org.ts.robot.model.Robot;

import static org.junit.jupiter.api.Assertions.*;

class MoveForwardTest {

    private final MoveForward instruction = new MoveForward();

    @Test
    void moveForwardNorthOnTheGrid() {
        // Given
        Grid grid = new Grid(new GridPoint(5, 5));
        Robot robot = new Robot(new GridPoint(3, 3), Orientation.NORTH, grid, false);

        // When
        instruction.processInstruction(robot);

        // Then
        assertEquals(Orientation.NORTH, robot.getOrientation());
        assertFalse(robot.isLost());
        assertFalse(grid.isScented(new GridPoint(3, 3)));
        assertEquals(3, robot.getCurrentPoint().getX());
        assertEquals(4, robot.getCurrentPoint().getY());
    }

    @Test
    void moveForwardSouthOnTheGrid() {
        // Given
        Grid grid = new Grid(new GridPoint(5, 5));
        Robot robot = new Robot(new GridPoint(3, 3), Orientation.SOUTH, grid, false);

        // When
        instruction.processInstruction(robot);

        // Then
        assertEquals(Orientation.SOUTH, robot.getOrientation());
        assertFalse(robot.isLost());
        assertFalse(grid.isScented(new GridPoint(3, 3)));
        assertEquals(3, robot.getCurrentPoint().getX());
        assertEquals(2, robot.getCurrentPoint().getY());
    }

    @Test
    void moveForwardEastOnTheGrid() {
        // Given
        Grid grid = new Grid(new GridPoint(5, 5));
        Robot robot = new Robot(new GridPoint(3, 3), Orientation.EAST, grid, false);

        // When
        instruction.processInstruction(robot);

        // Then
        assertEquals(Orientation.EAST, robot.getOrientation());
        assertFalse(robot.isLost());
        assertFalse(grid.isScented(new GridPoint(3, 3)));
        assertEquals(4, robot.getCurrentPoint().getX());
        assertEquals(3, robot.getCurrentPoint().getY());
    }

    @Test
    void moveForwardWestOnTheGrid() {
        // Given
        Grid grid = new Grid(new GridPoint(5, 5));
        Robot robot = new Robot(new GridPoint(3, 3), Orientation.WEST, grid, false);

        // When
        instruction.processInstruction(robot);

        // Then
        assertEquals(Orientation.WEST, robot.getOrientation());
        assertFalse(robot.isLost());
        assertFalse(grid.isScented(new GridPoint(3, 3)));
        assertEquals(2, robot.getCurrentPoint().getX());
        assertEquals(3, robot.getCurrentPoint().getY());
    }

    @Test
    void moveForwardOffTheGrid() {
        // Given
        Grid grid = new Grid(new GridPoint(5, 5));
        Robot robot = new Robot(new GridPoint(5, 5), Orientation.NORTH, grid, false);

        // When
        instruction.processInstruction(robot);

        // Then
        assertEquals(Orientation.NORTH, robot.getOrientation());
        assertTrue(robot.isLost());
        assertTrue(grid.isScented(new GridPoint(5, 5)));
        assertEquals(5, robot.getCurrentPoint().getX());
        assertEquals(5, robot.getCurrentPoint().getY());
    }

    @Test
    void moveForwardOffTheGridFromScentedPoint() {
        // Given
        Grid grid = new Grid(new GridPoint(5, 5));
        Robot robot1 = new Robot(new GridPoint(5, 5), Orientation.NORTH, grid, false);
        Robot robot2 = new Robot(new GridPoint(5, 5), Orientation.NORTH, grid, false);

        // When
        instruction.processInstruction(robot1);
        instruction.processInstruction(robot2);

        // Then
        assertTrue(robot1.isLost());
        assertTrue(grid.isScented(new GridPoint(5, 5)));
        assertEquals(5, robot1.getCurrentPoint().getX());
        assertEquals(5, robot1.getCurrentPoint().getY());

        assertFalse(robot2.isLost());
        assertEquals(5, robot2.getCurrentPoint().getX());
        assertEquals(5, robot2.getCurrentPoint().getY());
    }
}