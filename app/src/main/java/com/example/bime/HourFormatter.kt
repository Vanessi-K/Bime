package com.example.bime

import com.github.mikephil.charting.data.*
import com.github.mikephil.charting.formatter.ValueFormatter

class HourFormatter : ValueFormatter() {

    override fun getPieLabel(value: Float, pieEntry: PieEntry?): String {
        return "$value h"
    }
}