package com.example.projectjuicyfruit.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.projectjuicyfruit.R
import com.example.projectjuicyfruit.viewholders.CategoriesViewHolder

var listOfKids = mutableListOf<String>()

class RecyclerAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return CategoriesViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_view_categories, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is CategoriesViewHolder -> {
                if (position != RecyclerView.NO_POSITION) {
                    holder.bind(listOfKids.get(position), position);
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return listOfKids.size
    }

    fun setList(listOfItems: Array<String>) {
        listOfKids = listOfItems.toMutableList()
    }

}