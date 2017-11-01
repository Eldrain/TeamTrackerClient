package visual;

import geometry.IPoint;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;

/**
 * Created by Артём on 13.10.2017.
 */
public class Drawer1 implements IDrawer {
    GraphicsContext c;

    public Drawer1(GraphicsContext c) {
        this.c = c;
    }
    @Override
    public void line(IPoint from, IPoint to) {
        c.moveTo(from.getX(), from.getY());
        c.lineTo(to.getX(), to.getY());
        c.stroke();
    }

    @Override
    public void startP(IPoint p) {
    }

    @Override
    public void finishP(IPoint p) {

    }
}
