**
 * Kotlin extension functions for numbers.
 *
 * @author [Dhruvin]
 * @since [1.2]
 */
@file:JvmName("KtExtNumber")
@file:JvmMultifileClass


import java.text.DecimalFormat

/**
 * Returns a default value of -1 if the integer is null.
 *
 * @return the integer value or -1 if null
 */
fun Int?.nullToDefault() = this ?: -1

/**
 * Returns a default value of 0 if the integer is null.
 *
 * @return the integer value or 0 if null
 */
fun Int?.nullToZero() = this ?: 0

/**
 * Checks if the integer is equal to 1, often used for boolean-like values.
 *
 * @return true if the integer is 1, false otherwise
 */
fun Int?.isTrue() = this == 1

/**
 * Checks if the double is null or equal to 0.0.
 *
 * @return true if the double is null or 0.0, false otherwise
 */
fun Double?.isNullOrZero() = this == 0.0 || this == null

/**
 * Returns a default value of -1.0 if the double is null.
 *
 * @return the double value or -1.0 if null
 */
fun Double?.nullToDefault() = this ?: -1.0

/**
 * Returns a default value of 0.0 if the double is null.
 *
 * @return the double value or 0.0 if null
 */
fun Double?.nullToZero() = this ?: 0.0

/**
 * Converts the double to a price string with the specified currency.
 *
 * @param currency the currency symbol to append to the price
 * @return the formatted price string
 */
fun Double?.convertToPrice(currency: String) = String.format("%s%s", this.nullToZero().formatMoney(), currency)

/**
 * Formats the double as a money string with commas as thousand separators.
 *
 * @return the formatted money string
 */
fun Double.formatMoney(): String {
    // Create a decimal format with commas as thousand separators
    val formatter = DecimalFormat("###,###,###")
    return formatter.format(this)
}