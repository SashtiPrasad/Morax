package app.morax.View;

import app.morax.Controller.Controller;
import app.morax.Interface.ModelListener;
import app.morax.Model.Base.MainModel;
import javafx.geometry.Pos;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;

public class MainUI extends StackPane implements ModelListener {

    public enum View {COMPARE, ACTIVITIES, CHART}
    private View currentMainView;

    private ScheduleDisplayView scheduleDisplayView;
    private MenuView menuBarView;
    private ActivitiesListView activitiesListView;
    private MainModel mainModel;

    private BorderPane mainPane;
    private ChartView chartView;
    private CompareView compareView;

    public MainUI(MainModel model) {

        // creating the MVC objects
        Controller controller = new Controller();
        activitiesListView = new ActivitiesListView();
        activitiesListView.setPrefWidth(270);
        scheduleDisplayView = new ScheduleDisplayView();

        //create a stackPane, so we can stack the views and switch between them
        StackPane switchViews = new StackPane();

        //keep track of which of these we are viewing
        currentMainView = View.ACTIVITIES;

        scheduleDisplayView = new ScheduleDisplayView();
        chartView = new ChartView();
        compareView = new CompareView();

        this.switchView();

        switchViews.getChildren().addAll(compareView, chartView, scheduleDisplayView);
        switchViews.setOnDragOver(controller::handleCompare);
        switchViews.setOnDragDropped(controller::handleDragDropped);

        menuBarView = new MenuView();

        // linking the MVC objects
        controller.setModel(model);
        compareView.setModel(model);
        scheduleDisplayView.setModel(model);
        chartView.setModel(model);
        activitiesListView.setModel(model);
        menuBarView.setModel(model);
        this.setModel(model);

        scheduleDisplayView.associateHandler(controller);
        activitiesListView.associateHandler(controller);
        menuBarView.associateHandler(controller);
        this.associateHandler(controller);

        model.addSubscriber(scheduleDisplayView);
        model.addSubscriber(activitiesListView);
        model.addSubscriber(menuBarView);
        model.addSubscriber(chartView);
        model.addSubscriber(compareView);
        model.addSubscriber(this);

        controller.setView(this);
        controller.setCompareView(compareView);

        // setting up the borderPane
        mainPane = new BorderPane();
        mainPane.setCenter(switchViews);
        mainPane.setRight(activitiesListView);
        mainPane.setTop(menuBarView);

        this.getChildren().add(mainPane);
        this.setAlignment(Pos.CENTER);
    }


    @Override
    public void update() {

    }

    @Override
    public void setModel(MainModel model) {
        this.mainModel = model;
    }

    public void associateHandler(Controller controller) {

    }

    public static String getMonth(int i) {
        String out;
        switch (i) {
            case 1:
                out = "January";
                break;
            case 2:
                out = "February";
                break;
            case 3:
                out ="March";
                break;
            case 4:
                out = "April";
                break;
            case 5:
                out = "May";
                break;
            case 6:
                out = "June";
                break;
            case 7:
                out = "July";
                break;
            case 8:
                out = "August";
                break;
            case 9:
                out = "September";
                break;
            case 10:
                out = "October";
                break;
            case 11:
                out = "November";
                break;
            case 12:
                out = "December";
                break;
            default:
                out = null;
        }
        return out;
    }

    public void switchView() {
        switch (currentMainView){
            case ACTIVITIES:
                this.chartView.setVisible(false);
                this.scheduleDisplayView.setVisible(true);
                this.compareView.setVisible(false);
                break;
            case CHART:
                this.chartView.setVisible(true);
                this.scheduleDisplayView.setVisible(false);
                this.compareView.setVisible(false);
                break;
            case COMPARE:
                this.chartView.setVisible(false);
                this.scheduleDisplayView.setVisible(false);
                this.compareView.setVisible(true);

        }
    }

    public void setCurrentMainView(View view){
        this.currentMainView = view;
        switchView();
    }
}
