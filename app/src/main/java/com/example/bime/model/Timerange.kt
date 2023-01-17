package com.example.bime.model

import android.content.Context
import com.example.bime.DatabaseHandler
import java.time.LocalDate

class Timerange(var startDay: LocalDate, var timerange: Int, private val context: Context) {

    val db = DatabaseHandler(this.context)

    val listOfEntriesInRange : MutableList<Day> = mutableListOf()
    init {
        for(i in 0..timerange) {
            listOfEntriesInRange.add(Day(startDay.plusDays(i.toLong()), context))
        }
    }

    override fun toString(): String {
        return "Timerange_startDay: ${startDay}, Timerange_timerange: ${timerange}, Timerange_listOfDaysInRange: ${listOfEntriesInRange}";
    }

    fun timePerCategory(category: Int): Double {
        var time = 0.0
        for (day in listOfEntriesInRange) {
            time+=day.timePerCategory(category)
        }

        return time
    }

    fun timePerTimerange(): Double {
        var time = 0.0
        for (day in listOfEntriesInRange) {
            time+=day.timePerDay()
        }

        return time
    }
}