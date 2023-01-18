package com.example.bime.classes.charts

import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.data.*
import com.github.mikephil.charting.formatter.ValueFormatter

class HourFormatter : ValueFormatter() {

    override fun getPieLabel(value: Float, pieEntry: PieEntry?): String {
        return "$value h"
    }

    override fun getAxisLabel(value: Float, axis: AxisBase?): String {
        return "$value h"
    }
}