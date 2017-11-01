package visual;

import geometry.ICurve;
import geometry.IPoint;
import javafx.scene.canvas.GraphicsContext;

/**
 * Created by Артём on 04.10.2017.
 */
public abstract class VisualCurve implements ICurve, IDrawable {

    public void Draw(IDrawer drawer, int step) {
        double h = (double)1/step;
        IPoint p = this.getPoint(0), nextP = this.getPoint(h);

        drawer.startP(p);
        for(double t = 0; t <= 1; t+=h) {
            p = nextP;
            nextP = this.getPoint(t);
            drawer.line(p, nextP);
        }
        drawer.finishP(nextP);
    }
}
