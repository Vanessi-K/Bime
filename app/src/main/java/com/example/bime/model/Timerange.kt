package com.example.bime.model

import android.content.Context
import android.graphics.Color
import com.example.bime.classes.charts.CustomBarChart
import com.example.bime.classes.charts.CustomPieChart
import com.example.bime.DatabaseHandler
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.BarEntry
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class Timerange(private var startDay: LocalDate, private var timerange: Int, private val context: Context?) {

    private val db = DatabaseHandler(this.context)

    private val allCategories = db.getAllCategories()
    val listOfDaysInRange : MutableList<Day> = mutableListOf()
    init {
        for(i in 0..timerange) {
            val day= Day(startDay.plusDays(i.toLong()), context)
            println(day)
            listOfDaysInRange.add(day)
        }
    }

    override fun toString(): String {
        return "Timerange_startDay: $startDay, Timerange_timerange: $timerange, Timerange_listOfDaysInRange: $listOfDaysInRange"
    }

    private fun timePerCategory(category: Int): Double {
        var time = 0.0
        for (day in listOfDaysInRange) {
            time+=day.timePerCategory(category)
        }

        println("$category - $time")
        return time
    }

    fun createBarChart(barChart: BarChart) {
        CustomBarChart(barChart, generateBarEntries(), getCategoryLabels(), getCategoryColours())
    }

    fun createPieChart(pieChart: PieChart) {
        val dayNumber = timerange + 1
        val endDay = startDay.plusDays(timerange.toLong())
        val prefix = if(endDay == LocalDate.now()) "Last " else ""

        val pieChartTitle = "$prefix $dayNumber days"
        val pieChartSubtitle = "${this.getFirstDay()} - ${this.getLastDay()}"
        CustomPieChart(pieChart, generatePieEntries(), getCategoryLabels(), getCategoryColours(), pieChartTitle, pieChartSubtitle)
    }

    private fun generatePieEntries(): Array<Double> {
        val entriesByCategory = Array(allCategories.size){0.0}

        for (categoryIndex in allCategories.indices) {
            println(categoryIndex)
            entriesByCategory[categoryIndex] = timePerCategory(allCategories[categoryIndex].id)
        }

        return entriesByCategory
    }

    private fun generateBarEntries(): ArrayList<BarEntry> {
        val entries = ArrayList<BarEntry>()
        for ((index, day) in listOfDaysInRange.withIndex()) {
            val dayValueArrayByCategory = FloatArray(allCategories.size) { 0.0f }

            for(categoryIndex in allCategories.indices) {
                val category = allCategories[categoryIndex]
                val timePerCategory = day.timePerCategory(category.id).toFloat()
                dayValueArrayByCategory[categoryIndex] = timePerCategory
            }
            entries.add(BarEntry(index.toFloat(), dayValueArrayByCategory))
        }
        return entries
    }

    private fun getCategoryLabels(): Array<String> {
        val categoryLabelsArray = Array(allCategories.size) { "undefined" }

        for (categoryIndex in allCategories.indices) {
            categoryLabelsArray[categoryIndex] = allCategories[categoryIndex].name
        }
        return categoryLabelsArray
    }

    private fun getCategoryColours(): Array<Int> {
        val categoryColorsArray = Array(allCategories.size) { 0 }

        for (categoryIndex in allCategories.indices) {
            categoryColorsArray[categoryIndex] = Color.parseColor(allCategories[categoryIndex].colour)
        }
        return categoryColorsArray
    }

    fun getLastDay(): String {
        val formatter = DateTimeFormatter.ofPattern("dd.MM")
        return formatter.format(startDay.plusDays(timerange.toLong()))
    }

    fun getFirstDay(): String {
        val formatter = DateTimeFormatter.ofPattern("dd.MM")
        return formatter.format(startDay)
    }
}