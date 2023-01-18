package com.example.bime.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CalendarView
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.fragment.app.Fragment
import com.example.bime.DatabaseHandler
import com.example.bime.R
import java.time.LocalDate

class AddEntryFragment : Fragment() {

    var selectedCalenderDate: LocalDate = LocalDate.now()
    var selectedCategory: String = "Busy Time"

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

        val calendarView = view.findViewById<CalendarView>(R.id.calendarView)
        calendarView.setOnDateChangeListener { view, year, month, dayOfMonth ->
            selectedCalenderDate = LocalDate.of(year, month + 1, dayOfMonth)
        }

        val radioGroup = view.findViewById<RadioGroup>(R.id.radio_group)
        radioGroup.setOnCheckedChangeListener { group, checkedId ->
            val radio: RadioButton = view.findViewById(checkedId)
            selectedCategory = radio.text.toString()
        }

        var saveButton = view.findViewById<Button>(R.id.save_button)

        var checkTime = view.findViewById<EditText>(R.id.hour_input_field)

        checkTime.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(s: CharSequence, start: Int,
                                       before: Int, count: Int) {
                var checkTimeContent = checkTime.text.toString()
                saveButton.isEnabled = checkTimeContent != ""
            }

            override fun afterTextChanged(p0: Editable?) {

            }
        })

        saveButton.setOnClickListener { onEntrySave(view) }

    }

    fun onEntrySave(view: View) {
        val db = DatabaseHandler(this.activity)
        val day = selectedCalenderDate
        val time = view.findViewById<EditText>(R.id.hour_input_field).text.toString().toDouble()
        val category = if (selectedCategory === "Busy Time") 1 else 2

        db.insertEntry(category, day, time)
    }

}
