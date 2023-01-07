package app.morax;

import app.morax.Model.Base.MainModel;
import app.morax.View.MainUI;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

    MainModel model;
    Stage stage;

    @Override
    public void start(Stage primaryStage) {

        model = MainModel.loadFromFile("saved.o");
        if (model == null) model = new MainModel();

        stage = primaryStage;

        MainUI mainUI = new MainUI(model);
        Scene mainScene = new Scene(mainUI, 1000, 450);
        mainScene.getStylesheets().add("primarySkin.css");
        primaryStage.setScene(mainScene);
        primaryStage.setTitle("Calendar");
    }

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void stop(){
        model.saveToFile("saved.o");
    }
}