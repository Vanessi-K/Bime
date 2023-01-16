package com.example.bime

class Entry(var category: String? = "", var id: String? = null, var time: String? = "", var day: String? = "")
{
    override fun toString(): String {
        return "Category_id: ${category}, Entry_id: ${id}, Entry_time: ${time}, Entry_day: ${day}";
    }
}