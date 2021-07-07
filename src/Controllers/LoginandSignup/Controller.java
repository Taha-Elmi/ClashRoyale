package Controllers.LoginandSignup;

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
        setAllFocusTraversable(false);
    }

    private void setAllFocusTraversable(boolean b) {
        usernameTextField.setFocusTraversable(b);
        passwordTextField.setFocusTraversable(b);
        loginButton.setFocusTraversable(b);
        signupButton.setFocusTraversable(b);
    }
    protected abstract void loginButtonPressed();
    protected abstract void signupButtonPressed();
}
