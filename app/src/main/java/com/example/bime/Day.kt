package com.example.bime

import java.sql.Date

class Day(var day: Date? = null) {

    val db = DatabaseHandler(this.activity)

    val listOfEntries get() = db.getEntriesByDay(day)

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
