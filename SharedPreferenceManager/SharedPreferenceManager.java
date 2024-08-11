/**
 * A utility class for managing SharedPreferences in an Android application.
 * This class provides a simple and convenient way to store and retrieve
 * application preferences.
 *
 * @author [Dhruvin]
 * @version 3.0
 */
public class SharedPreferenceManager {

    /**
     * The instance of the SharedPreferenceManager.
     */
    private static SharedPreferenceManager instance;

    /**
     * The context of the application.
     */
    private static Context mContext;

    /**
     * The SharedPreferences object.
     */
    private SharedPreferences mSettings;

    /**
     * The Editor object for modifying the SharedPreferences.
     */
    private Editor mEditor;

    /**
     * The default file name for the SharedPreferences.
     */
    private static final String DEFAULT_PREF_FILE_NAME = "app_preferences";

    /**
     * The default mode for the SharedPreferences.
     */
    private static final int DEFAULT_MODE = Context.MODE_PRIVATE;

    /**
     * Private constructor to prevent instantiation.
     */
    private SharedPreferenceManager() {
    }

    /**
     * Initializes the SharedPreferenceManager with the default file name and mode.
     *
     * @param context The context of the application.
     */
    public static void init(Context context) {
        mSettings = mContext.getSharedPreferences(DEFAULT_PREF_FILE_NAME, DEFAULT_MODE);
        mEditor = mSettings.edit();
    }

    /**
     * Initializes the SharedPreferenceManager with a custom file name and mode.
     *
     * @param context The context of the application.
     * @param prefFileName The file name for the SharedPreferences.
     * @param mode The mode for the SharedPreferences.
     */
    public static void init(Context context, String prefFileName, int mode) {
        mSettings = mContext.getSharedPreferences(prefFileName, mode);
        mEditor = mSettings.edit();
    }

    /**
     * Returns the instance of the SharedPreferenceManager.
     *
     * @return The instance of the SharedPreferenceManager.
     */
    public static SharedPreferenceManager getInstance() {
        if (instance == null) {
            instance = new SharedPreferenceManager();
        }
        return instance;
    }

    /**
     * Sets a string value in the SharedPreferences.
     *
     * @param key The key for the value.
     * @param value The value to be stored.
     */
    public void setValue(String key, String value) {
        mEditor.putString(key, value);
        mEditor.commit();
    }

    /**
     * Sets an integer value in the SharedPreferences.
     *
     * @param key The key for the value.
     * @param value The value to be stored.
     */
    public void setValue(String key, int value) {
        mEditor.putInt(key, value);
        mEditor.commit();
    }

    /**
     * Sets a boolean value in the SharedPreferences.
     *
     * @param key The key for the value.
     * @param value The value to be stored.
     */
    public void setValue(String key, boolean value) {
        mEditor.putBoolean(key, value);
        mEditor.commit();
    }

    /**
     * Sets a float value in the SharedPreferences.
     *
     * @param key The key for the value.
     * @param value The value to be stored.
     */
    public void setValue(String key, float value) {
        mEditor.putFloat(key, value);
        mEditor.commit();
    }

    /**
     * Sets a long value in the SharedPreferences.
     *
     * @param key The key for the value.
     * @param value The value to be stored.
     */
    public void setValue(String key, long value) {
        mEditor.putLong(key, value);
        mEditor.commit();
    }

    /**
     * Returns a string value from the SharedPreferences.
     *
     * @param key The key for the value.
     * @param defaultValue The default value to return if the key is not found.
     * @return The value associated with the key, or the default value if not found.
     */
    public String getValue(String key, String defaultValue) {
        return mSettings.getString(key, defaultValue);
    }

    /**
     * Returns an integer value from the SharedPreferences.
     *
     * @param key The key for the value.
     * @param defaultValue The default value to return if the key is not found.
     * @return The value associated with the key, or the default value if not found.
     */
    public int getValue(String key, int defaultValue) {
        return mSettings.getInt(key, defaultValue);
    }

    /**
     * Returns a boolean value from the SharedPreferences.
     *
     * @param key The key for the value.
     * @param defaultValue The default value to return if the key is not found.
     * @return The value associated with the key, or the default value if not found.
     */
    public boolean getValue(String key, boolean defaultValue) {
        return mSettings.getBoolean(key, defaultValue);
    }

    /**
     * Returns a float value from the SharedPreferences.
     *
     * @param key The key for the value.
     * @param defaultValue The default value to return if the key is not found.
     * @return The value associated with the key, or the default value if not found.
     */
    public float getValue(String key, float defaultValue) {
        return mSettings.getFloat(key, defaultValue);
    }

    /**
     * Returns a long value from the SharedPreferences.
     *
     * @param key The key for the value.
     * @param defaultValue The default value to return if the key is not found.
     * @return The value associated with the key, or the default value if not found.
     */
    public long getValue(String key, long defaultValue) {
        return mSettings.getLong(key, defaultValue);
    }

    /**
     * Clears all values from the SharedPreferences.
     */
    public void clearPreferences() {
        mEditor.clear();
        mEditor.commit();
    }

    /**
     * Removes a value from the SharedPreferences.
     *
     * @param key The key for the value to be removed.
     */
    public void removePreference(String key) {
        mEditor.remove(key);
        mEditor.commit();
    }
}
