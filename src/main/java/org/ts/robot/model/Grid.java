package org.ts.robot.model;

import lombok.NonNull;

import java.util.HashMap;
import java.util.Map;

public final class Grid {
    private final GridPoint maxPoint;
    private final GridPoint minPoint = new GridPoint(0, 0);
    private final Map<GridPoint, Boolean> scents = new HashMap<>();

    public Grid(@NonNull GridPoint maxPoint) {
        if (maxPoint.equals(minPoint) || maxPoint.isLessThan(minPoint)) {
            throw new IllegalArgumentException("Invalid max point: " + maxPoint + "should be more than: " + minPoint);
        }
        this.maxPoint = maxPoint;
    }

    public boolean isOffTheGrid(@NonNull GridPoint point) {
        return point.isLessThan(minPoint) || point.isGreaterThan(maxPoint);
    }

    public boolean isScented(@NonNull GridPoint point) {
        return scents.getOrDefault(point, false);
    }

    public void markScented(@NonNull GridPoint point) {
        scents.put(point, true);
    }
}
