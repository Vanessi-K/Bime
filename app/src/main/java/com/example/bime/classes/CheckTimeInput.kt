package com.example.bime.classes

import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText

class CheckTimeInput(private val timeInput: EditText, private val saveButton: Button) {

    init {
        timeInput.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(
                s: CharSequence, start: Int,
                before: Int, count: Int
            ) {
                val checkTimeContent = timeInput.text.toString()
                saveButton.isEnabled = checkTimeContent != ""
            }

            override fun afterTextChanged(p0: Editable?) {

            }
        })
    }

    fun checkTimeInput() : Double {
        return timeInput.text.toString().toDouble()
    }
}