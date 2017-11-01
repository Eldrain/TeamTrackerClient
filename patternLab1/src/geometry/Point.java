package geometry;

/**
 * Created by Артём on 04.10.2017.
 */
public class Point implements IPoint {
    private double x, y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public double getX() {
        return x;
    }

    @Override
    public void setX(double x) {
        this.x = x;
    }

    @Override
    public double getY() {
        return y;
    }

    @Override
    public void setY(double y) {
        this.y = y;
    }

    public IPoint mult(double t) {
        x = x * t;
        y = y * t;
        return this;
    }

    public Point add(IPoint p, double mn) {
        x = x + mn*p.getX();
        y = y + mn*p.getY();
        return this;
    }
}
