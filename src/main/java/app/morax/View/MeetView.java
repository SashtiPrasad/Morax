package app.morax.View;

import app.morax.Controller.MeetController;
import app.morax.Interface.ModelListener;
import app.morax.Model.Base.MainModel;
import app.morax.Model.Base.Person;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class MeetView extends StackPane implements ModelListener {

    private MainModel model;

    private Button addPersonB;
    private Button removePersonB;
    private Button meetB;
    private Button cancelB;

    private ObservableList<Person> personsObs = FXCollections.observableArrayList();
    private ListView<Person> personsList;

    private Label errorL = new Label("Please select an item");


    public MeetView() {


        // The Left VBox
        personsList = new ListView<>();
        VBox leftVBox = new VBox(personsList);
        leftVBox.setAlignment(Pos.CENTER);
        leftVBox.setSpacing(5);
        leftVBox.setPrefWidth(200);
        leftVBox.setPrefHeight(300);



        // The Right VBox
        meetB = new Button("Meet");
        meetB.setStyle("-fx-background-color: linear-gradient(#d38836, Yellow)");
        addPersonB = new Button("Add Person");
        removePersonB = new Button("Remove Person");
        cancelB = new Button("Cancel");
        errorL.setVisible(false);
        errorL.setStyle("-fx-text-fill: Red;" + "-fx-font-size: 12;" + "-fx-font-family: Arial");
        VBox rightVBox = new VBox(meetB, addPersonB, removePersonB, cancelB, errorL);
        rightVBox.setSpacing(10);
        rightVBox.setAlignment(Pos.TOP_LEFT);

        // The content HBox
        HBox contentHBox = new HBox(leftVBox, rightVBox);
        contentHBox.setSpacing(5);

        // The main VBox
        Label title1 = new Label("All people to meet");
        leftVBox.setAlignment(Pos.CENTER);
        leftVBox.setSpacing(5);
        VBox mainVBox = new VBox(title1, contentHBox);
        mainVBox.setAlignment(Pos.TOP_CENTER);
        mainVBox.setSpacing(5);
        mainVBox.setPadding(new Insets(5, 5, 5, 5));


        this.getChildren().add(mainVBox);
    }

    @Override
    public void update() {
        personsObs.clear();;
        personsObs.addAll(model.getPeople().values());
        personsList.setItems(personsObs);

    }

    @Override
    public void setModel(MainModel model) {
        this.model = model;
    }

    public Person getSelection(){
        return this.personsList.getSelectionModel().getSelectedItem();
    }

    public void associateHandler(MeetController controller) {
        addPersonB.setOnAction(controller::handleAddPerson);
        removePersonB.setOnAction(controller::handleRemovePerson);
        meetB.setOnAction(controller::handleMeetB);
        cancelB.setOnAction(controller::handleCancelB);
    }

    public Label getErrorL() {
        return errorL;
    }
}
