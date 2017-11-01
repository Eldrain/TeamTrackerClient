package visual;

import geometry.IPoint;
import javafx.scene.canvas.GraphicsContext;

/**
 * Created by Артём on 20.10.2017.
 */
public class Drawer2 implements IDrawer {
    GraphicsContext c;

    public Drawer2(GraphicsContext c) {
        this.c = c;
    }
    @Override
    public void line(IPoint from, IPoint to) {
        c.moveTo(from.getX(), from.getY());
        c.lineTo(to.getX() - (to.getX() - from.getX())/5, to.getY() - (to.getY() - from.getY())/5);
    }

    @Override
    public void startP(IPoint p) {
    }

    @Override
    public void finishP(IPoint p) {

    }
}
