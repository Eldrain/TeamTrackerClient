package visual;

import geometry.IPoint;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

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
        c.setStroke(Color.GREEN);
        c.moveTo(from.getX(), from.getY());
        c.lineTo(to.getX(), to.getY());
        c.stroke();
    }

    @Override
    public void startP(IPoint p) {
        c.setFill(Color.GREEN);
        c.fillOval(p.getX() - 3, p.getY() - 3, 6, 6);
    }

    @Override
    public void finishP(IPoint prevP, IPoint lastP) {
        //c.setLineWidth(1);
        c.setStroke(Color.GREEN);
        double angle = angle(prevP, lastP);
        c.moveTo(lastP.getX(), lastP.getY());
        c.lineTo(lastP.getX() + Math.cos(angle - Math.PI/6) * 10, lastP.getY() + Math.sin(angle - Math.PI/6) * 10);

        c.moveTo(lastP.getX(), lastP.getY());
        c.lineTo(lastP.getX() + Math.cos(angle + Math.PI/6) * 10, lastP.getY() + Math.sin(angle + Math.PI/6) * 10);

        //c.strokeText(String.valueOf(angle(prevP, lastP)), lastP.getX(), lastP.getY());
    }

    private double angle(IPoint prevP, IPoint lastP) {
        double angle = 0, x1 = lastP.getX(), y1 = lastP.getY(), x2 = prevP.getX(), y2 = prevP.getY();
        x1-=x2;
        y1-=y2;

        if(x1 == 0) {
            if (y1 > 0)
                return Math.PI / 2;
            else
                return 3* Math.PI / 2;
        }

        angle =  Math.atan(y1/x1);
        if(x1 > 0) {
            if(y1 > 0)
                angle = -angle;
            else
                angle = Math.PI - angle;

        } else {
            if(y1 > 0)
                angle = Math.PI - angle;
            else
                angle = 2*Math.PI - angle;
        }

        return angle;
    }
}
