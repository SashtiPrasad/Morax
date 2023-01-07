package app.morax.Controller;

import app.morax.Model.Base.Category;
import app.morax.Model.Base.MainModel;
import app.morax.View.ErrorMessage;
import app.morax.View.NewCategoryView;
import javafx.event.ActionEvent;
import javafx.stage.Stage;

public class NewCategoryController {

    private MainModel model;
    private NewCategoryView view;
    private Stage stage;

    public void setModel(MainModel model) {
        this.model = model;
    }

    public void setView(NewCategoryView view) {
        this.view = view;
    }

    //add a new Category
    public void handleAddCategory(ActionEvent e){
        Category c = new Category(view.getTitle(), 0, 12, Integer.parseInt(view.getTime()));
        if (model.categoryExists(c.getName())){
            categoryWithNameExistsError(c.getName());
            return;
        }
        model.addCategory(c);
        this.stage.close();
    }

    private void categoryWithNameExistsError(String name){
        ErrorMessage error = new ErrorMessage("Category with name: " + name + " already exists");
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void handleCancelB(ActionEvent actionEvent) {
        this.stage.close();
    }
}
