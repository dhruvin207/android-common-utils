/**
 * Provides a set of utility functions and properties for working with Android Context.
 */
@file:Suppress("NOTHING_TO_INLINE")

import android.accounts.AccountManager
import android.app.*
import android.app.admin.DevicePolicyManager
import android.app.job.JobScheduler
import android.appwidget.AppWidgetManager
import android.bluetooth.BluetoothManager
import android.content.ClipboardManager
import android.content.Context
import android.content.RestrictionsManager
import android.content.pm.LauncherApps
import android.content.pm.PackageManager
import android.hardware.ConsumerIrManager
import android.hardware.SensorManager
import android.hardware.camera2.CameraManager
import android.hardware.display.DisplayManager
import android.hardware.input.InputManager
import android.hardware.usb.UsbManager
import android.location.LocationManager
import android.media.AudioManager
import android.media.MediaRouter
import android.media.projection.MediaProjectionManager
import android.media.session.MediaSessionManager
import android.media.tv.TvInputManager
import android.net.ConnectivityManager
import android.net.nsd.NsdManager
import android.net.wifi.WifiManager
import android.net.wifi.p2p.WifiP2pManager
import android.nfc.NfcManager
import android.os.*
import android.os.storage.StorageManager
import android.preference.PreferenceManager
import android.print.PrintManager
import android.support.annotation.LayoutRes
import android.support.annotation.RequiresApi
import android.support.annotation.StringRes
import android.support.v4.content.ContextCompat
import android.telecom.TelecomManager
import android.telephony.TelephonyManager
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.WindowManager
import android.view.accessibility.AccessibilityManager
import android.view.accessibility.CaptioningManager
import android.view.inputmethod.InputMethodManager
import android.view.textservice.TextServicesManager
import android.widget.Toast

/**
 * Returns the display width in pixels.
 */
inline val Context.displayWidth
    get() = resources.displayMetrics.widthPixels

/**
 * Returns the display height in pixels.
 */
inline val Context.displayHeight
    get() = resources.displayMetrics.heightPixels

/**
 * Inflates a layout resource into a ViewGroup.
 *
 * @param layoutResId the layout resource ID
 * @param parent the parent ViewGroup
 * @param attachToRoot whether to attach the inflated layout to the parent
 * @return the inflated layout
 */
inline fun Context.inflateLayout(@LayoutRes layoutResId: Int, parent: ViewGroup? = null, attachToRoot: Boolean = false) =
        LayoutInflater.from(this).inflate(layoutResId, parent, attachToRoot)

/**
 * Returns the AccessibilityManager instance.
 */
inline val Context.accessibilityManager
    get() = getSystemService(Context.ACCESSIBILITY_SERVICE) as AccessibilityManager?

/**
 * Returns the AccountManager instance.
 */
inline val Context.accountManager
    get() = getSystemService(Context.ACCOUNT_SERVICE) as AccountManager?

/**
 * Returns the ActivityManager instance.
 */
inline val Context.activityManager
    get() = getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager?

/**
 * Returns the AlarmManager instance.
 */
inline val Context.alarmManager
    get() = getSystemService(Context.ALARM_SERVICE) as AlarmManager?

/**
 * Returns the AppWidgetManager instance.
 *
 * @requiresApi API level 21 (Lollipop) or higher
 */
inline val Context.appWidgetManager
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    get() = getSystemService(Context.APPWIDGET_SERVICE) as AppWidgetManager?

/**
 * Returns the AppOpsManager instance.
 *
 * @requiresApi API level 19 (KitKat) or higher
 */
inline val Context.appOpsManager
    @RequiresApi(Build.VERSION_CODES.KITKAT)
    get() = getSystemService(Context.APP_OPS_SERVICE) as AppOpsManager?

/**
 * Returns the AudioManager instance.
 */
inline val Context.audioManager
    get() = getSystemService(Context.AUDIO_SERVICE) as AudioManager?

/**
 * Returns the BatteryManager instance.
 *
 * @requiresApi API level 21 (Lollipop) or higher
 */
