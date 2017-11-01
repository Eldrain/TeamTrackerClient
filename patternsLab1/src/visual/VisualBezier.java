package visual;

import geometry.Bezier;
import geometry.IPoint;
import geometry.Point;
import javafx.scene.canvas.GraphicsContext;

/**
 * Created by Артём on 04.10.2017.
 */
public class VisualBezier extends VisualCurve {
    Bezier b;
    int step;

    public VisualBezier(double x1, double y1, double x2, double y2, double x3, double y3, double x4, double y4, int step) {
        b = new Bezier(new Point(x1, y1), new Point(x2, y2), new Point(x3, y3), new Point(x4, y4));
        this.step = step;
    }

    public VisualBezier(IPoint a, IPoint b1, IPoint c, IPoint d, int step) {
        b = new Bezier(a, b1, c, d);
        this.step = step;
    }

    @Override
    public IPoint getPoint(double t) {
        return b.getPoint(t);
    }

}
