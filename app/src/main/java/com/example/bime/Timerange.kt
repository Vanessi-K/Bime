package com.example.bime

import android.content.Context
import java.sql.Date

class Timerange(var startDay: Date? = null, var timerange: Int? = null, private val context: Context) {

    val db = DatabaseHandler(this.activity)

    val listOfDaysInRange get() = db.getEntriesByDay(startDay.day+ timerange)

    override fun toString(): String {
        return "Timerange_startDay: ${startDay}, Timerange_timerange: ${timerange}, Timerange_listOfDaysInRange: ${listOfDaysInRange}";
    }

    fun dayPerTimerange(){
        var days = 0
        for (i in listOfDaysInRange) {
            days += 1
        }
        return days
    }
}