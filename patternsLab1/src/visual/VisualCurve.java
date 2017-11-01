package visual;

import geometry.ICurve;
import geometry.IPoint;
import javafx.scene.canvas.GraphicsContext;

/**
 * Created by Артём on 04.10.2017.
 */
public abstract class VisualCurve implements ICurve, IDrawable {

    public void Draw(GraphicsContext c, int step) {
        IPoint p = this.getPoint(0);
        c.moveTo(p.getX(), p.getY());

        for(double h = 0; h <= 1; h+=(double)1/step) {
            p = this.getPoint(h);
            c.lineTo(p.getX(), p.getY());
        }
        c.stroke();
    }
}
