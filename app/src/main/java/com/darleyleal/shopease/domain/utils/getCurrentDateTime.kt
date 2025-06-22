package com.darleyleal.shopease.domain.utils

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.ZoneId
import java.util.Locale

fun getCurrentDateTime(): String {
    val zoneId = ZoneId.of("America/Sao_Paulo")
    val locale = Locale("pt", "BR")

    val dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm a", locale)
    val dateTime = LocalDateTime.now(zoneId).format(dateTimeFormatter)
        .replace("-", "/").uppercase().replace(".", "")

    val dayOfWeekFormatter = DateTimeFormatter.ofPattern("EEEE", locale)
    val dayOfWeek = LocalDateTime.now(zoneId).format(dayOfWeekFormatter).replaceFirstChar {
        it.uppercase()
    }

    // Return exemple: Sexta-feira (13/06/2025) às 5:17 AM
    return "$dayOfWeek (${dateTime.substring(0, dateTime.indexOf("5") + 1)}) às ${
        dateTime.substring(
            10,
            dateTime.indexOf("M") + 1
        )
    }"
}