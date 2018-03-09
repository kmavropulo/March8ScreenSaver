package com.screensaver.march8.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * This @code{Line} class implements line in two-dimensional plane.
 *
 * @author Konstantin Mavropulo {@link <a href=http://www.kmavropulo.ru>kmavropulo</a>}.
 */
public class Line {
    private Point pointFirst;
    private Point pointSecond;

    /**
     * Constructs the line.
     *
     * @param pointFirst  one of points of the line.
     * @param pointSecond one of points of the line.
     * @throws IllegalArgumentException if at least one of arguments is null.
     */
    public Line(Point pointFirst, Point pointSecond) {

        this.pointFirst = pointFirst;
        this.pointSecond = pointSecond;
    }

    /**
     * Gets first end point of the @code{Line}.
     *
     * @return @code{Point} first point.
     */
    public Point getPointFirst() {
        return pointFirst;
    }

    /**
     * Sets first end point of the @code{Line}.
     *
     * @param pointFirst @code{Point} to set.
     * @throws IllegalArgumentException if argument is null.
     */
    public void setPointFirst(Point pointFirst) {

        this.pointFirst = pointFirst;
    }

    /**
     * Gets second end point of the @code{Line}.
     *
     * @return @code{Point} second point.
     */
    public Point getPointSecond() {
        return pointSecond;
    }

    /**
     * Sets second end point of the @code{Line}.
     *
     * @param pointSecond @code{Point} to set.
     * @throws IllegalArgumentException if argument is null.
     */
    public void setPointSecond(Point pointSecond) {

        this.pointSecond = pointSecond;
    }

    /**
     * Finds intersection point of this @code{Line} and @code{lineSecond}.
     *
     * @param lineSecond @code{Line} to find intersection with.
     * @param isSegment  true searching the segments intersection, false if the lines.
     * @return @code{Point} intersection point or null, if there are no intersection.
     * @throws IllegalArgumentException if argument is null.
     */
    public Point intersectionPoint(Line lineSecond, boolean isSegment) {


        // first line represented as a1x + b1y = c1
        double firstA = getCanonicalParameters().get("parameterA");
        double firstB = getCanonicalParameters().get("parameterB");
        double firstC = getCanonicalParameters().get("parameterC");

        // second line represented as a2x + b2y = c2
        double secondA = lineSecond.getCanonicalParameters().get(
                "parameterA");
        double secondB = lineSecond.getCanonicalParameters().get(
                "parameterB");
        double secondC = lineSecond.getCanonicalParameters().get(
                "parameterC");
        double determinant = firstA * secondB - secondA * firstB;

        if (determinant == 0) {
            // The lines are parallel. This is simplified by returning null
            return null;
        } else {
            double intersectionX = (secondB * firstC - firstB * secondC)
                    / determinant;
            double intersectionY = (firstA * secondC - secondA * firstC)
                    / determinant;
            //check that intersection point belongs to the segments
            if (isSegment) {
                Point tempIntersectionPoint = new Point(intersectionX,
                        intersectionY);
                if (this.isInSegmentRange(tempIntersectionPoint)
                        && lineSecond.isInSegmentRange(tempIntersectionPoint)) {
                    return tempIntersectionPoint;
                }
                return null;
            } else {
                return new Point(intersectionX, intersectionY);
            }
        }
    }

    /**
     * Checks if @code{Point} @code{ToCheck} belongs to range of line segment.
     *
     * @param pointToCheck point to check.
     * @return @code{boolean} true if point belongs to the range.
     * @throws IllegalArgumentException if @code{pointToCheck} is null.
     */
    private boolean isInSegmentRange(Point pointToCheck) {

        return ((Math.min(this.pointFirst.getCoordinateX(), this.pointSecond
                .getCoordinateX()) <= pointToCheck.getCoordinateX())
                && (pointToCheck.getCoordinateX() <= Math.max(this.pointFirst
                .getCoordinateX(), this.pointSecond.getCoordinateX())))
                && ((Math.min(this.pointFirst.getCoordinateY(), this.pointSecond
                .getCoordinateY()) <= pointToCheck.getCoordinateY())
                && (pointToCheck.getCoordinateY() <= Math.max(this.pointFirst
                .getCoordinateY(), this.pointSecond.getCoordinateY())));
    }

    /**
     * Gets canonical parameters for this @code{Line}.
     *
     * @return @code{Map} with parameters.
     */
    private Map<String, Double> getCanonicalParameters() {
        Map<String, Double> canonicalParameters = new HashMap<>();
        // first line represented as a1x + b1y = c1
        canonicalParameters.put("parameterA", this.pointSecond.getCoordinateY()
                - this.pointFirst.getCoordinateY());
        canonicalParameters.put("parameterB", this.pointFirst.getCoordinateX()
                - this.pointSecond.getCoordinateX());
        canonicalParameters.put("parameterC", canonicalParameters.get(
                "parameterA") * (this.pointFirst.getCoordinateX())
                + canonicalParameters.get("parameterB") * (this.pointFirst
                .getCoordinateY()));
        return canonicalParameters;
    }

    @Override
    public String toString() {
        return "Line{" + "pointFirst=" + pointFirst + System.lineSeparator()
                + ", pointSecond=" + pointSecond + '}';
    }
}