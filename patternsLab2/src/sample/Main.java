package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
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

        Button btn = new Button("yeah");
        btn.setTranslateX(50);
        btn.setTranslateY(520);

        Canvas reg1 = new Canvas(300, 400);
        Canvas reg2 = new Canvas(300, 400);
        reg1.setTranslateX(50);
        reg1.setTranslateY(100);
        reg2.setTranslateX(400);
        reg2.setTranslateY(100);

        root.getChildren().add(reg1);
        root.getChildren().add(reg2);
        root.getChildren().add(btn);

        primaryStage.show();

        GraphicsContext con1 = reg1.getGraphicsContext2D();
        GraphicsContext con2 = reg2.getGraphicsContext2D();
        con1.rect(0, 0, 300, 400);
        con2.rect(0, 0, 300, 400);
        Drawer1 drawer1 = new Drawer1(con1);
        Drawer2 drawer2 = new Drawer2(con2);
        VisualLine line = new VisualLine(0, 0, 200, 400);

        con1.setLineWidth(0.5);
        con2.setLineWidth(0.5);
        //context.sm
        //line4.Draw(drawer1, 1000);
        line.Draw(drawer1, 100);
        line.Draw(drawer2, 100);
        //bezier.Draw(drawer2, 500);
    }


    public static void main(String[] args) {
        launch(args);
    }
}
