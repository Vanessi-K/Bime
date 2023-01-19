package com.example.bime.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.bime.DatabaseHandler
import com.example.bime.R
import com.example.bime.model.Timerange
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.charts.PieChart
import com.google.android.material.floatingactionbutton.FloatingActionButton
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

        val last5Days = Timerange(LocalDate.now().minusDays(4), 4, this.activity)
        last5Days.createPieChart(dashboardPieChart)

        childFragmentManager.beginTransaction()
            .add(R.id.fragment_timerange_list, TimerangeListFragment("Last 5 days", last5Days))
            .commit()
        childFragmentManager.executePendingTransactions()

        view.findViewById<TextView>(R.id.myWeek).setOnClickListener {
            navigateToWeekly()
        }

        view.findViewById<FloatingActionButton>(R.id.addActionButton).setOnClickListener() {
            navigateToAddEntry()
        }
    }

    private fun navigateToAddEntry() {
        findNavController().navigate(R.id.action_dashboard_to_addEntry)
    }

    private fun navigateToWeekly() {
        findNavController().navigate(R.id.action_dashboard_to_weeklyOverview)
    }
}