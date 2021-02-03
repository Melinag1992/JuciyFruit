package com.example.projectjuicyfruit.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.projectjuicyfruit.R
import com.example.projectjuicyfruit.data.petfinder.Animal.PetDetails
import com.example.projectjuicyfruit.viewholders.ItemsViewHolder
import kotlinx.android.synthetic.main.layout_card_item.view.*

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
                    holder.bind(items[position],holder)

                    // added the click here vs. on Bind because we have access to the navcontroller
                    holder.itemView.view_details_btn.setOnClickListener{ view ->
                        view.findNavController().navigate(R.id.next_action, null)
                    }
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun setData(listOfItem: MutableList<PetDetails>) {
        items = listOfItem
        notifyDataSetChanged()
    }
}
