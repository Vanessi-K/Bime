package com.example.bime.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.bime.DatabaseHandler
import com.example.bime.R
import com.example.bime.classes.CheckCalendar
import com.example.bime.classes.CheckRadioButtons
import com.example.bime.classes.CheckTimeInput
import com.google.android.material.bottomappbar.BottomAppBar
import java.time.LocalDate

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

        val bottomAppBar : BottomAppBar = view.findViewById(R.id.bottomAppBar);
        bottomAppBar.setOnMenuItemClickListener() {
            when (it.itemId) {
                R.id.dashboard -> {
                    navigateToDashboard()
                    true
                }
                R.id.weeklyOverview -> {
                    navigateToWeekly()
                    true
                }
                else -> false
            }
        }

        calendarCheck = CheckCalendar(view.findViewById(R.id.calendarView), LocalDate.now())

        radioButtonCheck = CheckRadioButtons(view.findViewById(R.id.radio_group), view.findViewById(R.id.radio_busy_time))

        var saveButton = view.findViewById<Button>(R.id.save_button)

        timeCheck = CheckTimeInput(view.findViewById(R.id.hour_input_field), saveButton)

        saveButton.setOnClickListener { onEntrySave(view) }

        view.findViewById<Button>(R.id.cancel_button).setOnClickListener { goBack() }
        view.findViewById<ImageView>(R.id.back_arrow).setOnClickListener { goBack() }
        view.findViewById<ImageView>(R.id.cancel_icon).setOnClickListener { goBack() }
    }

    fun onEntrySave(view: View) {
        val db = DatabaseHandler(this.activity)
        val day = calendarCheck.selectedDate()
        val time = timeCheck.checkTimeInput()
        val category = radioButtonCheck.selectedCategory()

        db.insertEntry(category, day, time)

        goBack()
    }

    fun goBack() {
        findNavController().popBackStack()
    }

    private fun navigateToDashboard() {
        val navController = findNavController()
        val action = AddEntryFragmentDirections.actionAddEntryToDashboard()
        navController.navigate(action)
    }

    private fun navigateToWeekly() {
        val navController = findNavController()
        val action = AddEntryFragmentDirections.actionAddEntryToWeeklyOverview(LocalDate.now().toString())
        navController.navigate(action)
    }

}
