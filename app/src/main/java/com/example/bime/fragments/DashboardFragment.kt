package com.example.bime.fragments

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.bime.CustomPieChart
import com.example.bime.DatabaseHandler
import com.example.bime.R
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry

class DashboardFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dashboard, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val dashboardPieChart = view.findViewById<PieChart>(R.id.pieChart)

        val db = DatabaseHandler(this.activity)

        val entries = db.getAllEntries()
        val categories = db.getAllCategories();

        val entriesTimeArray = Array<Double>(categories.size) { 0.0 }
        val categoryLabelsArray = Array<String>(categories.size) { "undefined" }
        val categoryColorArray = Array<Int>(categories.size) { 0 }

        var index = 0
        for (category in categories) {
            categoryLabelsArray[index] = category.name
            categoryColorArray[index] = Color.parseColor(category.colour);

            for (entry in entries) {
                if (entry.category == category.id) {
                    println(entriesTimeArray[index])
                    entriesTimeArray[index] += entry.time
                    println(entriesTimeArray[index])
                }
                println(entry)
            }
            index++
        }

        CustomPieChart(dashboardPieChart, entriesTimeArray, categoryLabelsArray, categoryColorArray)
    }
}