inline val Context.batteryManager
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    get() = getSystemService(Context.BATTERY_SERVICE) as BatteryManager?

/**
 * Returns the BluetoothManager instance.
 *
 * @requiresApi API level 18 (Jelly Bean MR2) or higher
 */
inline val Context.bluetoothManager
    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
    get() = getSystemService(Context.BLUETOOTH_SERVICE) as BluetoothManager?

/**
 * Returns the CameraManager instance.
 *
 * @requiresApi API level 21 (Lollipop) or higher
 */
inline val Context.cameraManager
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    get() = getSystemService(Context.CAMERA_SERVICE) as CameraManager?

/**
 * Returns the CaptioningManager instance.
 *
 * @requiresApi API level 19 (KitKat) or higher
 */
inline val Context.captioningManager
    @RequiresApi(Build.VERSION_CODES.KITKAT)
    get() = getSystemService(Context.CAPTIONING_SERVICE) as CaptioningManager?

/**
 * Returns the ClipboardManager instance.
 */
inline val Context.clipboardManager
    get() = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager?

/**
 * Returns the ConnectivityManager instance.
 */
inline val Context.connectivityManager
    get() = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?

/**
 * Returns the ConsumerIrManager instance.
 *
 * @requiresApi API level 19 (KitKat) or higher
 */
inline val Context.consumerIrManager
    @RequiresApi(Build.VERSION_CODES.KITKAT)
    get() = getSystemService(Context.CONSUMER_IR_SERVICE) as ConsumerIrManager?

/**
 * Returns the DevicePolicyManager instance.
 */
inline val Context.devicePolicyManager
    get() = getSystemService(Context.DEVICE_POLICY_SERVICE) as DevicePolicyManager?

/**
 * Returns the DisplayManager instance.
 *
 * @requiresApi API level 17 (Jelly Bean MR1) or higher
 */
inline val Context.displayManager
    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    get() = getSystemService(Context.DISPLAY_SERVICE) as DisplayManager?

/**
 * Returns the DownloadManager instance.
 */
inline val Context.downloadManager
    get() = getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager?

/**
 * Returns the DropBoxManager instance.
 */
inline val Context.dropBoxManager
    get() = getSystemService(Context.DROPBOX_SERVICE) as DropBoxManager?

/**
 * Returns the InputMethodManager instance.
 */
inline val Context.inputMethodManager
    get() = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?

/**
 * Returns the InputManager instance.
 */
inline val Context.inputManager
    get() = getSystemService(Context.INPUT_SERVICE) as InputManager?

/**
 * Returns the JobScheduler instance.
 *
 * @requiresApi API level 21 (Lollipop) or higher
 */
inline val Context.jobScheduler
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    get() = getSystemService(Context.JOB_SCHEDULER_SERVICE) as JobScheduler?

/**
 * Returns the KeyguardManager instance.
 */
inline val Context.keyguardManager
    get() = getSystemService(Context.KEYGUARD_SERVICE) as KeyguardManager?

/**
 * Returns the LauncherApps instance.
 *
 * @requiresApi API level 21 (Lollipop) or higher
 */
inline val Context.launcherApps
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    get() = getSystemService(Context.LAUNCHER_APPS_SERVICE) as LauncherApps?

/**
 * Returns the LayoutInflater instance.
 */
inline val Context.layoutInflater
    get() = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater?

/**
 * Returns the LocationManager instance.
 */
inline val Context.locationManager
    get() = getSystemService(Context.LOCATION_SERVICE) as LocationManager?

/**
 * Returns the MediaProjectionManager instance.
 *
 * @requiresApi API level 21 (Lollipop) or higher
 */
inline val Context.mediaProjectionManager
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    get() = getSystemService(Context.MEDIA_PROJECTION_SERVICE) as MediaProjectionManager?

/**
 * Returns the MediaRouter instance.
 */
inline val Context.mediaRouter
    get() = getSystemService(Context.MEDIA_ROUTER_SERVICE) as MediaRouter?

/**
 * Returns the MediaSessionManager instance.
 *
 * @requiresApi API level 21 (Lollipop) or higher
 */
