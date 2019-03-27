import java.io.*;
import java.util.HashMap;

@SuppressWarnings("SameParameterValue")
class GameSettings {
    private static final GameSettings instance = new GameSettings();
    private HashMap<String, Object> settings;
    private final File file = new File("settings.dat");

    static final String FULLSCREEN = "full_screen";

    private GameSettings() {
        try {
            boolean isNewFile = file.createNewFile();
            if (isNewFile){
                settings = new HashMap<>();
                writeSettings();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        readSettings();
    }

    static GameSettings getInstance() {
        return instance;
    }

    void initDefaults() {
        if (!settings.containsKey(FULLSCREEN)) {
            settings.put(FULLSCREEN, false);
        }
    }

    Object get(String key) {
        return settings.getOrDefault(key, null);
    }

    void put(String key, Object value) {
        settings.put(key, value);
        writeSettings();
    }

    @SuppressWarnings("unchecked")
    private void readSettings() {
        try {
            FileInputStream fis = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(fis);
            settings = (HashMap<String, Object>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void writeSettings() {
        try {
            FileOutputStream fis = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(fis);
            oos.writeObject(settings);
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
