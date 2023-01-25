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

    private val allCategories = db.getAllCategories().toList()
    val listOfDaysInRange : MutableList<Day> = mutableListOf()

    private val categoriesWithTime: List<Category>
        get() {
            val categoriesWithTimeList : MutableList<Category> = mutableListOf()

            for (category in allCategories) {
                val time = timePerCategory(category.id)

                if(time > 0) {
                    categoriesWithTimeList.add(category)
                }
            }
            return categoriesWithTimeList.toList()
        }

    init {
        for(i in 0..timerange) {
            val day= Day(startDay.plusDays(i.toLong()), context)
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

        return time
    }

    fun createBarChart(barChart: BarChart) {
        val barCategories = allCategories
        CustomBarChart(barChart, generateBarEntries(), getCategoryLabels(barCategories), getCategoryColours(barCategories))
    }

    fun createPieChart(pieChart: PieChart) {
        val dayNumber = timerange + 1
        val endDay = startDay.plusDays(timerange.toLong())
        val prefix = if(endDay == LocalDate.now()) "Last " else ""

        val pieChartTitle = "$prefix $dayNumber days"
        val pieChartSubtitle = "${this.getFirstDayFormatted()} - ${this.getLastDayFormatted()}"
        CustomPieChart(pieChart, generatePieEntries(), getCategoryLabels(categoriesWithTime), getCategoryColours(categoriesWithTime), pieChartTitle, pieChartSubtitle)
    }

    private fun generatePieEntries(): Array<Double> {
        val entriesByCategory = Array(categoriesWithTime.size){0.0}

        for (categoryIndex in categoriesWithTime.indices) {
            entriesByCategory[categoryIndex] = timePerCategory(categoriesWithTime[categoryIndex].id)
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

    private fun getCategoryLabels(categories : List<Category>): Array<String> {
        val categoryLabelsArray = Array(categories.size) { "undefined" }

        for (categoryIndex in categories.indices) {
            categoryLabelsArray[categoryIndex] = categories[categoryIndex].name
        }
        return categoryLabelsArray
    }

    private fun getCategoryColours(categories: List<Category>): Array<Int> {
        val categoryColorsArray = Array(categories.size) { 0 }

        for (categoryIndex in categories.indices) {
            categoryColorsArray[categoryIndex] = Color.parseColor(categories[categoryIndex].colour)
        }
        return categoryColorsArray
    }

    fun getLastDayFormatted(): String {
        val formatter = DateTimeFormatter.ofPattern("dd.MM")
        return formatter.format(startDay.plusDays(timerange.toLong()))
    }

    fun getFirstDayFormatted(): String {
        val formatter = DateTimeFormatter.ofPattern("dd.MM")
        return formatter.format(startDay)
    }
}