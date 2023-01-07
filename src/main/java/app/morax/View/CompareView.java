package app.morax.View;

import app.morax.Controller.Controller;
import app.morax.Interface.ModelListener;
import app.morax.Model.Base.MainModel;
import app.morax.Model.Base.Task;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CompareView extends StackPane implements ModelListener {

    private MainModel model;
    private MainModel comparisonModel;

    private final ObservableList<DayComparison> days;

    public CompareView(){
        Controller controller = new Controller();
        controller.setCompareView(this);
        days = FXCollections.observableArrayList(new DayComparison("Drag a file to compare schedules", new ArrayList<>(), new ArrayList<>()));

        ListView<DayComparison> compareView = new ListView<>();
        compareView.setItems(days);
        compareView.setMinWidth(500);
        compareView.setPrefHeight(10000);

        this.getChildren().add(compareView);

        this.setAlignment(Pos.TOP_CENTER);
        this.setPadding(new Insets(2, 2, 2, 2));

        this.setOnDragOver(controller::handleCompare);
        this.setOnDragDropped(controller::handleDragDropped);
    }

    private static class DayComparison extends StackPane{
        public DayComparison(String date, ArrayList<Task> tasks, ArrayList<Task> comparisonTasks){
            VBox root = new VBox();

            Label title = new Label(date);
            title.setPadding(new Insets(2, 2, 2, 2));
            title.setStyle("-fx-background-color: rgba(255,183,0,0.38);" +
                    "-fx-text-fill: rgba(217,142,4,0.89)");

            //there is nothing to compare
            if (tasks.size() == 0 && comparisonTasks.size() == 0){
                root.getChildren().addAll(title);
                this.getChildren().add(root);
                return;
            }

            Label yours = new Label("Your Schedule");
            VBox containerOne = new VBox(yours);
            createComparison(tasks, containerOne);

            Label theirs = new Label("Their Schedule");
            VBox containerTwo = new VBox(theirs);
            createComparison(comparisonTasks, containerTwo);

            HBox comparison = new HBox();

            comparison.getChildren().addAll(containerOne, containerTwo);
            root.getChildren().addAll(title, comparison);
            this.getChildren().add(root);
        }

        private void createComparison(ArrayList<Task> Tasks, VBox container) {

            Font font = new Font(10);
            LocalDateTime taskDate;
            int time;
            Label info;
            for (Task task: Tasks){
                taskDate = task.getDate();
                time = taskDate.getHour() < 12 ? taskDate.getHour(): task.getDate().getHour() - 12;
                info = new Label("   • " + time  + ":" + taskDate.format(DateTimeFormatter.ofPattern("mm")) + " "
                        +  (taskDate.getHour() < 12 ? "AM ":"PM " + task.getName()));

                info.setFont(font);

                container.getChildren().add(info);
            }

            if (Tasks.size() == 0){
                info = new Label("   • None");
                container.getChildren().add(info);
            }
        }
    }

    @Override
    public void setModel(MainModel model) {
        this.model = model;
        this.update();
    }

    public void setComparisonModel(MainModel comparisonModel) {
        this.comparisonModel = comparisonModel;
        this.update();
    }

    @Override
    public void update() {

        days.clear();

        if (comparisonModel == null){
            days.add(new DayComparison("Drag a file to compare schedules", new ArrayList<>(), new ArrayList<>()));
            return;
        }

        Map<String, ArrayList<Task>> myTasksByDay = new HashMap<>();
        Map<String, ArrayList<Task>> comparisonTasksByDay = new HashMap<>();

        model.sortTaskList(2);
        comparisonModel.sortTaskList(2);

        //generate the keys
        for (String key:generateKeys(model)){
            myTasksByDay.put(key, new ArrayList<>());
            comparisonTasksByDay.put(key, new ArrayList<>());
        }
        //generate the keys
        for (String key:generateKeys(comparisonModel)){
            myTasksByDay.put(key, new ArrayList<>());
            comparisonTasksByDay.put(key, new ArrayList<>());
        }

        getTasksInDay(myTasksByDay, model);
        getTasksInDay(comparisonTasksByDay, comparisonModel);

        for (String key:myTasksByDay.keySet()){
            days.add(new DayComparison(key, myTasksByDay.get(key), comparisonTasksByDay.get(key)));
        }
    }

    private ArrayList<String> generateKeys(MainModel aModel) {

        ArrayList<String> output = new ArrayList<>();

        int year;
        String month;
        int day;
        String key;

        for (Task task: aModel.getTasks()){
            //generate the key
            year = task.getDate().getYear();
            month = task.getDate().getMonth().toString();
            day = task.getDate().getDayOfMonth();
            key = month + " " + day + " " + year;

            if (!output.contains(key)){
                output.add(key);
            }
        }

        return output;
    }

    /**
     * Method to organize tasks into lists keyed by date
     * @param dayToTasksMap the keyed map that the tasks will be organized into
     * @param model a model object
     */
    private void getTasksInDay(Map<String, ArrayList<Task>> dayToTasksMap, MainModel model) {
        //values will be keyed based off date
        int year;
        String month;
        int day;
        String key;

        for (Task task: model.getTasks()){
            //generate the key
            year = task.getDate().getYear();
            month = task.getDate().getMonth().toString();
            day = task.getDate().getDayOfMonth();
            key = month + " " + day + " " + year;

            dayToTasksMap.get(key).add(task);
        }
    }
}
