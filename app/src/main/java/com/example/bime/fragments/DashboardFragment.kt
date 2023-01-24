package com.example.bime.fragments

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.text.Html
import android.text.Html.FROM_HTML_MODE_LEGACY
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.bime.R
import com.example.bime.model.Entry
import com.example.bime.model.Timerange
import com.github.mikephil.charting.charts.PieChart
import com.google.android.material.bottomappbar.BottomAppBar
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

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val bottomAppBar : BottomAppBar = view.findViewById(R.id.bottomAppBar)
        bottomAppBar.setOnMenuItemClickListener() {
            when (it.itemId) {
                R.id.weeklyOverview -> {
                    navigateToWeekly()
                    true
                }
                else -> false
            }
        }

        val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE) ?: return
        val nickname = sharedPref.getString("BIME_Nickname", "you")
        val nicknameEscaped: String = TextUtils.htmlEncode(nickname)

        view.findViewById<TextView>(R.id.headerTitle).text = Html.fromHtml(resources.getString(R.string.headline_dashboard, nicknameEscaped),FROM_HTML_MODE_LEGACY)

        val dashboardPieChart = view.findViewById<PieChart>(R.id.pieChart)

        val last5Days = Timerange(LocalDate.now().minusDays(4), 4, this.activity)
        last5Days.createPieChart(dashboardPieChart)

        childFragmentManager.beginTransaction()
            .replace(R.id.fragment_timerange_list, TimerangeListFragment("Last 5 days", last5Days, this::navigateToAddEntry, this::navigateToEditEntry))
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
        val navController = findNavController()
        val action = DashboardFragmentDirections.actionDashboardToAddEntry()
        navController.navigate(action)
    }

    private fun navigateToEditEntry(entry: Entry) {
        val navController = findNavController()
        val action = entry.id?.let { DashboardFragmentDirections.actionDashboardToEditEntry(it) }
        if (action != null) navController.navigate(action)
    }

    private fun navigateToWeekly() {
        val navController = findNavController()
        val action = DashboardFragmentDirections.actionDashboardToWeeklyOverview(LocalDate.now().toString())
        navController.navigate(action)
    }
}