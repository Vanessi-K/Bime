package com.example.bime.classes.charts

import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry

class CustomBarChart(barChart: BarChart,  entries: ArrayList<BarEntry>, labels: Array<String>, colorSet: Array<Int>, titel: String) {

    init {
        populateBarChart(barChart, entries, labels, colorSet)
    }

    private fun populateBarChart(barChart: BarChart, entries: ArrayList<BarEntry>, labels: Array<String>, colorSet: Array<Int>) {

        val ourSet = BarDataSet(entries, "")
        val data = BarData(ourSet)

        ourSet.colors = colorSet.toMutableList()
        ourSet.stackLabels = labels
        ourSet.setDrawValues(false)

        barChart.description.isEnabled = false

        barChart.xAxis.valueFormatter = WeekFormatter()
        barChart.axisLeft.valueFormatter = HourFormatter()

        barChart.xAxis.setDrawGridLines(false)
        barChart.xAxis.setDrawAxisLine(false)
        barChart.xAxis.textSize = 15f
        barChart.xAxis.position = XAxis.XAxisPosition.BOTTOM
        barChart.extraBottomOffset = 2f

        barChart.axisRight.isEnabled = false
        barChart.axisLeft.setDrawGridLines(false)

        barChart.legend.verticalAlignment = Legend.LegendVerticalAlignment.TOP
        barChart.legend.horizontalAlignment = Legend.LegendHorizontalAlignment.RIGHT
        barChart.legend.orientation = Legend.LegendOrientation.HORIZONTAL
        barChart.legend.isWordWrapEnabled = true
        barChart.legend.textSize = 15f
        barChart.legend.isEnabled = true

        barChart.data = data

        barChart.setTouchEnabled(false)

        barChart.animateY(1400)
    }
}