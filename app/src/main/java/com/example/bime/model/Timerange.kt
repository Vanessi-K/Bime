package com.example.bime.model

import android.content.Context
import android.graphics.Color
import com.example.bime.CustomBarChart
import com.example.bime.CustomPieChart
import com.example.bime.DatabaseHandler
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.BarEntry
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class Timerange(var startDay: LocalDate, var timerange: Int, private val context: Context?) {

    val db = DatabaseHandler(this.context)

    val allCategories = db.getAllCategories()
    val listOfDaysInRange : MutableList<Day> = mutableListOf()
    init {
        for(i in 0..timerange) {
            listOfDaysInRange.add(Day(startDay.plusDays(i.toLong()), context))
        }
    }

    override fun toString(): String {
        return "Timerange_startDay: ${startDay}, Timerange_timerange: ${timerange}, Timerange_listOfDaysInRange: ${listOfDaysInRange}";
    }

    fun timePerCategory(category: Int): Double {
        var time = 0.0
        for (day in listOfDaysInRange) {
            time+=day.timePerCategory(category)
        }

        return time
    }

    fun timePerTimerange(): Double {
        var time = 0.0
        for (day in listOfDaysInRange) {
            time+=day.timePerDay()
        }

        return time
    }

    fun createBarChart(barChart: BarChart) {
        val endDay = startDay.plusDays(timerange.toLong())

        val formatter = DateTimeFormatter.ofPattern("dd.MM");
        val pieChartTitle = "${formatter.format(startDay)} - ${formatter.format(endDay)}"
        CustomBarChart(barChart, generateBarEntries(), getCategoryLabels(), getCategoryColours(), pieChartTitle)
    }

    fun createPieChart(pieChart: PieChart) {
        val dayNumber = timerange + 1
        val endDay = startDay.plusDays(timerange.toLong())
        var prefix = if(endDay == LocalDate.now()) "Last " else ""

        val pieChartTitle = "$prefix $dayNumber days"
        val formatter = DateTimeFormatter.ofPattern("dd.MM");
        val pieChartSubtitle = "${formatter.format(startDay)} - ${formatter.format(endDay)}"
        CustomPieChart(pieChart, generatePieEntries(), getCategoryLabels(), getCategoryColours(), pieChartTitle, pieChartSubtitle)
    }

    private fun generatePieEntries(): Array<Double> {
        val entriesByCategory = Array<Double>(allCategories.size){0.0}

        for (categoryIndex in allCategories.indices) {
            entriesByCategory[categoryIndex] = timePerCategory(allCategories[categoryIndex].id!!)
        }

        return entriesByCategory
    }

    private fun generateBarEntries(): ArrayList<BarEntry> {
        val entries = ArrayList<BarEntry>()
        var index = 0
        for (day in listOfDaysInRange) {
            val dayValueArrayByCategory = FloatArray(allCategories.size) { 0.0f }

            for(categoryIndex in allCategories.indices) {
                val category = allCategories[categoryIndex]
                val timePerCategory = day.timePerCategory(category.id).toFloat()
                println(day)
                println(category.id)
                println(timePerCategory)
                dayValueArrayByCategory[categoryIndex] = timePerCategory;
            }
            entries.add(BarEntry(index.toFloat(), dayValueArrayByCategory))
            index++
        }
        return entries
    }

    private fun getCategoryLabels(): Array<String> {
        val categoryLabelsArray = Array<String>(allCategories.size) { "undefined" }

        for (categoryIndex in allCategories.indices) {
            categoryLabelsArray[categoryIndex] = allCategories[categoryIndex].name
        }
        return categoryLabelsArray;
    }

    private fun getCategoryColours(): Array<Int> {
        val categoryColorsArray = Array<Int>(allCategories.size) { 0 }

        for (categoryIndex in allCategories.indices) {
            categoryColorsArray[categoryIndex] = Color.parseColor(allCategories[categoryIndex].colour)
        }
        return categoryColorsArray;
    }
}