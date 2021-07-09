package Models.Graphic;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
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
    public static void openWindow(String fxmlName) {
        try {
            Parent root = FXMLLoader.load(FXManager.class.getResource(
                    "/Views/FXMLFiles/" + fxmlName));
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.sizeToScene();
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public static void setBackground(Image image,Pane pane) {
        BackgroundImage backgroundimage = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                new BackgroundSize(1,1,
                        true, true,
                        false, false));
        pane.setBackground(new Background(backgroundimage));
    }
    public static Image getImage(String imageName) {
        return new Image(FXManager.class.getResourceAsStream("/Views/Assets/Pictures/BackGrounds/" + imageName));
    }
}
