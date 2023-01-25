package com.example.bime.recycler.entry

import android.graphics.Color
import android.view.View
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.bime.R
import com.example.bime.model.Category
import com.example.bime.model.Entry

class ItemEntryViewHolder(
    root: View,
    private val onClick: (Entry) -> Unit,
    private val onHold: (Entry) -> Unit
): RecyclerView.ViewHolder(root) {
    private val item: ConstraintLayout = root as ConstraintLayout

    fun display(entry: Entry, category: Category) {
        val categoryLabel = item.findViewById<TextView>(R.id.categoryLabel)

        categoryLabel.text = category.name

        val entryTime = item.findViewById<TextView>(R.id.entryTime)
        entryTime.text = entry.time.toString() +  " h"

        val highlightColour = item.findViewById<View>(R.id.highlightColour)
        highlightColour.setBackgroundColor(Color.parseColor(category.colour))

        item.setOnClickListener {
            onClick(entry)
        }

        item.setOnLongClickListener{
            onHold(entry)
            true
        }
    }
}