inline val Context.mediaSessionManager
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    get() = getSystemService(Context.MEDIA_SESSION_SERVICE) as MediaSessionManager?

/**
 * Returns the NfcManager instance.
 */
inline val Context.nfcManager
    get() = getSystemService(Context.NFC_SERVICE) as NfcManager?

/**
 * Returns the NotificationManager instance.
 */
inline val Context.notificationManager
    get() = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager?

/**
 * Returns the NsdManager instance.
 */
inline val Context.nsdManager
    get() = getSystemService(Context.NSD_SERVICE) as NsdManager?

/**
 * Returns the PowerManager instance.
 */
inline val Context.powerManager
    get() = getSystemService(Context.POWER_SERVICE) as PowerManager?

/**
 * Returns the PrintManager instance.
 *
 * @requiresApi API level 19 (KitKat) or higher
 */
inline val Context.printManager
    @RequiresApi(Build.VERSION_CODES.KITKAT)
    get() = getSystemService(Context.PRINT_SERVICE) as PrintManager?

/**
 * Returns the RestrictionsManager instance.
 *
 * @requiresApi API level 21 (Lollipop) or higher
 */
inline val Context.restrictionsManager
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    get() = getSystemService(Context.RESTRICTIONS_SERVICE) as RestrictionsManager?

/**
 * Returns the SearchManager instance.
 */
inline val Context.searchManager
    get() = getSystemService(Context.SEARCH_SERVICE) as SearchManager?

/**
 * Returns the SensorManager instance.
 */
inline val Context.sensorManager
    get() = getSystemService(Context.SENSOR_SERVICE) as SensorManager?

/**
 * Returns the StorageManager instance.
 */
inline val Context.storageManager
    get() = getSystemService(Context.STORAGE_SERVICE) as StorageManager?

/**
 * Returns the TelecomManager instance.
 *
 * @requiresApi API level 21 (Lollipop) or higher
 */
inline val Context.telecomManager
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    get() = getSystemService(Context.TELECOM_SERVICE) as TelecomManager?

/**
 * Returns the TelephonyManager instance.
 */
inline val Context.telephonyManager
    get() = getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager?

/**
 * Returns the TextServicesManager instance.
 */
inline val Context.textServicesManager
    get() = getSystemService(Context.TEXT_SERVICES_MANAGER_SERVICE) as TextServicesManager?

/**
 * Returns the TvInputManager instance.
 *
 * @requiresApi API level 21 (Lollipop) or higher
 */
inline val Context.tvInputManager
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    get() = getSystemService(Context.TV_INPUT_SERVICE) as TvInputManager?

/**
 * Returns the UiModeManager instance.
 */
inline val Context.uiModeManager
    get() = getSystemService(Context.UI_MODE_SERVICE) as UiModeManager?

/**
 * Returns the UsbManager instance.
 */
inline val Context.usbManager
    get() = getSystemService(Context.USB_SERVICE) as UsbManager?

/**
 * Returns the UserManager instance.
 *
 * @requiresApi API level 17 (Jelly Bean MR1) or higher
 */
inline val Context.userManager
    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    get() = getSystemService(Context.USER_SERVICE) as UserManager?

/**
 * Returns the Vibrator instance.
 */
inline val Context.vibrator
    get() = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator?

/**
 * Returns the WallpaperManager instance.
 */
inline val Context.wallpaperManager
    get() = getSystemService(Context.WALLPAPER_SERVICE) as WallpaperManager?

/**
 * Returns the WifiP2pManager instance.
 */
inline val Context.wifiP2pManager
    get() = getSystemService(Context.WIFI_P2P_SERVICE) as WifiP2pManager?

/**
 * Returns the WifiManager instance.
 */
inline val Context.wifiManager
    get() = applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager?

/**
 * Returns the WindowManager instance.
 */
inline val Context.windowManager
    get() = getSystemService(Context.WINDOW_SERVICE) as WindowManager?

/**
 * Returns the default SharedPreferences instance.
 */
