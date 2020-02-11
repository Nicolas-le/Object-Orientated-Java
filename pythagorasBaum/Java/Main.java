package sample;

import javafx.application.*;
import javafx.stage.Stage;


public class Main extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception {

        Controller fx = new Controller();
        fx.start(primaryStage);

    }



    public static void main(String[] args) {
        launch(args);

    }
}
