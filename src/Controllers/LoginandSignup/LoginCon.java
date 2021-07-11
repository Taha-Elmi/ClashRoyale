package Controllers.LoginandSignup;

import Main.Config;
import Models.Client;
import Models.Graphic.FXManager;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginCon extends Controller {

    @FXML
    private TextField usernameTextField;

    @FXML
    private TextField passwordField;

    @FXML
    private Label warningLabel;

    @Override
    protected void loginButtonPressed() {
        loginProcess();
        FXManager.goTo("MainMenu.fxml",(Stage) loginButton.getScene().getWindow());
    }

    private void loginProcess() {

        

        System.out.println("login");
        //sample client.
        Config.client = new Client("Farid",0,1);
    }
    @Override
    protected void signupButtonPressed() {
        FXManager.goTo("signup.fxml",(Stage) signupButton.getScene().getWindow());
    }
}
