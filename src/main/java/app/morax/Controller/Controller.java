package app.morax.Controller;

import app.morax.Model.Base.MainModel;
import app.morax.View.*;
import app.morax.View.MeetView;
import app.morax.View.NewActivityView;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.stage.Stage;

public class Controller {

    private MainModel model;
    private MainUI view;
    private CompareView compareView;

    public void setModel(MainModel model) {
        this.model = model;
    }

    public void setView(MainUI view) {
        this.view = view;
    }

    public void setCompareView(CompareView compareView) {
        this.compareView = compareView;
    }

    public void handleNewActivityB(ActionEvent ignored) {
        // creating the components
        NewActivityView newActivityView = new NewActivityView();
        NewActivityController newActivityController = new NewActivityController();


        // linking components
        newActivityView.setModel(model);
        newActivityView.associateHandler(newActivityController);
        newActivityController.setView(newActivityView);
        newActivityController.setModel(model);
        model.addSubscriber(newActivityView);

        // Starting new window
        Stage newActivityStage = new Stage();
        newActivityStage.setScene(new Scene(newActivityView, 340, 290));
        newActivityStage.getScene().getStylesheets().add("secondarySkin.css");
        newActivityController.setStage(newActivityStage);
        newActivityStage.show();
    }

    public void handleCategoriesB(ActionEvent ignored) {
        CategoriesView thisView = new CategoriesView();
        CategoryController thisController = new CategoryController();

        //linking everything together
        thisController.setModel(model);
        thisController.setView(thisView);

        thisView.setModel(model);
        thisView.associateHandler(thisController);

        model.addSubscriber(thisView);
        thisView.update();

        //opening the new window
        Stage categoriesStage = new Stage();
        //make it so the controller has controller over the stage
        thisController.setStage(categoriesStage);

        categoriesStage.setScene(new Scene(thisView, 350, 240));
        categoriesStage.getScene().getStylesheets().add("secondarySkin.css");
        categoriesStage.show();
    }

    //Handles the sort parameters changing for the activities view
    public void handleSort(ActionEvent ignored){
        if (model.getTasks().size() != 0) {
            model.updateSubscribers();
        }
    }

    public void handleSwitchView(ActionEvent ignored){
        view.switchView();
    }

    public void handleSettingB(ActionEvent actionEvent) {
    }

    public void handleMeetB(ActionEvent actionEvent) {
        MeetView meetView = new MeetView();
        MeetController meetController = new MeetController();

        // linking everything together
        meetController.setModel(model);
        meetController.setView(meetView);

        meetView.setModel(model);
        meetView.associateHandler(meetController);

        model.addSubscriber(meetView);
        meetView.update();

        // opening the new window
        Stage meetStage = new Stage();
        // make it so the controller has controller over the stage
        meetController.setStage(meetStage);

        meetStage.setScene(new Scene(meetView, 335, 250));
        meetStage.getScene().getStylesheets().add("secondarySkin.css");
        meetStage.show();
    }

    public void handleCompare(DragEvent dragEvent) {
        Dragboard dragboard = dragEvent.getDragboard();
        if (dragboard.hasFiles()){
            dragEvent.acceptTransferModes(TransferMode.ANY);
        }
        else{
            dragEvent.consume();
        }
    }

    public void handleDragDropped(DragEvent dragEvent) {
        Dragboard dragboard = dragEvent.getDragboard();

        if (!dragboard.hasFiles()) return;

        String path = dragboard.getFiles().get(0).getAbsolutePath();
        MainModel comparisonModel = MainModel.loadFromFile(path);

        if (comparisonModel == null) new ErrorMessage("File is not a valid type");
        compareView.setComparisonModel(comparisonModel);
    }

    public void handleSwitchViewSchedule(ActionEvent actionEvent) {
        this.view.setCurrentMainView(MainUI.View.ACTIVITIES);
    }

    public void handleSwitchViewChart(ActionEvent actionEvent) {
        this.view.setCurrentMainView(MainUI.View.CHART);
    }

    public void handleSwitchViewCompare(ActionEvent actionEvent) {
        this.view.setCurrentMainView(MainUI.View.COMPARE);
    }
}
