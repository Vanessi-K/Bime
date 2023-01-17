package com.example.bime

import android.graphics.Color
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry

class CustomBarChart(barChart: BarChart,  entries: ArrayList<BarEntry>, labels: Array<String>, colorSet: Array<Int>) {

    init {
        populateBarChart(barChart, entries, labels, colorSet)
    }

    private fun populateBarChart(barChart: BarChart, entries: ArrayList<BarEntry>, labels: Array<String>, colorSet: Array<Int>) {

            val ourSet = BarDataSet(entries, "Timerange bar chart")
            val data = BarData(ourSet)

            ourSet.colors = colorSet.toMutableList()
            ourSet.stackLabels = labels

            barChart.data = data

            barChart.description.isEnabled = false

            barChart.xAxis.valueFormatter = WeekFormatter()
            barChart.axisLeft.valueFormatter = HourFormatter()

            barChart.animateY(1400)
    }


}