package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import visual.Drawer1;
import visual.Drawer2;
import visual.VisualBezier;
import visual.VisualLine;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Group root = new Group();
        primaryStage.setTitle("Pattern lab1");
        primaryStage.setScene(new Scene(root, 800, 600));

        Canvas canvas = new Canvas(800, 600); // создаем новый объект Canvas с шириной 300px, и высотой 275px
        root.getChildren().add(canvas); // добавляем его в корневой контейнер
        GraphicsContext context = canvas.getGraphicsContext2D(); // и получаем GraphicContext
        Drawer1 drawer1 = new Drawer1(context);
        Drawer2 drawer2 = new Drawer2(context);

        VisualLine line = new VisualLine(100, 100, 300, 150);
        VisualBezier bezier = new VisualBezier(100, 300, 700, 400, 400, 400, 550, 300, 1000);
        context.setFill(Color.RED);

        line.Draw(drawer1, 1000);
        bezier.Draw(drawer1, 1000);

        //context.stroke();
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
