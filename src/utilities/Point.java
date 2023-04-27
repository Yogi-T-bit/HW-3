package utilities;

/**
 * @author: Gil Cohen - 314800558, Yogev Tamir - 203762216
 *
 * This class present a point in the race for every racer (describes the location of the racer every move)
 */
public class Point {
    private double x;
    private double y;
    public static final int MAX_X = 1_000_000;
    public static final int MAX_Y = 800;
    public static final int MIN_X = 0;
    public static final int MIN_Y = 0;

    /**
     * @param x x point double var - the x value in the map
     * @param y y point double var - the y value in the map
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * @param point making point var the new point (x,y)
     */
    public Point(Point point) {
        this(point.getX(), point.getY());
    }

    public Point() {
        this(MIN_X, MIN_Y);
    }

    /**
     * @return return x var
     */
    public double getX() {
        return x;
    }

    /**
     * @param x x var chosen by the user
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     * @return return y var
     */
    public double getY() {
        return y;
    }

    /**
     * @param y y var chosen by the user
     */
    public void setY(double y) {
        this.y = y;
    }


    /**
     * @return return format string for x,y
     */
    public String toString() {
        return "(" + this.x + "," + this.y + ")";
    }
}
