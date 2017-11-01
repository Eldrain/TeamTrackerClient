package geometry;

/**
 * Created by Артём on 04.10.2017.
 */
public class Line extends ACurve {

    public Line(IPoint a, IPoint b) {
        super(a, b);
    }

    @Override
    public IPoint getPoint(double t) {
        return new Point(0, 0).add(a, 1 - t).add(b, t);
    }
}
