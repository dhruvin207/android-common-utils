
import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

import com.google.android.gms.location.CurrentLocationRequest;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationAvailability;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.Priority;
import com.google.android.gms.tasks.Task;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.Locale;
import java.util.Objects;
/**
 * Utility class for managing location-related tasks.
 * Provides methods to check location availability, request location updates,
 * and fetch the current location.
 */
public class LocationUtils {

    private static final String TAG = LocationUtils.class.getSimpleName();

    private final LocationManager locationManager;
    private final FusedLocationProviderClient fusedLocationProviderClient;
    private LocationListener listener;
    private Context context;

    /**
     * Constructor to initialize LocationManagerUtil.
     *
     * @param context The context from which the system services are accessed.
     */
    public LocationUtils(Context context) {
        this.context=context;
        locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(context);
    }

    /**
     * Checks if the GPS provider is enabled.
     *
     * @return True if GPS provider is enabled, false otherwise.
     */
    public boolean isGpsEnabled() {
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
    }

    /**
     * Fetches the current location with high accuracy.
     *
     * @param listener The listener to notify with the location result.
     */
    public void getCurrentLocation(LocationListener listener) {
        if (!hasLocationPermission()) {
            listener.onLocationChanged(null);
            return;
        }

        CurrentLocationRequest request = new CurrentLocationRequest.Builder()
                .setPriority(Priority.PRIORITY_HIGH_ACCURACY)
                .build();

        fusedLocationProviderClient.getCurrentLocation(request, null)
                .addOnCompleteListener(locationResult -> handleLocationResult(locationResult, listener));
    }

    /**
     * Handles the result of the location fetch request.
     *
     * @param locationResult The Task containing the location result.
     * @param listener       The listener to notify with the location result.
     */
    private void handleLocationResult(Task<Location> locationResult, LocationListener listener) {
        if (locationResult.isSuccessful()) {
            Location location = locationResult.getResult();
            listener.onLocationChanged(location);
        } else {
            listener.onLocationChanged(null);
        }
    }

    /**
     * Registers a location listener to handle location updates.
     *
     * @param listener The listener to register.
     */
    public void registerLocationListener(LocationListener listener) {
        this.listener = listener;
    }

    /**
     * Requests location updates with the specified time and distance intervals.
     *
     * @param minDistance The minimum distance between location updates in meters.
     * @param minTime     The minimum time between location updates in milliseconds.
     */
    public void requestLocationUpdates(float minDistance, long minTime) {
        if (!hasLocationPermission()) {
            return;
        }

        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, minTime, minDistance, locationListener, Looper.getMainLooper());
    }

    /**
     * Removes location updates to stop receiving location changes.
     */
    public void stopLocationUpdates() {
        try {
            locationManager.removeUpdates(locationListener);
        } catch (IllegalArgumentException e) {
            Log.e(TAG, e.getMessage());
        }
    }

    /**
     * LocationListener implementation to handle location changes.
     */
    private final LocationListener locationListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            if (listener != null) {
                listener.onLocationChanged(location);
            }
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {}

        @Override
        public void onProviderEnabled(@NonNull String provider) {}

        @Override
        public void onProviderDisabled(@NonNull String provider) {}
    };

    /**
     * Interface for listening to location changes.
     */
    public interface LocationListener {
        void onLocationChanged(Location location);
    }

    /**
     * Checks if the location permission is granted.
     *
     * @return True if the location permission is granted, false otherwise.
     */
    private boolean hasLocationPermission() {
        return ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED;
    }
}