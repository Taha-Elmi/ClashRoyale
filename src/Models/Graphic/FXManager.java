package Models.Graphic;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class FXManager {
    public static void goTo(String fxmlName,Stage stage) {
        Parent root = null;
        try {
            root = FXMLLoader.load(FXManager.class.getResource("/Views/FXMLFiles/" + fxmlName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert root != null;
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
