package app.morax.Controller;

import app.morax.Model.Base.MainModel;
import app.morax.Model.Base.Task;
import javafx.event.ActionEvent;

public class TaskController extends Controller{

    private Task task;
    private MainModel model;

    @Override
    public void setModel(MainModel model) {
        this.model = model;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public void handleFinish(ActionEvent ignored){
        model.taskComplete(task);
    }

    public void handleDelete(ActionEvent actionEvent) {
        model.removeTask(task);
    }
}
