
import android.content.Context
import android.content.SharedPreferences
import kotlinx.coroutines.*

/**
 * A utility class for managing shared preferences in an Android application.
 */
object SharedPreferenceManager {

    /**
     * The context of the application.
     */
    private lateinit var mContext: Context

    /**
     * The shared preferences object.
     */
    private lateinit var mSettings: SharedPreferences

    /**
     * The editor for the shared preferences object.
     */
    private lateinit var mEditor: SharedPreferences.Editor

    /**
     * The default file name for the shared preferences.
     */
    private const val DEFAULT_PREF_FILE_NAME = "app_preferences"

    /**
     * The default mode for the shared preferences.
     */
    private const val DEFAULT_MODE = Context.MODE_PRIVATE

    /**
     * The job for the coroutine scope.
     */
    private val job = SupervisorJob()

    /**
     * The coroutine scope for IO operations.
     */
    private val scope = CoroutineScope(Dispatchers.IO + job)

    /**
     * Initializes the shared preference manager with the default file name and mode.
     *
     * @param context The context of the application.
     */
    fun init(context: Context) {
        mContext = context
        mSettings = mContext.getSharedPreferences(DEFAULT_PREF_FILE_NAME, DEFAULT_MODE)
        mEditor = mSettings.edit()
    }

    /**
     * Initializes the shared preference manager with a custom file name and mode.
     *
     * @param context The context of the application.
     * @param prefFileName The file name for the shared preferences.
     * @param mode The mode for the shared preferences.
     */
    fun init(context: Context, prefFileName: String, mode: Int) {
        mContext = context
        mSettings = mContext.getSharedPreferences(prefFileName, mode)
        mEditor = mSettings.edit()
    }

    /**
     * Sets a string value in the shared preferences.
     *
     * @param key The key for the value.
     * @param value The value to set.
     */
    suspend fun setValue(key: String, value: String) = withContext(Dispatchers.IO) {
        mEditor.putString(key, value)
        mEditor.commit()
    }

    /**
     * Sets an integer value in the shared preferences.
     *
     * @param key The key for the value.
     * @param value The value to set.
     */
    suspend fun setValue(key: String, value: Int) = withContext(Dispatchers.IO) {
        mEditor.putInt(key, value)
        mEditor.commit()
    }

    /**
     * Sets a boolean value in the shared preferences.
     *
     * @param key The key for the value.
     * @param value The value to set.
     */
    suspend fun setValue(key: String, value: Boolean) = withContext(Dispatchers.IO) {
        mEditor.putBoolean(key, value)
        mEditor.commit()
    }

    /**
     * Sets a float value in the shared preferences.
     *
     * @param key The key for the value.
     * @param value The value to set.
     */
    suspend fun setValue(key: String, value: Float) = withContext(Dispatchers.IO) {
        mEditor.putFloat(key, value)
        mEditor.commit()
    }

    /**
     * Sets a long value in the shared preferences.
     *
     * @param key The key for the value.
     * @param value The value to set.
     */
    suspend fun setValue(key: String, value: Long) = withContext(Dispatchers.IO) {
        mEditor.putLong(key, value)
        mEditor.commit()
    }

    /**
     * Gets a string value from the shared preferences.
     *
     * @param key The key for the value.
     * @param defaultValue The default value to return if the key is not found.
     * @return The value associated with the key, or the default value if not found.
     */
    fun getValue(key: String, defaultValue: String): String {
        return mSettings.getString(key, defaultValue) ?: defaultValue
    }

    /**
     * Gets an integer value from the shared preferences.
     *
     * @param key The key for the value.
     * @param defaultValue The default value to return if the key is not found.
     * @return The value associated with the key, or the default value if not found.
     */
    fun getValue(key: String, defaultValue: Int): Int {
        return mSettings.getInt(key, defaultValue)
    }

    /**
     * Gets a boolean value from the shared preferences.
     *
     * @param key The key for the value.
     * @param defaultValue The default value to return if the key is not found.
     * @return The value associated with the key, or the default value if not found.
     */
    fun getValue(key: String, defaultValue: Boolean): Boolean {
        return mSettings.getBoolean(key, defaultValue)
    }

    /**
     * Gets a float value from the shared preferences.
     *
     * @param key The key for the value.
     * @param defaultValue The default value to return if the key is not found.
     * @return The value associated with the key, or the default value if not found.
     */
    fun getValue(key: String, defaultValue: Float): Float {
        return mSettings.getFloat(key, defaultValue)
    }

    /**
     * Gets a long value from the shared preferences.
     *
     * @param key The key for the value.
     * @param defaultValue The default value to return if the key is not found.
     * @return The value associated with the key, or the default value if not found.
     */
    fun getValue(key: String, defaultValue: Long): Long {
        return mSettings.getLong(key, defaultValue)
    }

    /**
     * Clears all values from the shared preferences.
     */
    suspend fun clearPreferences() = withContext(Dispatchers.IO) {
        mEditor.clear()
        mEditor.commit()
    }

    /**
     * Removes a value from the shared preferences.
     *
     * @param key The key for the value to remove.
     */
    suspend fun removePreference(key: String) = withContext(Dispatchers.IO) {
        mEditor.remove(key)
        mEditor.commit()
    }
}