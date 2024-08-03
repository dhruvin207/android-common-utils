import android.app.ActivityManager
import android.content.Context
import android.os.Process
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.ProcessLifecycleOwner
import kotlinx.coroutines.*

/**
 * A helper class for managing the application state.
 */
class ApplicationStateManager(private val context: Context) {

    /**
     * A coroutine scope for IO-bound operations.
     */
    private val coroutineScope = CoroutineScope(Dispatchers.IO)

    /**
     * An enum representing the possible application states.
     */
    enum class AppState(val value: String) {
        /**
         * The application is in the foreground.
         */
        FOREGROUND("Foreground"),

        /**
         * The application is in the background.
         */
        BACKGROUND("Background"),

        /**
         * The application has been terminated.
         */
        TERMINATED("Terminated");

        /**
         * Gets an AppState from a string value.
         *
         * @param value the string value
         * @return the corresponding AppState
         */
        companion object {
            fun fromValue(value: String): AppState {
                return values().firstOrNull { it.value == value }?: TERMINATED
            }
        }
    }

    /**
     * An interface for listening to application state changes.
     */
    interface AppStateChangeListener {
        /**
         * Called whenever the application state changes.
         *
         * @param state the new application state
         */
        fun onAppStateChange(state: AppState)
    }

    /**
     * Gets the current application state.
     *
     * @return a Deferred object that can be awaited to get the current application state
     */
    fun getCurrentAppState(): Deferred<AppState> {
        return coroutineScope.async {
            try {
                val activityManager = context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
                val tasks = activityManager.getAppTasks()
                if (tasks.isNullOrEmpty()) {
                    AppState.TERMINATED
                } else {
                    getAppState(context, activityManager, tasks)
                }
            } catch (e: Exception) {
                AppState.TERMINATED
            }
        }
    }

    /**
     * Gets the application state from a list of tasks.
     *
     * @param context the application context
     * @param activityManager the activity manager
     * @param tasks the list of tasks
     * @return the application state
     */
    private suspend fun getAppState(context: Context, activityManager: ActivityManager, tasks: List<ActivityManager.AppTask>): AppState {
        var state = AppState.TERMINATED
        tasks.forEach { task ->
            val taskInfo = task.taskInfo
            val componentName = taskInfo.baseActivity
            if (componentName!= null && componentName.packageName == context.packageName) {
                state = getAppStateFromProcesses(context, activityManager)
                return@forEach
            }
        }
        return state
    }

    /**
     * Gets the application state from a list of running app processes.
     *
     * @param context the application context
     * @param activityManager the activity manager
     * @return the application state
     */
    private suspend fun getAppStateFromProcesses(context: Context, activityManager: ActivityManager): AppState {
        val appProcesses = activityManager.runningAppProcesses
        if (appProcesses.isNullOrEmpty()) {
            return AppState.BACKGROUND
        }
        appProcesses.forEach { appProcess ->
            if (appProcess.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND
                    && appProcess.processName == context.packageName) {
                return AppState.FOREGROUND
            }
        }
        return AppState.BACKGROUND
    }

    /**
     * Observes application state changes.
     *
     * @param listener an AppStateChangeListener object that will be notified of state changes
     */
    fun observeAppStateChanges(listener: AppStateChangeListener) {
        ProcessLifecycleOwner.get().lifecycle.addObserver(object : LifecycleEventObserver {
            override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
                if (event == Lifecycle.Event.ON_START
                        || event == Lifecycle.Event.ON_RESUME
                        || event == Lifecycle.Event.ON_STOP
                        || event == Lifecycle.Event.ON_PAUSE
                        || event == Lifecycle.Event.ON_DESTROY) {
                    coroutineScope.launch {
                        val state = getCurrentAppState().await()
                        listener.onAppStateChange(state)
                    }
                }
            }
        })
    }
}