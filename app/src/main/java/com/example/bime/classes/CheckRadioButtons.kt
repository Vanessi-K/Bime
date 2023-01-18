package com.example.bime.classes

import android.widget.RadioButton
import android.widget.RadioGroup

class CheckRadioButtons(private val radioGroup: RadioGroup, private val radio: RadioButton) {

    private var selectedCategory: Int = 1

    init {
        radioGroup.setOnCheckedChangeListener { group, checkedId ->
            selectedCategory = if (radio.id == checkedId) {
                1
            } else {
                2
            }
        }
    }

    fun selectedCategory(): Int {
        return selectedCategory
    }
}
