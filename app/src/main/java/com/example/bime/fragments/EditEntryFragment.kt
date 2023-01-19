package com.example.bime.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.RadioGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.bime.DatabaseHandler
import com.example.bime.R
import com.example.bime.classes.CheckCalendar
import com.example.bime.classes.CheckRadioButtons
import com.example.bime.classes.CheckTimeInput

class EditEntryFragment : Fragment() {

    private val args: EditEntryFragmentArgs by navArgs()
    private var passedId = 0

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
        return inflater.inflate(R.layout.fragment_edit_entry, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        passedId = args.id

        val db = DatabaseHandler(this.activity)

        calendarCheck = CheckCalendar(view.findViewById(R.id.calendarView))

        radioButtonCheck = CheckRadioButtons(
            view.findViewById(R.id.radio_group),
            view.findViewById(R.id.radio_busy_time)
        )

        var saveButton = view.findViewById<Button>(R.id.save_button)
        var deleteButton = view.findViewById<Button>(R.id.delete_button)

        timeCheck = CheckTimeInput(view.findViewById(R.id.hour_input_field), saveButton)

        val entry = db.getEntryById(passedId)

        if (entry != null) {
            view.findViewById<EditText>(R.id.hour_input_field).setText(entry.time.toString())

            if (entry.category == 1) {
                view.findViewById<RadioGroup>(R.id.radio_group).check(R.id.radio_busy_time)
            } else {
                view.findViewById<RadioGroup>(R.id.radio_group).check(R.id.radio_free_time)
            }

            calendarCheck.setDate(entry.day)
        }

        saveButton.setOnClickListener {
            onEntryUpgrade()
            goBack()
        }
        deleteButton.setOnClickListener {
            onEntryDelete()
            goBack()
        }

        view.findViewById<ImageView>(R.id.back_arrow).setOnClickListener { goBack() }
        view.findViewById<ImageView>(R.id.cancel_icon).setOnClickListener { goBack() }
    }

    fun onEntryUpgrade() {
        val db = DatabaseHandler(this.activity)
        val day = calendarCheck.selectedDate()
        val time = timeCheck.checkTimeInput()
        val category = radioButtonCheck.selectedCategory()

        db.updateEntry(passedId, category, day, time)

    }

    fun onEntryDelete() {
        val db = DatabaseHandler(this.activity)

        db.deleteEntry(passedId)
    }

    fun goBack() {
        findNavController().popBackStack()
    }
}