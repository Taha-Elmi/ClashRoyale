package Controllers.LoginandSignup;

import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
public abstract class Controller {
    @FXML
    protected Button loginButton;
    @FXML
    protected Button signupButton;
    @FXML
    protected TextField usernameTextField;
    @FXML
    protected TextField passwordTextField;

    @FXML
    protected void buttonPressed(ActionEvent actionEvent) {
        if (actionEvent.getSource() == loginButton) {
            loginButtonPressed();
        } else if (actionEvent.getSource() == signupButton) {
            signupButtonPressed();
        }
    }
    public void initialize() {
        usernameTextField.styleProperty().bind(
                Bindings
                        .when(usernameTextField.focusedProperty())
                        .then("-fx-prompt-text-fill: derive(-fx-control-inner-background, -30%);")
                        .otherwise("-fx-prompt-text-fill: derive(-fx-control-inner-background, -30%);"));
        passwordTextField.styleProperty().bind(
                Bindings
                        .when(passwordTextField.focusedProperty())
                        .then("-fx-prompt-text-fill: derive(-fx-control-inner-background, -30%);")
                        .otherwise("-fx-prompt-text-fill: derive(-fx-control-inner-background, -30%);"));

    }

    protected abstract void loginButtonPressed();
    protected abstract void signupButtonPressed();
}
