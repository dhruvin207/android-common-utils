import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.os.Looper
import android.util.Log
import androidx.core.content.ContextCompat
import com.google.android.gms.location.CurrentLocationRequest
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationAvailability
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.google.android.gms.tasks.Task
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

/**
 * Utility class for managing location-related tasks.
 * Provides methods to check location availability, request location updates,
 * and fetch the current location.
 */
class LocationUtils private constructor(private val context: Context) : CoroutineScope {

    private val locationManager: LocationManager =
        context.getSystemService(Context.LOCATION_SERVICE) as LocationManager

    private val fusedLocationProviderClient: FusedLocationProviderClient =
        LocationServices.getFusedLocationProviderClient(context)

    private var listener: LocationListener? = null

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + Job()

    /**
     * Companion object to create an instance of LocationUtils.
     */
    companion object {
        private const val TAG = "LocationUtils"

        fun newInstance(context: Context): LocationUtils {
            return LocationUtils(context)
        }
    }

    /**
     * Checks if the GPS provider is enabled.
     *
     * @return True if GPS provider is enabled, false otherwise.
     */
    fun isGpsEnabled(): Boolean {
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
    }

    /**
     * Fetches the current location with high accuracy.
     *
     * @param listener The listener to notify with the location result.
     */
    fun getCurrentLocation(listener: LocationListener) {
        if (!hasLocationPermission()) {
            listener.onLocationChanged(null)
            return
        }

        val request = CurrentLocationRequest.Builder()
            .setPriority(Priority.PRIORITY_HIGH_ACCURACY)
            .build()

        fusedLocationProviderClient.getCurrentLocation(request, null)
            .addOnCompleteListener { locationResult ->
                handleLocationResult(locationResult, listener)
            }
    }

    /**
     * Handles the result of the location fetch request.
     *
     * @param locationResult The Task containing the location result.
     * @param listener       The listener to notify with the location result.
     */
    private fun handleLocationResult(locationResult: Task<Location>, listener: LocationListener) {
        if (locationResult.isSuccessful) {
            val location = locationResult.result
            listener.onLocationChanged(location)
        } else {
            listener.onLocationChanged(null)
        }
    }

    /**
     * Registers a location listener to handle location updates.
     *
     * @param listener The listener to register.
     */
    fun registerLocationListener(listener: LocationListener) {
        this.listener = listener
    }

    /**
     * Requests location updates with the specified time and distance intervals.
     *
     * @param minDistance The minimum distance between location updates in meters.
     * @param minTime     The minimum time between location updates in milliseconds.
     */
    fun requestLocationUpdates(minDistance: Float, minTime: Long) {
        if (!hasLocationPermission()) {
            return
        }

        locationManager.requestLocationUpdates(
            LocationManager.GPS_PROVIDER,
            minTime,
            minDistance,
            locationListener,
            Looper.getMainLooper()
        )
    }

    /**
     * Removes location updates to stop receiving location changes.
     */
    fun stopLocationUpdates() {
        try {
            locationManager.removeUpdates(locationListener)
        } catch (e: IllegalArgumentException) {
            Log.e(TAG, e.message)
        }
    }

    /**
     * LocationListener implementation to handle location changes.
     */
    private val locationListener = object : LocationListener {
        override fun onLocationChanged(location: Location) {
            listener?.onLocationChanged(location)
        }

        override fun onStatusChanged(provider: String, status: Int, extras: Bundle) {}

        override fun onProviderEnabled(provider: String) {}

        override fun onProviderDisabled(provider: String) {}
    }

    /**
     * Interface for listening to location changes.
     */
    interface LocationListener {
        fun onLocationChanged(location: Location?)
    }

    /**
     * Checks if the location permission is granted.
     *
     * @return True if the location permission is granted, false otherwise.
     */
    private fun hasLocationPermission(): Boolean {
        return ContextCompat.checkSelfPermission(
            context,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
    }
}

fun Context.getLocationUtils(): LocationUtils {
    return LocationUtils.newInstance(this)
}