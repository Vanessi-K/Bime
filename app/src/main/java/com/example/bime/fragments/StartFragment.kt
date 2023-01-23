package com.example.bime.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.bime.DatabaseHandler
import com.example.bime.R
import com.example.bime.model.Entry
import java.time.LocalDate

class StartFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_start, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE) ?: return
        val nicknameRead = sharedPref.getString("BIME_Nickname", null)

        if(nicknameRead != null) {
            navigateToDashboard()
        } else {
            val db = DatabaseHandler(this.activity)

            // 01.01.2023
            db.insertEntry(Entry(2,0, 6.5, LocalDate.of(2023,1,1)))

            // week 02.01.2023 - 08.01.2023
            db.insertEntry(Entry(1,0, 6.0, LocalDate.of(2023,1,2)))
            db.insertEntry(Entry(2,0, 5.0, LocalDate.of(2023,1,2)))
            db.insertEntry(Entry(1,0, 5.5, LocalDate.of(2023,1,3)))
            db.insertEntry(Entry(2,0, 4.5, LocalDate.of(2023,1,3)))
            db.insertEntry(Entry(1,0, 2.0, LocalDate.of(2023,1,3)))
            db.insertEntry(Entry(1,0, 7.0, LocalDate.of(2023,1,4)))
            db.insertEntry(Entry(2,0, 3.5, LocalDate.of(2023,1,4)))
            db.insertEntry(Entry(1,0, 6.0, LocalDate.of(2023,1,5)))
            db.insertEntry(Entry(2,0, 2.5, LocalDate.of(2023,1,5)))
            db.insertEntry(Entry(1,0, 2.0, LocalDate.of(2023,1,6)))
            db.insertEntry(Entry(2,0, 6.5, LocalDate.of(2023,1,6)))
            db.insertEntry(Entry(2,0, 8.0, LocalDate.of(2023,1,7)))
            db.insertEntry(Entry(2,0, 7.0, LocalDate.of(2023,1,8)))

            // week 09.01.2023 - 15.01.2023
            db.insertEntry(Entry(1,0, 7.0, LocalDate.of(2023,1,9)))
            db.insertEntry(Entry(2,0, 3.5, LocalDate.of(2023,1,9)))
            db.insertEntry(Entry(1,0, 5.0, LocalDate.of(2023,1,10)))
            db.insertEntry(Entry(1,0, 1.5, LocalDate.of(2023,1,10)))
            db.insertEntry(Entry(2,0, 3.0, LocalDate.of(2023,1,10)))
            db.insertEntry(Entry(1,0, 2.0, LocalDate.of(2023,1,11)))
            db.insertEntry(Entry(2,0, 7.5, LocalDate.of(2023,1,11)))
            db.insertEntry(Entry(1,0, 6.5, LocalDate.of(2023,1,12)))
            db.insertEntry(Entry(2,0, 3.2, LocalDate.of(2023,1,12)))
            db.insertEntry(Entry(1,0, 1.0, LocalDate.of(2023,1,12)))
            db.insertEntry(Entry(1,0, 7.0, LocalDate.of(2023,1,13)))
            db.insertEntry(Entry(2,0, 3.5, LocalDate.of(2023,1,13)))
            db.insertEntry(Entry(2,0, 3.5, LocalDate.of(2023,1,13)))
            db.insertEntry(Entry(2,0, 8.5, LocalDate.of(2023,1,14)))
            db.insertEntry(Entry(2,0, 5.5, LocalDate.of(2023,1,15)))

            // week 16.01.2023 - 22.01.2023
            db.insertEntry(Entry(1,0, 7.5, LocalDate.of(2023,1,16)))
            db.insertEntry(Entry(2,0, 3.25, LocalDate.of(2023,1,16)))
            db.insertEntry(Entry(1,0, 6.5, LocalDate.of(2023,1,17)))
            db.insertEntry(Entry(2,0, 3.2, LocalDate.of(2023,1,17)))
            db.insertEntry(Entry(1,0, 1.0, LocalDate.of(2023,1,17)))
            db.insertEntry(Entry(1,0, 7.0, LocalDate.of(2023,1,18)))
            db.insertEntry(Entry(2,0, 3.5, LocalDate.of(2023,1,18)))
            db.insertEntry(Entry(1,0, 8.0, LocalDate.of(2023,1,19)))
            db.insertEntry(Entry(2,0, 4.0, LocalDate.of(2023,1,19)))
            db.insertEntry(Entry(2,0, 1.0, LocalDate.of(2023,1,19)))
            db.insertEntry(Entry(1,0, 2.0, LocalDate.of(2023,1,20)))
            db.insertEntry(Entry(2,0, 7.5, LocalDate.of(2023,1,20)))
            db.insertEntry(Entry(1,0, 1.0, LocalDate.of(2023,1,21)))
            db.insertEntry(Entry(2,0, 6.0, LocalDate.of(2023,1,21)))
            db.insertEntry(Entry(2,0, 5.5, LocalDate.of(2023,1,22)))

            // 23.01.2023
            db.insertEntry(Entry(2,0, 1.0, LocalDate.of(2023,1,23)))
        }

        view.findViewById<Button>(R.id.save_button).setOnClickListener() {
            val nickname = view.findViewById<EditText>(R.id.nickname_input_field).text.toString()
            if(nickname != "") {
                setNickname(nickname)
                navigateToDashboard()
            }
        }
    }

    private fun setNickname(nickname: String) {
        val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE) ?: return
        with (sharedPref.edit()) {
            putString("BIME_Nickname", nickname)
            commit()
        }
    }

    private fun navigateToDashboard() {
        val navController = findNavController()
        val action = StartFragmentDirections.actionStartScreenToDashboard()
        navController.navigate(action)
    }

}