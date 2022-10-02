package org.ts.robot.model;

import lombok.Data;
import lombok.NonNull;

@Data
public class Robot {

    private GridPoint currentPoint;

    private Orientation orientation;

    private Grid grid;

    private boolean lost;

    public Robot(@NonNull GridPoint currentPoint, @NonNull Orientation orientation, @NonNull Grid grid, boolean lost) {
        if (grid.isOffTheGrid(currentPoint)) {
            throw new IllegalArgumentException("Invalid grid point: " + currentPoint + ". It is off the grid");
        }

        this.currentPoint = currentPoint;
        this.orientation = orientation;
        this.grid = grid;
        this.lost = lost;
    }

    @Override
    public String toString() {
        return currentPoint.getX() + " " + currentPoint.getY() + " " + orientation.getValue() + (lost ? " LOST" : "");
    }
}
