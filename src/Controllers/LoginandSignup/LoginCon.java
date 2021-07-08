package Controllers.LoginandSignup;

import Models.Graphic.FXManager;
import javafx.stage.Stage;

public class LoginCon extends Controller {


    @Override
    protected void loginButtonPressed() {
        loginProcess();
        FXManager.goTo("MainMenu.fxml",(Stage) loginButton.getScene().getWindow());
    }

    private void loginProcess() {
        System.out.println("login");
    }
    @Override
    protected void signupButtonPressed() {
        FXManager.goTo("signup.fxml",(Stage) signupButton.getScene().getWindow());
    }
}
