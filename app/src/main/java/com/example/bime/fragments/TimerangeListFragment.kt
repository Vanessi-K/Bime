package com.example.bime.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.bime.DatabaseHandler
import com.example.bime.ItemDayAdapter
import com.example.bime.R
import com.example.bime.model.Timerange
import com.github.mikephil.charting.charts.PieChart
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.time.LocalDate

class TimerangeListFragment(val header: String = "", val timerange: Timerange? = null) : Fragment() {

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

        if(timerange != null) {
            var itemDayAdapter = ItemDayAdapter(timerange.listOfDaysInRange.filter { it.listOfEntries.size > 0  });
            val timerangeView = view.findViewById<RecyclerView>(R.id.allDays)
            timerangeView.adapter = itemDayAdapter
        }

        view.findViewById<TextView>(R.id.listHeader).text = header
    }
}