package com.example.bime.fragments

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.bime.DatabaseHandler
import com.example.bime.R
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry

class DashboardFragment : Fragment() {

    private lateinit var ourPieChart: PieChart

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

        ourPieChart = view.findViewById<PieChart>(R.id.pieChart)

        val db = DatabaseHandler(this.activity)

        val entries = db.getAllEntries();

        val entriesCategoryArray = Array<Int>(entries.size) { 0 }
        val entriesTimeArray = Array<String>(entries.size) { "hey" }

        var index = 0
        for (entry in entries) {
            entriesCategoryArray[index] = entry.category!!
            entriesTimeArray[index] = entry.time.toString()
            index++
        }

        populatePieChart(entriesCategoryArray, entriesTimeArray)
    }

    private fun populatePieChart(values: Array<Int>, labels: Array<String>) {
        //an array to store the pie slices entry
        val ourPieEntry = ArrayList<PieEntry>()
        var i = 0

        for (entry in values) {
            //converting to float
            var value = values[i].toFloat()
            var label = labels[i]
            //adding each value to the pieentry array
            ourPieEntry.add(PieEntry(value, label))
            i++
        }

        //assigning color to each slices
        val pieShades: ArrayList<Int> = ArrayList()
        pieShades.add(Color.parseColor("#0E2DEC"))
        pieShades.add(Color.parseColor("#B7520E"))
        pieShades.add(Color.parseColor("#5E6D4E"))
        pieShades.add(Color.parseColor("#DA1F12"))

        //add values to the pie dataset and passing them to the constructor
        val ourSet = PieDataSet(ourPieEntry, "")
        val data = PieData(ourSet)

        //setting the slices divider width
        ourSet.sliceSpace = 1f

        //populating the colors and data
        ourSet.colors = pieShades
        ourPieChart.data = data
        //setting color and size of text
        data.setValueTextColor(Color.WHITE)
        data.setValueTextSize(10f)

        //add an animation when rendering the pie chart
        ourPieChart.animateY(1400, Easing.EaseInOutQuad)
        //disabling center hole
        ourPieChart.isDrawHoleEnabled = false
        //do not show description text
        ourPieChart.description.isEnabled = false
        //legend enabled and its various appearance settings
        ourPieChart.legend.isEnabled = true
        ourPieChart.legend.orientation = Legend.LegendOrientation.HORIZONTAL
        ourPieChart.legend.horizontalAlignment = Legend.LegendHorizontalAlignment.CENTER
        ourPieChart.legend.isWordWrapEnabled = true

        //dont show the text values on slices e.g Antelope, impala etc
        ourPieChart.setDrawEntryLabels(false)
        //refreshing the chart
        ourPieChart.invalidate()
    }

}