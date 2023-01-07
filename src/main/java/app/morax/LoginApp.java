package app.morax;

import app.morax.Controller.LoginControls;
import app.morax.View.LoginView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class LoginApp extends Application {

    App ourApp;

    @Override
    public void start(Stage stage){
        LoginView root = new LoginView();

        Stage ourStage = new Stage();
        ourApp = new App();
        ourApp.start(ourStage);

        LoginControls controls = new LoginControls();
        //connect everything up
        controls.setStage(stage);
        controls.setLoginView(root);
        controls.setAlternateStage(ourStage);

        root.setController(controls);

        Scene mainScene = new Scene(root, 1000, 450);
        mainScene.getStylesheets().add("primarySkin.css");
        stage.setScene(mainScene);
        stage.setTitle("Login");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void stop() throws Exception {
        ourApp.stop();
    }
}
