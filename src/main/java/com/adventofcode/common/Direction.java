package com.adventofcode.common;

public enum Direction {
    TOP(new Pair<>(0, -1)),
    TOP_RIGHT(new Pair<>(1, -1)),
    RIGHT(new Pair<>(1, 0)),
    DOWN_RIGHT(new Pair<>(1, 1)),
    DOWN(new Pair<>(0, 1)),
    DOWN_LEFT(new Pair<>(-1, 1)),
    LEFT(new Pair<>(-1, 0)),
    TOP_LEFT(new Pair<>(-1, -1));

    private final Pair<Integer> vector;

    Direction(Pair<Integer> vector) {
        this.vector = vector;
    }

    public Pair<Integer> getVector() {
        return vector;
    }

    public Direction turnRight() {
        return switch (this) {
            case TOP -> RIGHT;
            case RIGHT -> DOWN;
            case DOWN -> LEFT;
            case LEFT -> TOP;
            default -> throw new UnsupportedOperationException();
        };
    }

    public Direction turnRight(int times) {
        Direction result = this;
        for(int i = 0; i < times; i++) {
            result = result.turnRight();
        }
        return result;
    }

    public Direction turnLeft() {
        return switch (this) {
            case TOP -> LEFT;
            case LEFT -> DOWN;
            case DOWN -> RIGHT;
            case RIGHT -> TOP;
            default -> throw new UnsupportedOperationException();
        };
    }

    public Direction turnLeft(int times) {
        Direction result = this;
        for(int i = 0; i < times; i++) {
            result = result.turnLeft();
        }
        return result;
    }

    public Direction turnAround() {
        return switch (this) {
            case TOP -> DOWN;
            case LEFT -> RIGHT;
            case DOWN -> TOP;
            case RIGHT -> LEFT;
            default -> throw new UnsupportedOperationException();
        };
    }

    public Pair<Integer> moveFrom(Pair<Integer> position) {
        return new Pair<>(position.a() + this.getVector().a(), position.b() + this.getVector().b());
    }
    
    public static Direction detect(Pair<Integer> from, Pair<Integer> to) {
        if(from.a().equals(to.a())) {
            if(from.b() < to.b()) {
                return DOWN;
            } else if(from.b() > to.b()) {
                return TOP; 
            }
        } else if(from.b().equals(to.b())) {
            if(from.a() < to.a()) {
                return RIGHT;
            } else if(from.a() > to.a()) {
                return LEFT;
            }
        }
        throw new IllegalStateException("Direction is not straight or there is not line longer than 1 between points");
    }
}
