package com.example.bime

import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.formatter.ValueFormatter

class WeekFormatter : ValueFormatter() {
    private val days = arrayOf("Mo", "Tu", "We", "Th", "Fr", "Sa", "Su")
    override fun getAxisLabel(value: Float, axis: AxisBase?): String {
        return days.getOrNull(value.toInt()) ?: value.toString()
    }
}