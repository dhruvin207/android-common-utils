
import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.util.Log;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleEventObserver;
import androidx.lifecycle.ProcessLifecycleOwner;

import java.util.List;
import java.util.Objects;

**
 * A utility class to determine the current state of an Android application.
 * The state can be one of the following: FOREGROUND, BACKGROUND, or TERMINATED.
 */
public class ApplicationStateManager {
    
    private ApplicationStateManager(){}

    /**
     * Returns the current state of the application.
     *
     * @param context The context of the application.
     * @return The current state of the application.
     */
    public static AppState getCurrentState(Context context) {
        try {
            // Get the ActivityManager system service from the provided context
            ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);

            // Check if the ActivityManager service is available
            if (activityManager == null) {
                // If ActivityManager is not available, return TERMINATED state
                return AppState.TERMINATED;
            }

            // Retrieve the list of AppTasks from the ActivityManager
            List<ActivityManager.AppTask> tasks = activityManager.getAppTasks();

            // Check if the list of tasks is null or empty
            if (tasks == null || tasks.isEmpty()) {
                // If there are no tasks, return TERMINATED state
                return AppState.TERMINATED;
            }

            // Delegate to getAppState to determine the current state of the app
            return getAppState(context, activityManager, tasks);

        } catch (Exception e) {
            // If an exception occurs during the process, log the error message
            Log.e("ApplicationStateManager", e.getMessage() != null ? e.getMessage() : "Unknown error");

            // Return TERMINATED state in case of an exception
            return AppState.TERMINATED;
        }
    }

    /**
     * Determines the state of the app based on the list of tasks.
     *
     * @param context        The context of the application.
     * @param activityManager The ActivityManager system service.
     * @param tasks          The list of AppTasks.
     * @return The determined state of the app.
     */
    private static AppState getAppState(Context context, ActivityManager activityManager, List<ActivityManager.AppTask> tasks) {
        // Initialize the default state as TERMINATED
        AppState state = AppState.TERMINATED;

        // Iterate through each task in the list of tasks
        for (ActivityManager.AppTask task : tasks) {
            // Get the RecentTaskInfo associated with the task
            ActivityManager.RecentTaskInfo taskInfo = task.getTaskInfo();

            // Retrieve the base activity ComponentName from the RecentTaskInfo
            ComponentName componentName = taskInfo.baseActivity;

            // Check if the base activity ComponentName is not null and belongs to the current app
            if (componentName != null && componentName.getPackageName().equals(context.getPackageName())) {
                // If the current app's task is found, determine the state by checking running processes
                state = getAppStateFromProcesses(context, activityManager);
                // Break out of the loop since we've determined the app's state
                break;
            }
        }

        // Return the determined state (either FOREGROUND or BACKGROUND)
        return state;
    }

    /**
     * Determines the app's state based on running processes.
     *
     * @param context        The context of the application.
     * @param activityManager The ActivityManager system service.
     * @return The determined state of the app.
     */
    private static AppState getAppStateFromProcesses(Context context, ActivityManager activityManager) {
        // Retrieve the list of RunningAppProcessInfo from the ActivityManager
        List<ActivityManager.RunningAppProcessInfo> appProcesses = activityManager.getRunningAppProcesses();

        // Check if the list of running processes is not null and not empty
        if (appProcesses != null && !appProcesses.isEmpty()) {
            // Iterate through each RunningAppProcessInfo
            for (ActivityManager.RunningAppProcessInfo appProcess : appProcesses) {
                // Check if the current app's process is in the foreground
                // and matches the package name of the current app
                if (appProcess.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND
                        && appProcess.processName.equals(context.getPackageName())) {
                    // If the app's process is in the foreground, return FOREGROUND state
                    return AppState.FOREGROUND;
                }
            }
        }

        // If no foreground process is found for the app, return BACKGROUND state
        return AppState.BACKGROUND;
    }

    /**
     * Observes the application's state changes and notifies the provided listener.
     *
     * @param context  The context of the application.
     * @param listener The listener to notify when the application's state changes.
     */
    public static void observeAppStateChanges(Context context, AppStateChangeListener listener) {
        ProcessLifecycleOwner.get().getLifecycle().addObserver((LifecycleEventObserver) (lifecycleOwner, event) -> {
            if ((event == Lifecycle.Event.ON_START
                    || event == Lifecycle.Event.ON_RESUME
                    || event == Lifecycle.Event.ON_STOP
                    || event == Lifecycle.Event.ON_PAUSE
                    || event == Lifecycle.Event.ON_DESTROY) && (listener != null)) {
                AppState state = getCurrentState(context);
                listener.onAppStateChange(state);
            }
        });
    }

    /**
     * An enum representing the possible states of an application.
     */
    public enum AppState {
        /**
         * The application is in the foreground.
         */
        FOREGROUND("Foreground"),
        /**
         * The application is in the background.
         */
        BACKGROUND("Background"),
        /**
         * The application is terminated.
         */
        TERMINATED("Terminated");

        private final String value;

        AppState(String value) {
            this.value = value;
        }

        /**
         * Returns the string representation of the application state.
         *
         * @return The string representation of the application state.
         */
        public String getValue() {
            return value;
        }

        /**
         * Returns the AppState enum value from the provided string representation.
         *
         * @param value The string representation of the application state.
         * @return The AppState enum value.
         */
        public static AppState fromValue(String value) {
            for (AppState enumValue : AppState.values()) {
                if (Objects.equals(enumValue.getValue(), value)) {
                    return enumValue;
                }
            }
            return TERMINATED;
        }
    }

    /**
     * An interface for listening to application state changes.
     */
    public interface AppStateChangeListener {
        /**
         * Called when the application's state changes.
         *
         * @param state The new state of the application.
         */
        void onAppStateChange(AppState state);
    }
}