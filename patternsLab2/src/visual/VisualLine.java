package visual;

import geometry.IPoint;
import geometry.Line;
import geometry.Point;

/**
 * Created by Артём on 04.10.2017.
 */
public class VisualLine extends VisualCurve {
    Line line;

    public VisualLine(double x1, double y1, double x2, double y2) {
        line = new Line(new Point(x1, y1), new Point(x2, y2));

    }

    @Override
    public IPoint getPoint(double t) {
        return line.getPoint(t);
    }
}
