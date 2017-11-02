package visual;

import geometry.IPoint;
import javafx.scene.canvas.GraphicsContext;

/**
 * Created by Артём on 13.10.2017.
 */
public interface IDrawer {
    void line(IPoint from, IPoint to);
    void startP(IPoint p);
    void finishP(IPoint prevP, IPoint lastP);
}
