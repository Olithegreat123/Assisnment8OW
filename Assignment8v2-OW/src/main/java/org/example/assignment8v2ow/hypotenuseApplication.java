package org.example.assignment8v2ow;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class hypotenuseApplication extends Application {
    // Override the start method from Application class
    @Override
    public void start(Stage stage) throws IOException {
        // Load the FXML file (hypotenuse-view.fxml) which contains the UI layout
        Parent root = FXMLLoader.load(hypotenuseApplication.class.getResource("hypotenuse-view.fxml"));

        // Create a new scene with the loaded UI layout, setting dimensions
        Scene scene = new Scene(root, 320, 240);

        // Set the title of the stage/window
        stage.setTitle("Hypotenuse Calculator");

        // Set the scene for the stage
        stage.setScene(scene);

        // Make the stage visible
        stage.show();
    }

    // The main method, entry point of the Java application
    public static void main(String[] args) {
        // Launch the JavaFX application
        launch();
    }
}
