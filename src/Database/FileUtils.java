package Database;

import javafx.scene.media.Media;
import java.io.*;

/**
 * This class  will do everything related to files
 */
public class FileUtils {
    private final static String rememberMeFilePath = "database/txtFiles/rememberMe.txt";

    /**
     * loads a media
     * @param path the path
     * @return the media
     */
    public static Media loadMedia(String path) {
        File file = new File(path);
        return new Media(file.toURI().toString());
    }

    /**
     * saves login info
     * @param username the username
     * @param password the password
     * @throws IOException exception
     */
    public static void saveHim(String username,String password) throws IOException {
        File file = new File(rememberMeFilePath);
        if (!file.exists()) {
            file.createNewFile();
        }
        DataOutputStream out = new DataOutputStream(new FileOutputStream(file));
        String string = username + " " + password;
        out.writeBytes(string);
    }

    /**
     * reset login info
     */
    public static void doNotSaveHim() {
        File file = new File(rememberMeFilePath);
        if (file.exists()) {
            file.delete();
        }
    }

    /**
     * loads login info
     * @return login info
     * @throws IOException exception
     */
    public static String rememberUsername() throws IOException {
        String[] usernameAndPassword = processRememberHim(rememberHim());
        if (usernameAndPassword == null) {
            return null;
        }
        return usernameAndPassword[0];
    }

    /**
     * gets saved password
     * @return the password
     * @throws IOException exception
     */
    public static String rememberPassword() throws IOException {
        String[] usernameAndPassword = processRememberHim(rememberHim());
        if (usernameAndPassword == null) {
            return null;
        }
        return usernameAndPassword[1];
    }

    private static String rememberHim() throws IOException {
        File file = new File(rememberMeFilePath);
        if (!file.exists()) {
            return null;
        }
        DataInputStream in = new DataInputStream(new FileInputStream(file));
        return in.readLine();
    }

    private static String[] processRememberHim(String str) {
        if (str == null)
            return null;
        return str.split(" ");
    }
}
