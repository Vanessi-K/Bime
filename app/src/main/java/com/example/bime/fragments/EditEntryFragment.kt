package com.example.bime.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CalendarView
import android.widget.EditText
import android.widget.RadioGroup
import androidx.fragment.app.Fragment
import com.example.bime.DatabaseHandler
import com.example.bime.R
import java.lang.Long.getLong
import java.time.LocalDate
import java.time.ZoneId
import java.time.ZoneOffset

class EditEntryFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_edit_entry, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val db = DatabaseHandler(this.activity)

        val entry = db.getEntryById(5)
        if (entry != null) {
            view.findViewById<EditText>(R.id.hour_input_field).setText(entry.time.toString())

            var selectedCategory = entry.category
            if (selectedCategory == 1) {
                view.findViewById<RadioGroup>(R.id.radio_group).check(R.id.radio_busy_time)
            } else {
                view.findViewById<RadioGroup>(R.id.radio_group).check(R.id.radio_free_time)
            }

       view.findViewById<CalendarView>(R.id.calendarView).date = entry.day.atStartOfDay().toInstant(
                ZoneOffset.UTC).toEpochMilli()
        }
    }

}