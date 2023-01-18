package com.example.bime.fragments

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.bime.CustomBarChart
import com.example.bime.CustomPieChart
import com.example.bime.DatabaseHandler
import com.example.bime.R
import com.example.bime.model.Timerange
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import java.time.LocalDate

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
        val dashboardBarChart = view.findViewById<BarChart>(R.id.barChart)

        val db = DatabaseHandler(this.activity)

        val week = Timerange(LocalDate.of(2023, 1,16),6, this.activity)
        week.createBarChart(dashboardBarChart)
        week.createPieChart(dashboardPieChart)
    }
}