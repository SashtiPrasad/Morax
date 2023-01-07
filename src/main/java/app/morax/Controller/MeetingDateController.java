package app.morax.Controller;

import app.morax.Model.Base.MainModel;
import app.morax.Model.Base.Task;
import app.morax.View.MeetingDateView;
import javafx.event.ActionEvent;
import javafx.stage.Stage;
import java.time.LocalDateTime;

public class MeetingDateController {

    private MeetingDateView view;
    private MainModel model;
    private Stage stage;


    public void setView(MeetingDateView meetingDateView) {
        this.view = meetingDateView;
    }

    public void setModel(MainModel model) {
        this.model = model;
    }

    public void handleSetB(ActionEvent actionEvent) {
        int PMAdditional = 0;
        if (this.view.PmAmC.getValue().equals("PM")) {
            PMAdditional += 12;
        }

        //YYYY/MM/DD HH:mm:ss
        int year = Integer.parseInt(this.view.DT.getEditor().getText(0,4));
        int month = Integer.parseInt(this.view.DT.getEditor().getText(5, 7));
        int day = Integer.parseInt(this.view.DT.getEditor().getText(8, 10));
        int hour = Integer.parseInt(this.view.getTimeText().getText(0, 2)) + PMAdditional;
        int min = Integer.parseInt(this.view.getTimeText().getText(3, 5));

        hour = hour > 23 ? 0 : hour;

        LocalDateTime thisT = LocalDateTime.of(year, month, day, hour, min);


        Task meeting = new Task(thisT, model.getPeople().get(view.getPersonName()));
        model.addTask(meeting);
        this.stage.close();
    }

    public void handleCancelB(ActionEvent actionEvent) {
        this.stage.close();
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
}
