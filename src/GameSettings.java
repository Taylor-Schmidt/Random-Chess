import java.util.HashMap;

public class GameSettings {
    private static final GameSettings instance = new GameSettings();
    private final HashMap<String, Object> settings;

    public static final String FULLSCREEN = "full_screen";

    private GameSettings(){
        settings = new HashMap<>();
    }

    public static GameSettings getInstance(){
        return instance;
    }

    public void initDefaults(){
        if (!settings.containsKey(FULLSCREEN)){
            settings.put(FULLSCREEN, false);
        }
    }

    public Object get(String key){
        return settings.getOrDefault(key, null);
    }

    public void put(String key, Object value){
        settings.put(key, value);
    }
}
