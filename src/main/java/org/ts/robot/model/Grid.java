package org.ts.robot.model;

import lombok.NonNull;

import java.util.HashMap;
import java.util.Map;

public final class Grid {
    private final GridPoint maxPoint;
    private final Map<GridPoint, Boolean> scents = new HashMap<>();

    public Grid(@NonNull GridPoint maxPoint) {
        this.maxPoint = maxPoint;
    }

    public boolean isOffTheGrid(@NonNull GridPoint point) {
        return point.getX() < 0 || point.getY() < 0 ||
                point.getX() > maxPoint.getX() || point.getY() > maxPoint.getY();
    }

    public boolean isScented(@NonNull GridPoint point) {
        return scents.getOrDefault(point, false);
    }

    public void markScented(@NonNull GridPoint point) {
        scents.put(point, true);
    }
}
