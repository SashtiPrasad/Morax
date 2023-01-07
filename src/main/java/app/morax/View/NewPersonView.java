package app.morax.View;

import app.morax.Controller.NewPersonController;
import app.morax.Interface.ModelListener;
import app.morax.Model.Base.MainModel;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class NewPersonView extends StackPane implements ModelListener {

    private TextField nameText;
    private TextArea descriptionText;
    private Button addB;

    private Button cancelB = new Button("Cancel");


    public NewPersonView() {
        Label label1 = new Label("Add a new Person");

        Label label2 = new Label("Person Name: ");
        nameText = new TextField();
        HBox HBox1 = new HBox(label2, nameText);
        HBox1.setAlignment(Pos.TOP_LEFT);

        addB = new Button("Add");
        // Buttons
        HBox buttonsHBox = new HBox(addB, cancelB);
        buttonsHBox.setAlignment(Pos.TOP_CENTER);
        buttonsHBox.setSpacing(10);

        VBox mainVBox = new VBox(label1, HBox1, buttonsHBox);
        mainVBox.setAlignment(Pos.TOP_CENTER);
        mainVBox.setSpacing(5);
        mainVBox.setPadding(new Insets(5, 5, 5, 5));
        this.getChildren().add(mainVBox);
    }
    @Override
    public void update() {

    }

    @Override
    public void setModel(MainModel model) {

    }

    public void associateHandler(NewPersonController controller) {
        addB.setOnAction(controller::handleAddPerson);
        cancelB.setOnAction(controller::handleCancelB);
    }

    public String getTitle(){
        return this.nameText.getText();
    }

    public String getDescription(){
        return descriptionText.getText();
    }
}
