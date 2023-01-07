package app.morax.View;

import app.morax.Controller.CategoryController;
import app.morax.Interface.ModelListener;
import app.morax.Model.Base.Category;
import app.morax.Model.Base.MainModel;
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

public class CategoriesView extends StackPane implements ModelListener {

    private MainModel model;

    private Button addCategoryB;

    private Button removeCategoryB;

    private Button cancelB;

    private Label errorL;

    private ObservableList<Category> categoriesObs = FXCollections.observableArrayList();

    private ListView<Category> categoriesL;

    public CategoriesView() {
        // The Left VBox
        categoriesL = new ListView<>();
        VBox leftVBox = new VBox(categoriesL);
        leftVBox.setAlignment(Pos.CENTER);
        leftVBox.setSpacing(5);
        leftVBox.setPrefWidth(200);
        leftVBox.setPrefHeight(300);



        // The Right VBox
        addCategoryB = new Button("Add Category");
        removeCategoryB = new Button("Remove Category");
        cancelB = new Button("Cancel");
        errorL = new Label("Please Select an item");
        errorL.setStyle("-fx-text-fill: Red;" + "-fx-font-size: 12;" + "-fx-font-family: Arial");
        errorL.setVisible(false);
        VBox rightVBox = new VBox(addCategoryB, removeCategoryB, cancelB, errorL);
        rightVBox.setSpacing(10);
        rightVBox.setAlignment(Pos.TOP_LEFT);

        // The content HBox
        HBox contentHBox = new HBox(leftVBox, rightVBox);
        contentHBox.setSpacing(5);

        // The main VBox
        Label title1 = new Label("All Categories");
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
        categoriesObs.clear();
        categoriesObs.addAll(model.getCategories());

        this.categoriesL.setItems(categoriesObs);
    }

    @Override
    public void setModel(MainModel model) {
        this.model = model;
        this.update();
    }

    public void associateHandler(CategoryController controller) {
        addCategoryB.setOnAction(controller::handleAddCategory);
        removeCategoryB.setOnAction(controller::handleRemoveCategory);
        cancelB.setOnAction(controller::handleCancel);
    }

    public Category getSelection(){
        return this.categoriesL.getSelectionModel().getSelectedItem();
    }

    public Label getErrorL() {
        return errorL;
    }
}


