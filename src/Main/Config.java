package Main;
import Models.Client;
import javafx.stage.Stage;
import java.sql.Statement;

public class Config {
    //fields
    public static Stage primaryStage;
    public static Client client;
    public static Statement statement;

    //methods
    public static void unknownInputException() {
        try {
            throw new Exception("Unknown Input!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
