package com.example.bime

import android.graphics.Color
import android.view.View
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.bime.model.Category
import com.example.bime.model.Day
import com.example.bime.model.Entry

class ItemDayViewHolder(
    private val root: View
): RecyclerView.ViewHolder(root) {
    private val item: ConstraintLayout = root as ConstraintLayout

    fun display(day: Day) {
        val dayLabel = item.findViewById<TextView>(R.id.dayLabel)
        dayLabel.text = day.day.dayOfMonth.toString()

        val monthLabel = item.findViewById<TextView>(R.id.monthLabel)
        monthLabel.text = day.day.month.toString()

        val entryAdapter = ItemEntryAdapter(day.listOfEntries, this::openEntry);
        val entryView = root.findViewById<RecyclerView>(R.id.dayList)
        entryView.adapter = entryAdapter
    }

    fun openEntry(entry: Entry) {
        println("Navigate to $entry")
    }

}