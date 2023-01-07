package app.morax.View;

import app.morax.Controller.TaskController;
import app.morax.Model.Base.HourModel;
import app.morax.Model.Base.MainModel;
import app.morax.Model.Base.Task;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class DayView extends VBox {

    private Label dateLabel;

    private ObservableList<Task> taskObservableList = FXCollections.observableArrayList();

    DayView(String date, String month, ArrayList<Task> tasks, MainModel model) {
        this.dateLabel = new Label(" " + month + " " + date + "                                                                               ");
        this.dateLabel.setPadding(new Insets(2, 2, 2, 2));
        this.getChildren().add(dateLabel);
        dateLabel.setStyle("-fx-background-color: rgba(255,183,0,0.38);" +
                "-fx-text-fill: rgba(217,142,4,0.89)");
        for (Task t : tasks) {
            Label category;
            Label time;
            HourModel hm = new HourModel(String.valueOf(t.getDate().getHour()), null);
            time = new Label("   • " + hm.getHour12()  + ":" + t.getDate().format(DateTimeFormatter.ofPattern("mm")) + " " +  hm.getAMPM());
            if (t.isMeeting()) category = new Label("                " + "Meeting");
            else category = new Label("                " + t.getCategory().getName());
            category.setStyle("-fx-font-size: 15;" + "-fx-text-fill: #bb7a00;" + "-fx-font-family: Arial;");
            time.setStyle("-fx-font-size: 12;" + "-fx-text-fill: Black;" + "-fx-font-family: Arial;");
            time.setAlignment(Pos.CENTER_LEFT);
            Label task = new Label(t.getName());

            //the button to complete a task and take it off the list
            Button finishB = new Button("Finish");

            //Button to delete a task
            Button delete = new Button("Delete");

            TaskController buttonControl = new TaskController();
            buttonControl.setTask(t);
            buttonControl.setModel(model);

            finishB.setOnAction(buttonControl::handleFinish);
            delete.setOnAction(buttonControl::handleDelete);

            HBox leftHBox = new HBox(time, task);
            leftHBox.setAlignment(Pos.CENTER_LEFT);
            leftHBox.setSpacing(5);
            HBox rightHBox = new HBox(category, finishB, delete);
            rightHBox.setAlignment(Pos.CENTER_RIGHT);
            rightHBox.setSpacing(10);


            AnchorPane anchorPane = new AnchorPane(leftHBox, rightHBox);
            anchorPane.setPrefSize(550, 30);
            AnchorPane.setRightAnchor(rightHBox, 10.0);
            AnchorPane.setLeftAnchor(leftHBox, 0.0);



            HBox taskHB = new HBox(anchorPane);
            taskHB.setSpacing(15);
            taskHB.setAlignment(Pos.CENTER_LEFT);
            this.getChildren().add(taskHB);
        }
        this.setSpacing(10);
    }

    public void setOBS(ObservableList<Task> tasksOBS) {
        this.taskObservableList = tasksOBS;
    }

    public ObservableList<Task> getOBS() {
        return taskObservableList;
    }

    public void setDateLabel(String dateLabel) {
        this.dateLabel.setText(dateLabel);
    }

    public String getDateLabel() {
        return dateLabel.getText();
    }

}
