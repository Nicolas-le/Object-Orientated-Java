package sample;

import javafx.application.Platform;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.canvas.Canvas;
import javax.imageio.ImageIO;
import javax.swing.event.ChangeListener;
import java.io.File;


public class Controller {

    int maxTiefe = 4;
    Color farbschema = Color.CYAN;


    Canvas canvas = new Canvas();

    public Controller() {


    }

    public void start(Stage primaryStage) {


        PythagorasBaum pb = new PythagorasBaum(275, 375, 500, 500, 0, 6, 45);
        canvas = pb.getCanvas();

        HBox buttons = addButtons(canvas, pb);
        VBox inputs = addInputs();

        BorderPane pane = new BorderPane();
        pane.setPadding(new Insets(15, 20, 10, 10));
        pane.setTop(buttons);
        pane.setCenter(canvas);
        pane.setLeft(inputs);


        Scene scene = new Scene(pane, 1000, 1000);
        primaryStage.setScene(scene);

        primaryStage.setTitle("Pythagoras Baum");
        primaryStage.show();

    }

    public HBox addButtons(Canvas canvas, PythagorasBaum pb) {

        Button sym = new Button("Symmetrisch");
        sym.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                pb.setMaxTiefe(maxTiefe);
                pb.drawTree(275, 500, 375, 500, 0, 45, farbschema);
            }

        });

        Button links = new Button("Links");
        links.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                pb.setMaxTiefe(maxTiefe);
                pb.drawTree(275, 500, 375, 500, 0, 50, Color.hsb(200, 1, 1));
            }

        });

        Button rechts = new Button("Rechts");
        rechts.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                pb.setMaxTiefe(maxTiefe);
                pb.drawTree(275, 500, 375, 500, 0, 30, Color.hsb(200, 1, 1));
            }

        });

        Button exit = new Button("Exit");
        exit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                Platform.exit();
            }

        });

        Button reset = new Button("Reset");
        reset.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                pb.getGc().clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
                pb.getGc().setFill(Color.WHITE);
                pb.getGc().fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
            }

        });

        Button save = new Button("Save");
        save.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                try {
                    Image snapshot = canvas.snapshot(null, null);
                    ImageIO.write(SwingFXUtils.fromFXImage(snapshot, null), "png", new File("screenshot.png"));
                } catch (Exception e) {
                    System.out.println("Failed to save Screenshot");
                }
            }

        });

        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(sym);
        hbBtn.getChildren().add(links);
        hbBtn.getChildren().add(rechts);
        hbBtn.getChildren().add(reset);
        hbBtn.getChildren().add(save);
        hbBtn.getChildren().add(exit);
        return hbBtn;

    }

    public VBox addInputs() {

        //maxTiefe Input und Button zum speichern
        TextField mT = new TextField();
        mT.setPromptText("Maximale Tiefe (pre: 4)");
        Button buttonTiefe = new Button("Speichere die maxTiefe");
        buttonTiefe.setOnAction(e -> getTextInt(mT));

        //Inputs fuer die Seiten der gemetrischen Formen und Button
        TextField a = new TextField();
        a.setPromptText("Seite A des Dreiecks");

        TextField b = new TextField();
        b.setPromptText("Seite B des Dreiecks");

        TextField c = new TextField();
        c.setPromptText("Groeße des 1. Quadrats");

        Button buttonGeo = new Button("Speichere Geometrie");
        buttonGeo.setOnAction(e -> getTextInt(mT));

        //Input fuer die Farbschemen und Button zum speichern
        ChoiceBox<String> cb = new ChoiceBox<>();
        cb.getItems().addAll("Cyan", "Rot", "Magenta");
        cb.setValue("Cyan");
        cb.setTooltip(new Tooltip("Wähle ein Farbschema"));

        Button buttonFarb = new Button("Speichere Farbschema");
        buttonFarb.setOnAction(e -> getChoice(cb));

        VBox vbBtn = new VBox(10);
        vbBtn.getChildren().add(mT);
        vbBtn.getChildren().add(buttonTiefe);

        vbBtn.getChildren().add(a);
        vbBtn.getChildren().add(b);
        vbBtn.getChildren().add(c);
        vbBtn.getChildren().add(buttonGeo);

        vbBtn.getChildren().add(cb);
        vbBtn.getChildren().add(buttonFarb);

        return vbBtn;

    }

    private void getChoice(ChoiceBox<String> cb) {

        String farbschemaTMP = cb.getValue();
        farbschema = farbschema(farbschemaTMP);

    }

    private void getTextInt(TextField t) {

        String newText = t.getText();

        if (newText.matches("-?([1-9][0-9]*)?")) {
            maxTiefe = Integer.parseInt(t.getText());
        }

    }

    private Color farbschema(String farbschemaTMP) {

        switch (farbschemaTMP) {

            case "Cyan":
                return Color.CYAN;
            case "Rot":
                return Color.RED;
            case "Magenta":
                return Color.MAGENTA;
            default:
                return Color.CYAN;

        }


    }
}


