package Main;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.sql.*;

/**
 * The Main.
 */
public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/Views/FXMLFiles/login.fxml"));
        primaryStage.setTitle("Clash Royale");
        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(root,400,520));
        Config.primaryStage = primaryStage;
        primaryStage.show();
    }

    /**
     * Connects to the game's database.
     */
    private static void connectToDatabase() {
        String url = "jdbc:mysql://localhost:3306";
        String user = "root";
        String pass = "99clash31royale";

        try {
            Connection connection = DriverManager.getConnection(url, user, pass);
            Config.statement = connection.createStatement();
            Config.statement.execute("use clash_royale");
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        connectToDatabase();
        Config.fillCards();
        launch(args);
    }
}
