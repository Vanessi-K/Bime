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

class TimerangeListFragment(val header: String = "", val timerange: Timerange? = null) : Fragment() {

    private var actionsAvailable: Boolean = false
    private lateinit var navigateToAddEntry: () -> Unit
    private lateinit var navigateToEditEntry: (Entry) -> Unit

    constructor(header: String, timerange: Timerange, navigateToAddEntry: () -> Unit, navigateToEditEntry: (Entry) -> Unit) : this(header, timerange) {
        this.navigateToAddEntry = navigateToAddEntry
        this.navigateToEditEntry = navigateToEditEntry
        this.actionsAvailable = true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_timerange_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if(timerange != null && actionsAvailable) {
            val daysList = timerange.listOfDaysInRange.filter { it.listOfEntries.size > 0  }
            val itemDayAdapter = ItemDayAdapter(daysList, navigateToEditEntry)
            val timerangeView = view.findViewById<RecyclerView>(R.id.allDays)
            timerangeView.adapter = itemDayAdapter

            val numberOfEntries = daysList.map { it.listOfEntries.size }.sum()
            println("Number of entries: $numberOfEntries")
            val params: ViewGroup.LayoutParams = timerangeView.getLayoutParams()
            params.height = (numberOfEntries * 200) + 400
            timerangeView.layoutParams = params
        }

        view.findViewById<TextView>(R.id.listHeader).text = header

        if(actionsAvailable) {
            view.findViewById<TextView>(R.id.newEntry).setOnClickListener{
                println("Navigating to add entry")
                navigateToAddEntry()
            }
        }
    }
}