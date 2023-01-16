package com.example.bime

import android.content.Context
import java.time.LocalDate

class Day(var day: LocalDate, private val context: Context?) {

    val db = DatabaseHandler(context);

    val listOfEntries get() = db.getEntriesByDate(day)

    override fun toString(): String {
        return "Day_date: ${day}, Day_listOfDays: ${listOfEntries}";
    }

    fun timePerDay(): Double {
        var time = 0.0

        for (i in listOfEntries) {
            time += i.time!!
        }
        return time
    }

    fun timePerCategory(category: Int): Double {
        var time = 0.0
        for (i in listOfEntries) {
            if (i.category == category) {
                time += i.time!!
            }
        }
        return time
    }
}
