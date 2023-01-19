package com.example.bime.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.example.bime.DatabaseHandler
import com.example.bime.R
import com.example.bime.classes.CheckCalendar
import com.example.bime.classes.CheckRadioButtons
import com.example.bime.classes.CheckTimeInput

class AddEntryFragment : Fragment() {

    lateinit var calendarCheck: CheckCalendar
    lateinit var radioButtonCheck: CheckRadioButtons
    lateinit var timeCheck: CheckTimeInput

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_entry, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        calendarCheck = CheckCalendar(view.findViewById(R.id.calendarView))

        radioButtonCheck = CheckRadioButtons(view.findViewById(R.id.radio_group), view.findViewById(R.id.radio_busy_time))

        var saveButton = view.findViewById<Button>(R.id.save_button)

        timeCheck = CheckTimeInput(view.findViewById(R.id.hour_input_field), saveButton)

        saveButton.setOnClickListener { onEntrySave(view) }

    }

    fun onEntrySave(view: View) {
        val db = DatabaseHandler(this.activity)
        val day = calendarCheck.selectedDate()
        val time = timeCheck.checkTimeInput()
        val category = radioButtonCheck.selectedCategory()

        db.insertEntry(category, day, time)
    }

}
