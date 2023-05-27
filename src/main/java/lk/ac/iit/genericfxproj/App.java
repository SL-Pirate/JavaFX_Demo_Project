package lk.ac.iit.genericfxproj;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {

    @Override

    public void start(Stage stage) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("login.fxml"));

        Scene scene = new Scene(fxmlLoader.load());

        stage.setTitle("Log In!"); //adding control to layout

        stage.setScene(scene);

        stage.show();

    }
}