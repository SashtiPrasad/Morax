package app.morax.View;

import app.morax.Controller.Controller;
import app.morax.Interface.ModelListener;
import app.morax.Model.Base.MainModel;
import app.morax.Model.Base.Task;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ListView;
import javafx.scene.layout.StackPane;
import java.util.ArrayList;

public class ScheduleDisplayView extends StackPane implements ModelListener {
    private ListView<DayView> scheduleList = new ListView<>();
    private ObservableList<DayView> scheduleObs = FXCollections.observableArrayList();
    private MainModel model;

    public ScheduleDisplayView() {
        this.scheduleList.setItems(scheduleObs);
        scheduleList.setPrefHeight(10000);

        this.getChildren().add(scheduleList);
        this.setAlignment(Pos.TOP_CENTER);
        this.setPadding(new Insets(2, 2, 2, 2));
    }

    @Override
    public void update() {
        updateScheduleObs();
    }

    @Override
    public void setModel(MainModel model) {
        this.model = model;
        this.update();
    }

    public void associateHandler(Controller controller) {

    }

    private void updateScheduleObs() {
        scheduleObs.clear();

        ArrayList<DayView> days = new ArrayList<>();
        ArrayList<Task> dayTasks = new ArrayList<>();

        model.sortTaskList(2);

        ArrayList<Task> sortedTasks = model.getTasks();

        if (sortedTasks.size() == 0) return;

        int day = sortedTasks.get(0).getDate().getDayOfMonth();
        int month = sortedTasks.get(0).getDate().getMonth().getValue();
        for (Task t : sortedTasks) {
            if (t.getDate().getDayOfMonth() == day && t.getDate().getMonth().getValue() == month) {
                dayTasks.add(t);
            }
            else {
                DayView dayView = new DayView(String.valueOf(day), MainUI.getMonth(month), dayTasks, this.model);
                days.add(dayView);
                dayTasks.clear();
                dayTasks.add(t);
                day = t.getDate().getDayOfMonth();
                month = t.getDate().getMonth().getValue();
            }
            if (sortedTasks.indexOf(t) + 1 == sortedTasks.size()) {
                DayView dayView = new DayView(String.valueOf(day), MainUI.getMonth(month), dayTasks, this.model);
                days.add(dayView);
                dayTasks.clear();
            }
        }
        this.scheduleObs.addAll(days);
    }
}
