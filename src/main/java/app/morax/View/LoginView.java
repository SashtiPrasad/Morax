package app.morax.View;

import app.morax.Controller.LoginControls;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class LoginView extends StackPane {

    private TextField username;
    private PasswordField password;
    Button loginButton;

    public LoginView(){
        BorderPane root = new BorderPane();

        VBox infoContainer = new VBox();

        Label userNameL = new Label("UserName: ");
        username = new TextField();
        HBox userContainer = new HBox(userNameL, username);

        Label passwordLabel = new Label("Password: ");
        password = new PasswordField();
        HBox passwordContainer = new HBox(passwordLabel, password);

        loginButton = new Button("Login");

        infoContainer.getChildren().addAll(userContainer, passwordContainer, loginButton);

        root.setCenter(infoContainer);
        this.getChildren().add(root);
    }

    public String getUsername() {
        return username.getText();
    }

    public String getPassword() {
        return password.getText();
    }

    public void setController(LoginControls control){
        this.loginButton.setOnAction(control::handleLogin);
    }
}
