package app.morax.View;

import app.morax.Controller.MeetingDateController;
import app.morax.Interface.ModelListener;
import app.morax.Model.Base.HourModel;
import app.morax.Model.Base.MainModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class MeetingDateView extends StackPane implements ModelListener {

    public DatePicker DT = new DatePicker();
    private TextField timeText = new TextField();

    public ComboBox<String> PmAmC = new ComboBox<>();
    private ObservableList<String> cOptions = FXCollections.observableArrayList("PM", "AM");

    private MainModel model;

    private Button setB = new Button("Set");
    private Button cancelB = new Button("Cancel");
    private String personName;

    public MeetingDateView(String name) {
        personName = name;
        PmAmC.setItems(cOptions);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String currentTime = dtf.format(now);

        Label label1 = new Label("Assign a New Meeting");

        Label label2 = new Label("Name: " + name);
        HBox nameHBox = new HBox(label2);
        nameHBox.setAlignment(Pos.CENTER_LEFT);

        Label title3 = new Label("Date: ");
        DT.setMaxWidth(120);
        DT.getEditor().setText(currentTime.substring(0, 4) + "-" + currentTime.substring(5, 7) + "-" + currentTime.substring(8, 10));
        HBox dateHBox = new HBox(title3, DT);
        dateHBox.setAlignment(Pos.CENTER_LEFT);

        Label title4 = new Label("Set Time: ");

        HourModel HM = new HourModel(currentTime.substring(11, 13), null);

        timeText.setText(HM.getHour12() + ":" + currentTime.substring(14, 16));
        timeText.setMaxWidth(50);
        PmAmC.setValue(HM.getAMPM());
        PmAmC.setMinWidth(10);
        HBox setTimeH = new HBox(title4, timeText, PmAmC);
        setTimeH.setSpacing(5);
        setTimeH.setAlignment(Pos.CENTER_LEFT);

        // Buttons
        HBox buttonsHBox = new HBox(setB, cancelB);
        buttonsHBox.setAlignment(Pos.TOP_CENTER);
        buttonsHBox.setSpacing(10);


        VBox mainVBox = new VBox(label1, nameHBox, dateHBox, setTimeH, buttonsHBox);
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
        this.model = model;
    }

    public void associateHandler(MeetingDateController meetingDateController) {
        this.setB.setOnAction(meetingDateController::handleSetB);
        this.cancelB.setOnAction(meetingDateController::handleCancelB);
    }

    public String getPersonName() {
        return personName;
    }

    public TextField getTimeText() {
        return timeText;
    }

}
