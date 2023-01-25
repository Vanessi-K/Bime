package com.example.bime.model

import android.content.Context
import com.example.bime.DatabaseHandler
import java.time.LocalDate


class Entry(var category: Int? = null, val id: Int? = null, var time: Double = 0.0, var day: LocalDate, private val context: Context? = null) {
    override fun toString(): String {
        return "Entry_id: ${id}, Entry_category: $category, Entry_time: $time, Entry_day: $day"
    }

    fun getCategory(): Category? {
        val db = DatabaseHandler(context)
        return if(category != null) db.getCategoryById(category!!) else null
    }
}