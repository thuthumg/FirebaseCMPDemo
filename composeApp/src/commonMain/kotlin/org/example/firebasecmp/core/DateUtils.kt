package org.example.firebasecmp.core


import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import kotlin.time.Clock

fun formatCurrentDate(): String {
    val today = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())
    val monthName = today.month.name.lowercase().replaceFirstChar { it.uppercase() }.take(3)
    val formatted = "${today.day}${getOrdinalSuffix(today.day)} $monthName ${today.year}"
    return formatted
}

fun getOrdinalSuffix(n: Int): String {
    if (n in 11..13) return "th"
    return when (n % 10) {
        1 -> "st"
        2 -> "nd"
        3 -> "rd"
        else -> "th"
    }
}