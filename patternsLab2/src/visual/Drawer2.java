package visual;

import geometry.IPoint;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

/**
 * Created by Артём on 20.10.2017.
 */
public class Drawer2 implements IDrawer {
    private final int R = 50;
    GraphicsContext c;
    int i;

    public Drawer2(GraphicsContext c) {
        this.c = c;
    }
    @Override
    public void line(IPoint from, IPoint to) {
       // Line line = new Line(from.getX(), from.getY(), to.getX(), to.getY());
        //line.setStrokeDashOffset(10);
        //c.setLineDashes(100);
        i++;
        if(i > R)
            i = -R;


        if(i >= 0) {
            c.setStroke(Color.BLACK);
            c.moveTo(from.getX(), from.getY());
            c.lineTo(to.getX(), to.getY());
            c.stroke();
        } else
            return;
    }

    @Override
    public void startP(IPoint p) {
        c.setFill(Color.BLACK);
        c.fillRect(p.getX() - 3, p.getY() - 3, 6, 6);
    }

    @Override
    public void finishP(IPoint prevP, IPoint lastP) {
        c.setFill(Color.BLACK);
        c.fillRect(lastP.getX() - 3, lastP.getY() - 3, 6, 6);
    }
}
