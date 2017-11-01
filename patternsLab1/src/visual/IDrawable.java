package visual;

import javafx.scene.canvas.GraphicsContext;

/**
 * Created by Артём on 04.10.2017.
 */
public interface IDrawable {
    void Draw(GraphicsContext c, int step);
}
