package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        // Set up a loader for the default scene's FXML
        FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("sample.fxml"));
        Parent root = fxmlloader.load();

        // Set up the default scene
        Scene mainScene = new Scene(root, 1000, 720);

        // Pass the main controller the default scene and the machine
        ((Controller) fxmlloader.getController()).initialise(new Machine(), new View());

        // Set up
        primaryStage.setTitle("Enigma I/M3 (1930/1939)");
        primaryStage.setScene(mainScene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
