package com.example.bime.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.bime.DatabaseHandler
import com.example.bime.R
import com.example.bime.model.Timerange
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.charts.PieChart
import java.time.LocalDate

class WeeklyOverviewFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_weekly_overview, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val passedDate = LocalDate.now();
        val weekStartDay = passedDate.minusDays(passedDate.dayOfWeek.value.toLong() - 1)

        val db = DatabaseHandler(this.activity)

        val weeklyBarChart = view.findViewById<BarChart>(R.id.barChart)

        val weekTimerange = Timerange(weekStartDay,6, this.activity)
        weekTimerange.createBarChart(weeklyBarChart)

    }

}