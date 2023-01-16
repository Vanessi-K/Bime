package com.example.bime

import java.sql.Date

class Entry(var category: Int? = null, val id: Int? = null, var time: Float? = null, var day: Date? = null) {
    override fun toString(): String {
        return "Entry_id: ${id}, Entry_category: ${category}, Entry_time: ${time}, Entry_day: ${day}";
    }
}