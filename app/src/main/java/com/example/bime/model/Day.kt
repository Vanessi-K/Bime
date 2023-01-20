package com.example.bime.model

import android.content.Context
import com.example.bime.DatabaseHandler
import java.time.LocalDate

class Day(var day: LocalDate, context: Context?) {

    private val db = DatabaseHandler(context)

    val listOfEntries get() = db.getEntriesByDate(day)

    override fun toString(): String {
        return "Day_date: $day, Day_listOfEntries: $listOfEntries"
    }

    fun timePerCategory(category: Int): Double {
        var time = 0.0
        for (i in listOfEntries) {
            if (i.category == category) {
                time += i.time
            }
        }
        return time
    }
}
