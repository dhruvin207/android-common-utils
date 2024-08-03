/**
 * Kotlin extension functions for the String class.
 *
 * @author [Your Name]
 * @since [Version]
 */
@file:JvmName("KtExtString")
@file:JvmMultifileClass

import android.util.Patterns
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone
import java.util.regex.Pattern

/**
 * Converts a string to a Date object using the specified format.
 *
 * @param format the format of the date string
 * @return the Date object
 * @throws ParseException if the string cannot be parsed
 */
@Throws(ParseException::class)
fun String.toDate(format: String): Date {
    // Create a SimpleDateFormat object with the specified format and locale
    val parser = SimpleDateFormat(format, Locale.getDefault())
    // Parse the string into a Date object
    return parser.parse(this)
}

/**
 * Checks if a string is blank (null or empty).
 *
 * @return true if the string is blank, false otherwise
 */
fun String?.isBlank(): Boolean {
    // Check if the string is null or empty
    return this == null || isEmpty()
}

/**
 * Converts a string to a date string with the specified input and output formats.
 *
 * @param inputFormat the format of the input date string
 * @param outputFormat the format of the output date string
 * @return the formatted date string
 * @throws ParseException if the string cannot be parsed
 */
@Throws(ParseException::class)
fun String.toDateWithFormat(inputFormat: String, outputFormat: String): String {
    // Get the UTC time zone
    val gmtTimeZone = TimeZone.getTimeZone(TIME_ZONE_UTC)
    // Create a SimpleDateFormat object with the input format and locale
    val inputDateTimeFormat = SimpleDateFormat(inputFormat, Locale.getDefault())
    // Set the time zone to UTC
    inputDateTimeFormat.timeZone = gmtTimeZone

    // Create a SimpleDateFormat object with the output format and locale
    val outputDateTimeFormat = SimpleDateFormat(outputFormat, Locale.getDefault())
    // Set the time zone to UTC
    outputDateTimeFormat.timeZone = gmtTimeZone
    // Parse the input string into a Date object and format it into a string
    return outputDateTimeFormat.format(inputDateTimeFormat.parse(this))
}

/**
 * Converts a string to a date string with the specified input and output formats,
 * and optional output time zone.
 *
 * @param inputFormat the format of the input date string
 * @param outputFormat the format of the output date string
 * @param outputTimeZone the time zone of the output date string (default is the system default)
 * @return the formatted date string
 * @throws ParseException if the string cannot be parsed
 */
@Throws(ParseException::class)
fun String.toDateWithFormat(
    inputFormat: String,
    outputFormat: String,
    outputTimeZone: TimeZone = TimeZone.getDefault()
): String {
    // Get the UTC time zone
    val gmtTimeZone = TimeZone.getTimeZone(TIME_ZONE_UTC)
    // Create a SimpleDateFormat object with the input format and locale
    val inputDateTimeFormat = SimpleDateFormat(inputFormat, Locale.getDefault())
    // Set the time zone to UTC
    inputDateTimeFormat.timeZone = gmtTimeZone

    // Create a SimpleDateFormat object with the output format and locale
    val outputDateTimeFormat = SimpleDateFormat(outputFormat, Locale.getDefault())
    // Set the time zone to the specified output time zone
    outputDateTimeFormat.timeZone = outputTimeZone
    // Parse the input string into a Date object and format it into a string
    return outputDateTimeFormat.format(inputDateTimeFormat.parse(this))
}

/**
 * Checks if a string matches the specified pattern.
 *
 * @param pattern the pattern to match
 * @return true if the string matches the pattern, false otherwise
 */
fun String.validWithPattern(pattern: Pattern): Boolean {
    // Convert the string to lowercase and check if it matches the pattern
    return pattern.matcher(toLowerCase()).find()
}

/**
 * Checks if a string matches the specified regular expression.
 *
 * @param regex the regular expression to match
 * @return true if the string matches the regular expression, false otherwise
 */
fun String.validWithPattern(regex: String): Boolean {
    // Compile the regular expression into a pattern and check if it matches the string
    return Pattern.compile(regex).matcher(this).find()
}

/**
 * Removes all whitespace characters from a string.
 *
 * @return the string with all whitespace characters removed
 */
fun String.removeWhitespaces(): String {
    // Use a regular expression to replace all whitespace characters with an empty string
    return this.replace("[\\s-]*".toRegex(), "")
}

/**
 * Converts a string to an integer, or returns 0 if the string is not a valid integer.
 *
 * @return the integer value of the string, or 0 if the string is not a valid integer
 */
fun String.toIntOrZero() = if (this.toIntOrNull() == null) 0 else this.toInt()

/**
 * Checks if a string is a numeric value.
 *
 * @return true if the string is a numeric value, false otherwise
 */
fun String.isNumeric(): Boolean = this matches "-?\\d+(\\.\\d+)?".toRegex()

/**
 * Checks if a string contains a web URL.
 *
 * @return true if the string contains a web URL, false otherwise
 */
fun String.containsWebUrl() = Patterns.WEB_URL.matcher(this).find()

/**
 * Returns an empty string if the input string is null, otherwise returns the input string.
 *
 * @return the input string, or an empty string if the input string is null
 */
fun String?.nullToEmpty(): String = this ?: ""

/**
 * Checks if a string is null or equal to "0".
 *
 * @return true if the string is null or equal to "0", false otherwise
 */
fun String?.isNullOrZero() = this == "0" || this == null