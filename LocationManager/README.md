## 📍 Location Manager Utility Classes for Android

## Overview

This repository contains two utility classes designed to simplify location management in Android applications:

1. **LocationUtils (Kotlin)**
2. **LocationUtils (Java)**

These classes provide an easy way to manage location-related tasks such as checking GPS availability, requesting location updates, and obtaining the current location. Whether you're working with Kotlin or Java, these utility classes are structured to fit seamlessly into your Android project.

## ✨ Features

- **GPS Availability Check:** 🛰️ Quickly determine if GPS is enabled on the user's device.
- **Location Updates:** 📡 Easily request continuous location updates with customizable intervals.
- **Current Location Fetching:** 🌍 Obtain the user's current location with high accuracy.
- **Permission Handling:** 🔐 Automatically handle location permissions to ensure smooth operation.
- **Listener Support:** 👂 Register listeners to handle location updates dynamically.

## 🔧 Installation and Setup

### 1. **Add the LocationUtils Class to Your Project**

- For **Kotlin**, add the `LocationUtils` class from the provided Kotlin code to your project.
- For **Java**, add the `LocationUtils` class from the provided Java code to your project.

### 2. **Request Location Permissions**

To use these utilities, ensure your `AndroidManifest.xml` includes the necessary permissions:

```xml
<uses-permission android:name="android.permission.ACCESS_FINE_LOCATION" />
<uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION" />
<uses-permission android:name="android.permission.ACCESS_BACKGROUND_LOCATION" />
```

You must also request these permissions at runtime, especially for Android 6.0 (Marshmallow) and above.

### 3. **Usage Example**

#### Kotlin

```kotlin
val locationUtils = context.getLocationUtils()

if (locationUtils.isGpsEnabled()) {
    locationUtils.getCurrentLocation(object : LocationUtils.LocationListener {
        override fun onLocationChanged(location: Location?) {
            // Handle the current location
        }
    })

    locationUtils.requestLocationUpdates(10f, 60000) // Every 10 meters or 60 seconds
}
```

#### Java

```java
LocationUtils locationUtils = new LocationUtils(context);

if (locationUtils.isGpsEnabled()) {
    locationUtils.getCurrentLocation(new LocationUtils.LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            // Handle the current location
        }
    });

    locationUtils.requestLocationUpdates(10f, 60000); // Every 10 meters or 60 seconds
}
```

## 📚 Class Breakdown

### **LocationUtils (Kotlin)**

- **Constructor:** The class is initialized using the `newInstance` method, which requires a `Context` object.
- **Coroutine Support:** This class implements `CoroutineScope`, allowing for asynchronous tasks without blocking the main thread.
- **GPS Check:** `isGpsEnabled()` method checks if the GPS provider is enabled on the device.
- **Current Location Fetching:** `getCurrentLocation()` method fetches the current location using Google's Fused Location Provider API.
- **Location Updates:** `requestLocationUpdates()` method requests continuous location updates based on specified time and distance intervals.
- **Stopping Updates:** `stopLocationUpdates()` method stops the location updates to save battery and resources.
- **Listener Interface:** The `LocationListener` interface is provided to notify when the location changes.

### **LocationUtils (Java)**

- **Constructor:** The class is initialized with a `Context` object.
- **GPS Check:** `isGpsEnabled()` method determines if GPS is enabled.
- **Current Location Fetching:** `getCurrentLocation()` method retrieves the current location using the Fused Location Provider API.
- **Location Updates:** `requestLocationUpdates()` method requests location updates with customizable intervals for time and distance.
- **Stopping Updates:** `stopLocationUpdates()` method stops location updates to conserve resources.
- **Listener Interface:** The `LocationListener` interface is used to handle location changes dynamically.

## 🛠️ Common Use Cases

### 1. **Tracking User Location in Real-Time**

Whether you're building a navigation app, a fitness tracker, or any app that requires real-time location updates, these utilities provide a robust solution for continuous tracking.

### 2. **Fetching User's Current Location**

Use these utilities to quickly fetch the user’s current location for one-time use cases like filling out address fields or finding nearby points of interest.

### 3. **Geofencing Applications**

These classes can be utilized to monitor user's location and trigger specific actions when they enter or leave predefined geographic boundaries.

## 🚀 Getting Started

1. **Integrate the Class:** Add the relevant `LocationUtils` class to your project (Kotlin or Java).
2. **Set Up Permissions:** Ensure all necessary permissions are handled in your app.
3. **Start Tracking:** Use the provided methods to start tracking location or fetching the current location.

## 📝 Notes

- **Battery Usage:** Continuous location updates can drain the device battery quickly. It's essential to balance accuracy with battery life based on your application's needs.
- **Permissions:** Always ensure you handle location permissions gracefully, providing clear explanations to the user why location access is needed.
- **Error Handling:** Implement error handling for scenarios where location services might be unavailable or permissions are not granted.

## 📱 Conclusion

By integrating these utility classes into your Android project, you can efficiently manage location-based tasks with minimal boilerplate code. The provided methods and interfaces offer flexibility, allowing you to adapt them to various use cases in your app.

---