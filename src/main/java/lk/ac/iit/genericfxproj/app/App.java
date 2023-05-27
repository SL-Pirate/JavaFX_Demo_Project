package lk.ac.iit.genericfxproj.app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lk.ac.iit.genericfxproj.Launch;
import java.io.IOException;

public class App extends Application {

    @Override

    public void start(Stage stage) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(Launch.class.getResource("login.fxml"));

        Scene scene = new Scene(fxmlLoader.load());

        stage.setTitle("Log In!"); //adding control to layout

        stage.setScene(scene);

        stage.show();

    }
}