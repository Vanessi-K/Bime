package com.example.bime.classes

import android.widget.CalendarView
import java.time.LocalDate
import java.time.ZoneOffset

class CheckCalendar(private val calendarView: CalendarView, private var selectedCalenderDate: LocalDate) {

    init {
        calendarView.setOnDateChangeListener { _, year, month, dayOfMonth ->
            selectedCalenderDate = LocalDate.of(year, month + 1, dayOfMonth)
        }
    }

    fun selectedDate(): LocalDate {
        return selectedCalenderDate
    }

    fun setDate(day: LocalDate) {
        calendarView.date = day.atStartOfDay().toInstant(
            ZoneOffset.UTC
        ).toEpochMilli()
    }

}