inline val Context.defaultSharedPreferences
    get() = PreferenceManager.getDefaultSharedPreferences(this)

/**
 * Starts an activity with the given intent.
 *
 * @param T the activity class
 */
inline fun <reified T : Any> Context.startActivity() = startActivity(IntentFor<T>(this))

/**
 * Checks if a permission is granted.
 *
 * @param permission the permission to check
 * @return true if the permission is granted, false otherwise
 */
inline fun Context.isPermissionGranted(permission: String): Boolean =
        ContextCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_GRANTED

/**
 * Checks if multiple permissions are granted.
 *
 * @param permissions the permissions to check
 * @return true if all permissions are granted, false otherwise
 */
inline fun Context.arePermissionsGranted(vararg permissions: String): Boolean =
        permissions.all { ContextCompat.checkSelfPermission(this, it) == PackageManager.PERMISSION_GRANTED }

/**
 * Shows a toast message.
 *
 * @param text the message to display
 * @return the toast instance
 */
inline fun Context.toast(text: CharSequence): Toast = Toast.makeText(this, text, Toast.LENGTH_SHORT).apply { show() }

/**
 * Shows a long toast message.
 *
 * @param text the message to display
 * @return the toast instance
 */
inline fun Context.longToast(text: CharSequence): Toast = Toast.makeText(this, text, Toast.LENGTH_LONG).apply { show() }

/**
 * Shows a toast message with a resource ID.
 *
 * @param resId the resource ID of the message to display
 * @return the toast instance
 */
inline fun Context.toast(@StringRes resId: Int): Toast = Toast.makeText(this, resId, Toast.LENGTH_SHORT).apply { show() }

/**
 * Shows a long toast message with a resource ID.
 *
 * @param resId the resource ID of the message to display
 * @return the toast instance
 */
inline fun Context.longToast(@StringRes resId: Int): Toast = Toast.makeText(this, resId, Toast.LENGTH_LONG).apply { show() }

/**
 * Returns whether the device's display is in portrait orientation.
 *
 * @return true if the display is in portrait orientation, false otherwise
 */
val Context.isDisplayPortrait: Boolean
    get() = (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT)

/**
 * Returns the Android ID of the device.
 *
 * @return the Android ID as a string
 */
val Context.androidId: String
    @SuppressLint("HardwareIds")
    get() = Settings.Secure.getString(contentResolver, Settings.Secure.ANDROID_ID)

/**
 * Returns the device's IMEI (International Mobile Equipment Identity) number.
 *
 * Requires the READ_PHONE_STATE permission.
 *
 * @return the IMEI number as a string, or the Android ID if the IMEI is not available
 */
val Context.deviceIMEI: String
    @SuppressLint("HardwareIds")
    @RequiresPermission(Manifest.permission.READ_PHONE_STATE)
    get() {
        // Initialize an empty string to store the unique identifier
        var uniqueIdentifier = ""

        // Get the TelephonyManager instance
        val telephonyManager = getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager

        // Check if the READ_PHONE_STATE permission is granted
        if (hasPermissions(Manifest.permission.READ_PHONE_STATE)) {
            // For Android O and above, use the imei property
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                uniqueIdentifier = telephonyManager.imei ?: androidId
            } else {
                // For older Android versions, use the deprecated deviceId property
                @Suppress("DEPRECATION")
                uniqueIdentifier = telephonyManager.deviceId ?: androidId
            }
        }

        // Return the unique identifier
        return uniqueIdentifier
    }

/**
 * Returns a color value from the resources.
 *
 * @param colorResId the resource ID of the color
 * @return the color value as an integer
 */
fun Context.getColorCompat(@ColorRes colorResId: Int): Int =
    ContextCompat.getColor(this, colorResId)

/**
 * Returns a drawable from the resources.
 *
 * @param drawableResId the resource ID of the drawable
 * @return the drawable instance, or null if not found
 */
fun Context.getDrawableCompat(@DrawableRes drawableResId: Int): Drawable? =
    ContextCompat.getDrawable(this, drawableResId)