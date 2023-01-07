package app.morax.Controller;

import app.morax.Model.Base.MainModel;
import app.morax.View.NewCategoryView;
import app.morax.View.CategoriesView;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class CategoryController {

    private MainModel model;
    private CategoriesView view;
    private Stage stage;

    public void setModel(MainModel model) {
        this.model = model;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setView(CategoriesView view) {
        this.view = view;
    }

    //bring up the add Category page to enter information about the category
    public void handleAddCategory(ActionEvent e) {
        view.getErrorL().setVisible(false);
        NewCategoryView thisView = new NewCategoryView();
        NewCategoryController thisController = new NewCategoryController();

        //link the new view and its controller
        thisView.associateHandler(thisController);
        thisController.setView(thisView);

        //link the model and controller
        thisController.setModel(model);

        //open a new window
        Stage addCategoriesStage = new Stage();
        addCategoriesStage.setScene(new Scene(thisView, 270, 170));
        addCategoriesStage.getScene().getStylesheets().add("secondarySkin.css");
        thisController.setStage(addCategoriesStage);
        addCategoriesStage.show();
    }

    //Remove the selected category
    public void handleRemoveCategory(ActionEvent e){
        if (view.getSelection() == null) {
            view.getErrorL().setVisible(true);
        }
        else {
            view.getErrorL().setVisible(false);
            model.removeCategory(view.getSelection());
        }
    }

    public void handleCancel(ActionEvent e){
        stage.close();
    }
}
