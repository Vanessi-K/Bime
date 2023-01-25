package com.example.bime.recycler.entry

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.bime.R
import com.example.bime.model.Entry

class ItemEntryAdapter(
    var data: List<Entry>,
    private val onClick: (Entry) -> Unit,
    private val onHold: (Entry) -> Unit
) : RecyclerView.Adapter<ItemEntryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemEntryViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.item_entry, parent, false)
        return ItemEntryViewHolder(view, onClick, onHold)
    }

    override fun onBindViewHolder(holder: ItemEntryViewHolder, position: Int) {
        val entry = data[position]
        val category = entry.getCategory()
        if (category != null) holder.display(entry, category)
    }

    override fun getItemCount(): Int {
        return data.count()
    }
}