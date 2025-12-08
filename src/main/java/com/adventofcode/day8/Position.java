package com.adventofcode.day8;

record Position(long x, long y, long z) {
    long distanceSquare(Position other) {
        return(pow2(other.x - this.x) + pow2(other.y - this.y) + pow2(other.z - this.z));
    }
    
    private long pow2(long a) {
        return a*a;
    }
}
