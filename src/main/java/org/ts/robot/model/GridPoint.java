package org.ts.robot.model;

import lombok.Value;

@Value
public class GridPoint {
    int x;
    int y;

    public boolean isLessThan(GridPoint point) {
        return this.x < point.x || this.y < point.y;
    }

    public boolean isGreaterThan(GridPoint point) {
        return this.x > point.x || this.y > point.y;
    }
}
