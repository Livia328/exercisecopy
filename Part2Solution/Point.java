package Part2Solution;
/**
 * Class for a coodinate point in 2D plane
 */
public class Point {
    /**
     * x : double type, x coodinate of this point
     */
    public double x;
    /**
     * y : double type, y coodinate of this point
     */
    public double y;
    /**
     * Constuctor, create a new Point instance with given coordinates
     * @param x x coordinate for the new point
     * @param y y coordinate for the new point
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }
    /**
     * Override toString() method
     * @return String representation of this Point instance.
     */
    @Override
    public String toString() {
        return "(" + x + "," + y + ")";
    }
}