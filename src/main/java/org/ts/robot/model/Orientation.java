package org.ts.robot.model;

import java.util.stream.Stream;

public enum Orientation {
    NORTH("N", "WEST", "EAST"),
    EAST("E", "NORTH", "SOUTH"),
    WEST("W", "SOUTH", "NORTH"),
    SOUTH("S", "EAST", "WEST");

    private final String value;
    private final String leftOrientation;
    private final String rightOrientation;

    Orientation(String value, String leftOrientation, String rightOrientation) {
        this.value = value;
        this.leftOrientation = leftOrientation;
        this.rightOrientation = rightOrientation;
    }

    public static Orientation toEnum(final String value) {
        return Stream.of(Orientation.values())
                .filter(v -> v.value.equals(value))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Invalid Orientation: " + value));
    }

    public String getValue() {
        return value;
    }

    public Orientation getLeft() {
        return Orientation.valueOf(leftOrientation);
    }

    public Orientation getRight() {
        return Orientation.valueOf(rightOrientation);
    }
}
