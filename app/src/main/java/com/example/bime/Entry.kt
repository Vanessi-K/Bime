package com.example.bime

import java.time.LocalDate


class Entry(var category: Int? = null, val id: Int? = null, var time: Double = 0.0, var day: LocalDate) {
    override fun toString(): String {
        return "Entry_id: ${id}, Entry_category: ${category}, Entry_time: ${time}, Entry_day: ${day}";
    }
}