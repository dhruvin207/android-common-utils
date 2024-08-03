# 🗂️ SharedPreferenceManager

`SharedPreferenceManager` is a utility class for managing shared preferences in an Android application. It provides a simple and convenient way to store and retrieve application preferences. It supports both Java and Kotlin implementations, allowing for flexible integration into your Android projects.

## Overview

- **Context**: The application context used to initialize the `SharedPreferences`.
- **SharedPreferences**: The object used for managing key-value pairs.
- **SharedPreferences.Editor**: The editor used to modify the shared preferences.
- **Coroutine Support (Kotlin)**: Operations that modify preferences (set, clear, remove) are performed asynchronously using Kotlin Coroutines in the Kotlin version.

## Java Implementation

### Initialization

Before using any of the methods, you must initialize the `SharedPreferenceManager` with the application context.

```java
SharedPreferenceManager.init(context);
```

Or with a custom file name and mode:

```java
SharedPreferenceManager.init(context, "custom_preferences", Context.MODE_PRIVATE);
```

### Methods

- **Set String Value**

    ```java
    public void setValue(String key, String value);
    ```

- **Set Integer Value**

    ```java
    public void setValue(String key, int value);
    ```

- **Set Boolean Value**

    ```java
    public void setValue(String key, boolean value);
    ```

- **Set Float Value**

    ```java
    public void setValue(String key, float value);
    ```

- **Set Long Value**

    ```java
    public void setValue(String key, long value);
    ```

- **Get String Value**

    ```java
    public String getValue(String key, String defaultValue);
    ```

- **Get Integer Value**

    ```java
    public int getValue(String key, int defaultValue);
    ```

- **Get Boolean Value**

    ```java
    public boolean getValue(String key, boolean defaultValue);
    ```

- **Get Float Value**

    ```java
    public float getValue(String key, float defaultValue);
    ```

- **Get Long Value**

    ```java
    public long getValue(String key, long defaultValue);
    ```

- **Clear All Preferences**

    ```java
    public void clearPreferences();
    ```

- **Remove Specific Preference**

    ```java
    public void removePreference(String key);
    ```

## Kotlin Implementation

### Initialization

Before using any of the methods, you must initialize the `SharedPreferenceManager` with the application context.

```kotlin
SharedPreferenceManager.init(context)
```

Or with a custom file name and mode:

```kotlin
SharedPreferenceManager.init(context, "custom_preferences", Context.MODE_PRIVATE)
```

### Methods

- **Set String Value** 📝

    ```kotlin
    suspend fun setValue(key: String, value: String)
    ```

- **Set Integer Value** 🔢

    ```kotlin
    suspend fun setValue(key: String, value: Int)
    ```

- **Set Boolean Value** ✔️ / ❌

    ```kotlin
    suspend fun setValue(key: String, value: Boolean)
    ```

- **Set Float Value** 📏

    ```kotlin
    suspend fun setValue(key: String, value: Float)
    ```

- **Set Long Value** 🕒

    ```kotlin
    suspend fun setValue(key: String, value: Long)
    ```

- **Get String Value** 📝

    ```kotlin
    fun getValue(key: String, defaultValue: String): String
    ```

- **Get Integer Value** 🔢

    ```kotlin
    fun getValue(key: String, defaultValue: Int): Int
    ```

- **Get Boolean Value** ✔️ / ❌

    ```kotlin
    fun getValue(key: String, defaultValue: Boolean): Boolean
    ```

- **Get Float Value** 📏

    ```kotlin
    fun getValue(key: String, defaultValue: Float): Float
    ```

- **Get Long Value** 🕒

    ```kotlin
    fun getValue(key: String, defaultValue: Long): Long
    ```

- **Clear All Preferences** 🚮

    ```kotlin
    suspend fun clearPreferences()
    ```

- **Remove Specific Preference** ❌

    ```kotlin
    suspend fun removePreference(key: String)
    ```

## Usage Example

### Java Example

```java
// Initialize the manager
SharedPreferenceManager.init(context);

// Setting values
SharedPreferenceManager.getInstance().setValue("key_string", "value_string");
SharedPreferenceManager.getInstance().setValue("key_int", 123);

// Getting values
String stringValue = SharedPreferenceManager.getInstance().getValue("key_string", "default_string");
int intValue = SharedPreferenceManager.getInstance().getValue("key_int", 0);

// Clearing preferences
SharedPreferenceManager.getInstance().clearPreferences();

// Removing a single preference
SharedPreferenceManager.getInstance().removePreference("key_string");
```

### Kotlin Example

```kotlin
// Initialize the manager
SharedPreferenceManager.init(context)

// Setting values
CoroutineScope(Dispatchers.Main).launch {
    SharedPreferenceManager.setValue("key_string", "value_string")
    SharedPreferenceManager.setValue("key_int", 123)
}

// Getting values
val stringValue = SharedPreferenceManager.getValue("key_string", "default_string")
val intValue = SharedPreferenceManager.getValue("key_int", 0)

// Clearing preferences
CoroutineScope(Dispatchers.Main).launch {
    SharedPreferenceManager.clearPreferences()
}

// Removing a single preference
CoroutineScope(Dispatchers.Main).launch {
    SharedPreferenceManager.removePreference("key_string")
}
```

## Notes

- **Coroutines (Kotlin)**: Make sure to handle coroutines properly when calling suspend functions. Use `CoroutineScope` and `launch` to perform asynchronous operations.
- **Threading (Java)**: Preference operations are performed on the main thread; consider using background threads if needed to avoid blocking the UI.
---
