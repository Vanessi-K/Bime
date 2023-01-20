package com.example.bime.fragments

import android.content.Context
import android.content.res.Resources
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.bime.DatabaseHandler
import com.example.bime.R

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