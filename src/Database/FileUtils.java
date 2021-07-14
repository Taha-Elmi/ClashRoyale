package Database;

import java.io.*;

public class FileUtils {
    private final static String rememberMeFilePath = "database/txtFiles/rememberMe.txt";
    public static void saveHim(String username,String password) throws IOException {
        File file = new File(rememberMeFilePath);
        if (!file.exists()) {
            file.createNewFile();
        }
        DataOutputStream out = new DataOutputStream(new FileOutputStream(file));
        out.writeUTF(username + " " + password);
    }
    public static void doNotSaveHim() {
        File file = new File(rememberMeFilePath);
        if (file.exists()) {
            file.delete();
        }
    }
    public static String rememberUsername() throws IOException {
        String[] usernameAndPassword = processRememberHim(rememberHim());
        if (usernameAndPassword == null) {
            return null;
        }
        return usernameAndPassword[0];
    }
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
