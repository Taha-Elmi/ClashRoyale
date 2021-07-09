package Controllers.LoginandSignup;

import Main.Config;
import Models.Client;
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
        //sample client.
        Config.client = new Client("Farid",0,1);
    }
    @Override
    protected void signupButtonPressed() {
        FXManager.goTo("signup.fxml",(Stage) signupButton.getScene().getWindow());
    }
}
