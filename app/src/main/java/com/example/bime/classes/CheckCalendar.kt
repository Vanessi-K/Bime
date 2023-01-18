package com.example.bime.classes

import android.widget.CalendarView
import java.time.LocalDate

class CheckCalendar(private val calendarView: CalendarView) {

    private var selectedCalenderDate: LocalDate = LocalDate.now()

    init {
        calendarView.setOnDateChangeListener { view, year, month, dayOfMonth ->
            selectedCalenderDate = LocalDate.of(year, month + 1, dayOfMonth)
        }
    }

    fun selectedDate() : LocalDate {
        return  selectedCalenderDate
    }

}