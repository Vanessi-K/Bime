package com.example.bime.recycler.day

import android.app.Activity
import android.view.View
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.bime.DatabaseHandler
import com.example.bime.recycler.entry.ItemEntryAdapter
import com.example.bime.R
import com.example.bime.classes.DeleteAlert
import com.example.bime.model.Day
import com.example.bime.model.Entry
import java.time.format.TextStyle
import java.util.*

class ItemDayViewHolder(
    private val root: View,
    private val onClickEntry: (Entry) -> Unit,
    private val activity: Activity
): RecyclerView.ViewHolder(root) {
    private val item: ConstraintLayout = root as ConstraintLayout
    private lateinit var entryAdapter : ItemEntryAdapter

    fun display(day: Day) {
        val dayLabel = item.findViewById<TextView>(R.id.dayLabel)
        dayLabel.text = day.day.dayOfMonth.toString()

        val weekdayMonthLabel = item.findViewById<TextView>(R.id.weekdayMonthLabel)
        val weekday = day.day.dayOfWeek.getDisplayName(TextStyle.SHORT, Locale.US).toString()
        val month = day.day.month.getDisplayName(TextStyle.SHORT, Locale.US).toString().uppercase(Locale.ROOT)
        weekdayMonthLabel.text = "$weekday - $month"

        entryAdapter = ItemEntryAdapter(day.listOfEntries, onClickEntry, this::deleteEntry)
        val entryView = root.findViewById<RecyclerView>(R.id.dayList)
        entryView.adapter = entryAdapter
    }

    private fun deleteEntry(entry: Entry) {
        val db = DatabaseHandler(activity)
        DeleteAlert(activity) {
            db.deleteEntry(entry)
            activity.recreate()
        }
    }
}