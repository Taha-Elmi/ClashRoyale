package Controllers.LoginandSignup;

import Models.Graphic.FXManager;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;
public class SignupCon extends Controller {

    @FXML
    private PasswordField confirmPasswordField;

    @Override
    public void initialize() {
        super.initialize();
        confirmPasswordField.styleProperty().bind(
                Bindings
                        .when(confirmPasswordField.focusedProperty())
                        .then("-fx-prompt-text-fill: derive(-fx-control-inner-background, -30%);")
                        .otherwise("-fx-prompt-text-fill: derive(-fx-control-inner-background, -30%);"));
    }
    @Override
    protected void loginButtonPressed() {
        FXManager.goTo("login.fxml",(Stage) loginButton.getScene().getWindow());
    }

    @Override
    protected void signupButtonPressed() {
        signupProcess();
        FXManager.goTo("MainMenu.fxml",(Stage) signupButton.getScene().getWindow());
    }
    private void signupProcess() {
        System.out.println("signup");
    }
}
