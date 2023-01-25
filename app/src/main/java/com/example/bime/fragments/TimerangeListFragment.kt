package com.example.bime.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.bime.recycler.day.ItemDayAdapter
import com.example.bime.R
import com.example.bime.model.Entry
import com.example.bime.model.Timerange

class TimerangeListFragment(private val header: String = "", private val timerange: Timerange? = null, private val currentTimeTop: Boolean = false) : Fragment() {

    private var actionsAvailable: Boolean = false
    private lateinit var navigateToAddEntry: () -> Unit
    private lateinit var navigateToEditEntry: (Entry) -> Unit

    constructor(header: String, timerange: Timerange, navigateToAddEntry: () -> Unit, navigateToEditEntry: (Entry) -> Unit, currentTimeTop: Boolean = false) : this(header, timerange, currentTimeTop) {
        this.navigateToAddEntry = navigateToAddEntry
        this.navigateToEditEntry = navigateToEditEntry
        this.actionsAvailable = true
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_timerange_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if(timerange != null && actionsAvailable) {
            var daysList = timerange.listOfDaysInRange.filter { it.listOfEntries.size > 0  }

            if(daysList.isEmpty()) {
                view.findViewById<TextView>(R.id.emptyList).visibility = View.VISIBLE
            }

            if(currentTimeTop) {
                daysList = daysList.reversed()
            }

            val itemDayAdapter = ItemDayAdapter(daysList, navigateToEditEntry, this.requireActivity())
            val timerangeView = view.findViewById<RecyclerView>(R.id.allDays)
            timerangeView.adapter = itemDayAdapter
            timerangeView.isNestedScrollingEnabled = false
        }

        view.findViewById<TextView>(R.id.listHeader).text = header

        if(actionsAvailable) {
            view.findViewById<TextView>(R.id.newEntry).setOnClickListener{
                navigateToAddEntry()
            }
        }
    }
}