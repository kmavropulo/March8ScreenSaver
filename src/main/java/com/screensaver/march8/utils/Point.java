package com.screensaver.march8.utils;

/**
 * This @code{Point} class is an immutable data type to encapsulate a
 * two-dimensional point with real-value coordinates.
 *
 * @author Konstantin Mavropulo {@link <a href=http://www.kmavropulo.ru>kmavropulo</a>}.
 */
public class Point implements Comparable<Point> {
    private final double coordinateX;    // x coordinate
    private final double coordinateY;    // y coordinate

    /**
     * Constructs a new instance of this class with the given coordinates.
     *
     * @param coordinateX the x-coordinate.
     * @param coordinateY the y-coordinate.
     * @throws IllegalArgumentException if either {@code x} or {@code y}
     *                                  is {@code Double.NaN},
     *                                  {@code Double.POSITIVE_INFINITY} or
     *                                  {@code Double.NEGATIVE_INFINITY}
     */
    public Point(double coordinateX, double coordinateY) {
        if (Double.isInfinite(coordinateX) || Double.isInfinite(coordinateY)) {
            throw new IllegalArgumentException("Coordinates must be finite");
        }
        if (Double.isNaN(coordinateX) || Double.isNaN(coordinateY)) {
            throw new IllegalArgumentException("Coordinates cannot be NaN");
        }
        if (coordinateX == 0.0) {
            this.coordinateX = 0.0; // convert -0.0 to +0.0
        } else {
            this.coordinateX = coordinateX;
        }

        if (coordinateY == 0.0) {
            this.coordinateY = 0.0; // convert -0.0 to +0.0
        } else {
            this.coordinateY = coordinateY;
        }
    }


    /**
     * Returns the x-coordinate.
     *
     * @return the x-coordinate.
     */
    public double getCoordinateX() {
        return coordinateX;
    }

    /**
     * Returns the y-coordinate.
     *
     * @return the y-coordinate.
     */
    public double getCoordinateY() {
        return coordinateY;
    }

    /**
     * Compares this point to the specified point.
     *
     * @param that the other point.
     * @return {@code true} if this point equals {@code other} or {@code false} otherwise.
     */
    @Override
    public boolean equals(Object that) {
        if (this == that) return true;
        if (that == null || getClass() != that.getClass()) return false;

        Point point = (Point) that;

        return Double.compare(point.coordinateX, coordinateX) == 0 && Double.compare(point
                .coordinateY, coordinateY) == 0;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(coordinateX);
        result = (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(coordinateY);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    /**
     * Compares two points by y-coordinate, breaking ties by x-coordinate.
     * Formally, the invoking point (x0, y0) is less than the argument
     * point (x1, y1) if and only if either {@code y0 < y1} or if
     * {@code y0 == y1} and {@code x0 < x1}.
     *
     * @param that the other point
     * @return the value {@code 0} if this string is equal to the argument
     * string (precisely when {@code equals()} returns {@code true});
     * a negative integer if this point is less than the argument
     * point; and a positive integer if this point is greater than the
     * argument point
     */

    public int compareTo(Point that) {
        if (this.coordinateY < that.coordinateY) {
            return -1;
        }
        if (this.coordinateY > that.coordinateY) {
            return +1;
        }
        if (this.coordinateX < that.coordinateX) {
            return -1;
        }
        if (this.coordinateX > that.coordinateX) {
            return +1;
        }
        return 0;
    }

    @Override
    public String toString() {
        return "Point{coordinateX=" + coordinateX + ", coordinateY="
                + coordinateY + '}';
    }
}