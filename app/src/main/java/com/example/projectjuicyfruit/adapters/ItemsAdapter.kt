package com.example.projectjuicyfruit.adapters


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.projectjuicyfruit.R
import com.example.projectjuicyfruit.viewholders.ItemsViewHolder

var listOfItems = mutableListOf<String>()

class HorizontalAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ItemsViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_view_items, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ItemsViewHolder -> {
                if (position != RecyclerView.NO_POSITION) {
                    holder.bind(listOfItems.get(position));
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return listOfItems.size
    }

    fun setList(listOfItem: Array<String>) {
        listOfItems = listOfItem.toMutableList()
    }
}