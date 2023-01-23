package com.example.bime.classes.charts

import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.data.*
import com.github.mikephil.charting.formatter.ValueFormatter
import java.math.BigDecimal
import java.math.RoundingMode

class HourFormatter : ValueFormatter() {

    override fun getPieLabel(value: Float, pieEntry: PieEntry?): String {
        val shortValue = BigDecimal(value.toDouble()).setScale(2, RoundingMode.HALF_EVEN)
        return "$shortValue h"
    }

    override fun getAxisLabel(value: Float, axis: AxisBase?): String {
        val shortValue = BigDecimal(value.toDouble()).setScale(2, RoundingMode.HALF_EVEN)
        return "$shortValue h"
    }
}