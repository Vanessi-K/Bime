package com.example.bime.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.bime.R
import com.example.bime.classes.OnSwipeListener
import com.example.bime.model.Entry
import com.example.bime.model.Timerange
import com.github.mikephil.charting.charts.BarChart
import com.google.android.material.bottomappbar.BottomAppBar
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.time.LocalDate
import java.time.temporal.WeekFields
import java.util.*

class WeeklyOverviewFragment : Fragment() {

    private val args: WeeklyOverviewFragmentArgs by navArgs()

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

        val bottomAppBar : BottomAppBar = view.findViewById(R.id.bottomAppBar)
        bottomAppBar.setOnMenuItemClickListener() {
            when (it.itemId) {
                R.id.dashboard -> {
                    navigateToDashboard()
                    true
                }
                R.id.weeklyOverview -> {
                    navigateToSelf(LocalDate.now())
                    true
                }
                else -> false
            }
        }

        val passedDate = LocalDate.parse(args.date)
        val weekStartDay = passedDate.minusDays(passedDate.dayOfWeek.value.toLong() - 1)


        val backToCurrentBtn = view.findViewById<TextView>(R.id.backToCurrentWeek)
        if(passedDate.isEqual(LocalDate.now())) {
            backToCurrentBtn.visibility = View.GONE
        } else {
            backToCurrentBtn.setOnClickListener { navigateToSelf(LocalDate.now()) }
        }

        val weekTimerange = Timerange(weekStartDay,6, this.activity)

        val weeklyBarChart = view.findViewById<BarChart>(R.id.barChart)
        weekTimerange.createBarChart(weeklyBarChart)

        val weekFields = WeekFields.of(Locale.getDefault())
        val weekOfYearNumber: Int = passedDate.get(weekFields.weekOfWeekBasedYear())
        val weekOfYearText = "Calendar week $weekOfYearNumber"

        view.findViewById<TextView>(R.id.weekLabel).text = weekOfYearText
        view.findViewById<TextView>(R.id.days).text = "${weekTimerange.getFirstDayFormatted()} - ${weekTimerange.getLastDayFormatted()}"

        childFragmentManager.beginTransaction()
            .replace(R.id.fragment_timerange_list, TimerangeListFragment(weekOfYearText, weekTimerange, this::navigateToAddEntry, this::navigateToEditEntry))
            .commit()
        childFragmentManager.executePendingTransactions()

        view.findViewById<FloatingActionButton>(R.id.addActionButton).setOnClickListener() {
            navigateToAddEntry()
        }

        view.findViewById<TextView>(R.id.previousWeek).setOnClickListener() { navigateToSelf(passedDate.minusDays(7)) }

        view.findViewById<ImageView>(R.id.leftArrow).setOnClickListener() { navigateToSelf(passedDate.minusDays(7)) }

        view.findViewById<TextView>(R.id.nextWeek).setOnClickListener() { navigateToSelf(passedDate.plusDays(7)) }

        view.findViewById<ImageView>(R.id.rightArrow).setOnClickListener() { navigateToSelf(passedDate.plusDays(7)) }


        weeklyBarChart.setOnTouchListener(object : OnSwipeListener(this.context) {
            override fun onSwipeLeft() {
                navigateToSelf(passedDate.plusDays(7))
            }

            override fun onSwipeRight() {
                navigateToSelf(passedDate.minusDays(7))
            }
        })
    }

    private fun navigateToAddEntry() {
        val navController = findNavController()
        val action = WeeklyOverviewFragmentDirections.actionWeeklyOverviewToAddEntry()
        navController.navigate(action)
    }

    private fun navigateToEditEntry(entry: Entry) {
        val navController = findNavController()
        val action = entry.id?.let { WeeklyOverviewFragmentDirections.actionWeeklyOverviewToEditEntry(it) }
        if (action != null) navController.navigate(action)
    }

    private fun navigateToSelf(date: LocalDate) {
        val navController = findNavController()
        val action = WeeklyOverviewFragmentDirections.actionWeeklyOverviewSelf(date.toString())
        navController.navigate(action)
    }

    private fun navigateToDashboard() {
        val navController = findNavController()
        val action = WeeklyOverviewFragmentDirections.actionWeeklyOverviewToDashboard()
        navController.navigate(action)
    }
}