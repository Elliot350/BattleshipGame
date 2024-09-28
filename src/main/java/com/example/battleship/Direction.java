package com.example.battleship;

public enum Direction {
    NORTH(0, false),
    EAST(90, true),
    SOUTH(180, false),
    WEST(-90, true);

    private final double rotationValue;
    private final boolean horizontal;

    Direction(double rotationValue, boolean horizontal) {
        this.rotationValue = rotationValue;
        this.horizontal = horizontal;
    }

    public double getRotationValue() {
        return rotationValue;
    }

    public boolean isHorizontal() {
        return horizontal;
    }

    public Direction next() {
        return switch (this) {
            case NORTH -> EAST;
            case EAST -> SOUTH;
            case SOUTH -> WEST;
            case WEST -> NORTH;
        };
    }

    public Direction previous() {
        return switch (this) {
            case NORTH -> WEST;
            case WEST -> SOUTH;
            case SOUTH -> EAST;
            case EAST -> NORTH;
        };
    }
}
