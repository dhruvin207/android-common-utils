# 📅 DateUtils Library

This repository contains date utility classes for Java and Kotlin. The `DateUtils` class provides a range of functions to manipulate and format dates. Whether you're working with ISO date strings, converting between UTC and local times, or generating relative time spans, this library has got you covered!

## 📜 Java Class

### `DateUtils` (Java)

**Features:**
- **Convert ISO Date to UTC**: Convert ISO date strings to UTC time.
- **Parse Date Strings**: Convert date strings into `Date` objects.
- **Convert UTC to Local Time**: Convert UTC formatted date strings to device local time.
- **Get Abbreviated Day of Week**: Retrieve the abbreviated name of the day of the week.
- **Get Full or Abbreviated Month Name**: Retrieve the full or abbreviated name of the month.
- **Get Relative Time Stamp**: Generate a string indicating the time difference from the current time (e.g., "5 mins ago").

**Usage Example:**
```java
String isoDate = "2024-08-03T12:00:00Z";
String utcTime = DateUtils.getUtcTime(isoDate);
Date date = DateUtils.parseDate(isoDate);
Date localTime = DateUtils.toLocalTime(isoDate, new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"));
String dayOfWeek = DateUtils.getDayOfWeekAbbreviated(isoDate);
String month = DateUtils.getMonth(isoDate);
String timeStamp = DateUtils.getTimeStamp(System.currentTimeMillis());
```

## 📜 Kotlin Class

### `DateUtils` (Kotlin)

**Features:**
- **Convert ISO Date to UTC**: Convert ISO date strings to UTC time.
- **Parse Date Strings**: Convert date strings into `Date` objects.
- **Convert UTC to Local Time**: Convert UTC formatted date strings to device local time.
- **Get Abbreviated Day of Week**: Retrieve the abbreviated name of the day of the week.
- **Get Full or Abbreviated Month Name**: Retrieve the full or abbreviated name of the month.
- **Get Relative Time Stamp**: Generate a string indicating the time difference from the current time (e.g., "5 mins ago").

**Usage Example:**
```kotlin
val isoDate = "2024-08-03T12:00:00Z"
val utcTime = DateUtils.getUtcTime(isoDate)
val date = DateUtils.parseDate(isoDate)
val localTime = DateUtils.toLocalTime(isoDate, SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"))
val dayOfWeek = DateUtils.getDayOfWeekAbbreviated(isoDate)
val month = DateUtils.getMonth(isoDate)
val timeStamp = DateUtils.getTimeStamp(System.currentTimeMillis())
```

## 📚 Methods Overview

- **`getUtcTime(dateAndTime: String): String`**: Converts ISO date string to UTC timezone equivalent.
- **`parseDate(date: String): Date`**: Parses date string and returns a `Date` object.
- **`toLocalTime(utcDate: String, format: SimpleDateFormat): Date`**: Converts UTC time formatted as ISO to device local time.
- **`getDayOfWeekAbbreviated(date: String): String`**: Returns abbreviated (3 letters) day of the week.
- **`getMonth(date: String): String`**: Gets the name of the month from the given date.
- **`getMonthAbbreviated(date: String): String`**: Gets abbreviated name of the month from the given date.
- **`getTimeStamp(originalDate: Date): String`**: Gets a string timestamp phrase like "5 mins ago", "yesterday", "3 days ago".
- **`getTimeStamp(originalDateTime: Long): String`**: Gets a string timestamp phrase for milliseconds since epoch.
