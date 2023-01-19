package com.example.bime

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.bime.model.Category
import com.example.bime.model.Day
import com.example.bime.model.Entry

class ItemDayAdapter(
    private var data: List<Day>,
) : RecyclerView.Adapter<ItemDayViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemDayViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.item_day, parent, false)
        return ItemDayViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemDayViewHolder, position: Int) {
        val day = data[position]
        holder.display(day)
    }

    override fun getItemCount(): Int {
        return data.count()
    }
}