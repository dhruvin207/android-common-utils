# 📱 ApplicationStateManager Documentation

## 🛠️ Overview

The `ApplicationStateManager` is a utility class designed to determine and observe the current state of an Android application. It can detect whether the application is in the foreground, background, or terminated state. This functionality is not provided by Android by default, making this class a useful addition to any Android project.

### ✨ Features:
- **Get Current State**: Determine if the app is in the foreground, background, or terminated.
- **Observe State Changes**: Monitor the app's state changes and execute custom logic when the state changes.

## 🚀 How It Works

- The `getCurrentState` method determines the current state of the application by checking the running processes and tasks associated with the app.
- The `observeAppStateChanges` method allows you to listen to state changes in the app's lifecycle and execute your own logic in response to these changes.

### 🔄 Enum `AppState`
The class defines an `AppState` enum with the following possible values:
- `🌟 FOREGROUND`: The application is currently in the foreground.
- `🌙 BACKGROUND`: The application is running but in the background.
- `❌ TERMINATED`: The application is not running.

### 🖥️ Interface `AppStateChangeListener`
The interface `AppStateChangeListener` should be implemented to receive notifications about state changes.

## 📝 Usage

### 1️⃣ Step 1: Add Required Dependencies

Make sure to include the `androidx.lifecycle:lifecycle-process` dependency in your `build.gradle` file:

```gradle
dependencies {
    implementation "androidx.lifecycle:lifecycle-process:2.6.1" // Replace with the latest version
}
```

### 2️⃣ Step 2: Using `ApplicationStateManager` in Your Application

1. **Get Current Application State**

   To determine the current state of the application, use the `getCurrentState` method:

   ```java
   Context context = getApplicationContext();
   ApplicationStateManager.AppState currentState = ApplicationStateManager.getCurrentState(context);
   ```

2. **Observe Application State Changes**

   To listen for changes in the application's state, implement the `AppStateChangeListener` interface and pass it to the `observeAppStateChanges` method:

   ```java
   public class MyApplication extends Application implements ApplicationStateManager.AppStateChangeListener {
   
       @Override
       public void onCreate() {
           super.onCreate();
           ApplicationStateManager.observeAppStateChanges(this, this);
       }
   
       @Override
       public void onAppStateChange(ApplicationStateManager.AppState state) {
           // Handle the app state change here
           Log.d("AppStateChange", "New State: " + state.getValue());
       }
   }
   ```

3. **Handling State Changes**

   When the application's state changes, the `onAppStateChange` method will be triggered, allowing you to handle each state accordingly.

### 📋 Example Usage

Here is an example of how to use the `ApplicationStateManager` in an Android Activity:

```java
public class MainActivity extends AppCompatActivity implements ApplicationStateManager.AppStateChangeListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Observe application state changes
        ApplicationStateManager.observeAppStateChanges(this, this);
    }

    @Override
    public void onAppStateChange(ApplicationStateManager.AppState state) {
        // Log the current application state
        Log.d("AppState", "Current State: " + state.getValue());
    }
}
```

### 🛠️ Kotlin Implementation

To initialize the `ApplicationStateManager` instance using an extension function `appStateManager()` for a given `Context`, use the following code:

```kotlin
fun Context.appStateManager(): ApplicationStateManager {
    return ApplicationStateManager(this)
}
```

You can then use this extension function to get an `ApplicationStateManager` instance for a given `Context`:

```kotlin
val context = applicationContext
val applicationStateManager = context.appStateManager()
```

### ⚙️ Required Configuration

- **Dependency**: Ensure that the `androidx.lifecycle:lifecycle-process` library is included in your project as it provides the necessary lifecycle components.
- **Permissions**: No special permissions are required for this class to function.

## 🏁 Conclusion

The `ApplicationStateManager` class is a useful tool for Android developers to manage and respond to application state changes. By integrating this class into your project, you can gain greater control over how your app behaves when moving between different states such as foreground, background, and termination.

---