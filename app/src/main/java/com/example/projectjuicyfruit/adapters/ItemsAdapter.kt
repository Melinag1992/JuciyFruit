package com.example.projectjuicyfruit.adapters


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.projectjuicyfruit.R
import com.example.projectjuicyfruit.data.petfinder.Pet.PetDetails
import com.example.projectjuicyfruit.viewholders.ItemsViewHolder


class ItemsAdapter(private var items: MutableList<PetDetails>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ItemsViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_view_items, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ItemsViewHolder -> {
                if (position != RecyclerView.NO_POSITION) {
                    holder.bind(items[position])
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun setData(listOfItem: MutableList<PetDetails>) {
        items = listOfItem
    }
}