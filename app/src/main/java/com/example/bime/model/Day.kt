package com.example.bime.model

import android.content.Context
import com.example.bime.DatabaseHandler
import java.time.LocalDate

class Day(var day: LocalDate, private val context: Context?) {

    val db = DatabaseHandler(context);

    val listOfEntries get() = db.getEntriesByDate(day)

    override fun toString(): String {
        return "Day_date: ${day}, Day_listOfEntries: ${listOfEntries}";
    }

    fun timePerDay(): Double {
        var time = 0.0

        for (i in listOfEntries) {
            time += i.time!!
        }
        return time
    }

    fun timePerCategory(category: Int): Double {
        println("timePerCategory")
        println("size list of entries: " + listOfEntries.size)
        var time = 0.0
        for (i in listOfEntries) {
            println("entry: ${i}")
            if (i.category == category) {
                time += i.time!!
            }
        }
        return time
    }
}
