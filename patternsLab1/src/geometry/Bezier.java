package geometry;

/**
 * Created by Артём on 04.10.2017.
 */
public class Bezier extends ACurve {
    IPoint c, d;

    public Bezier(IPoint a, IPoint b, IPoint c, IPoint d) {
        super(a, b);
        this.c = c;
        this.d = d;
    }

    @Override
    public IPoint getPoint(double t) {
        return new Point(0, 0).add(a, Math.pow((1 - t), 3)).add(c, 3*t*Math.pow((1 - t), 2)).add(d, 3*t*t*(1-t)).add(b, t*t*t);
    }
}
