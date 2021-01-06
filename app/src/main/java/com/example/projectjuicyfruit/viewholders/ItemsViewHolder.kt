package com.example.projectjuicyfruit.viewholders

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.projectjuicyfruit.R
import com.example.projectjuicyfruit.listOfItems
import com.example.projectjuicyfruit.rec_view

class ItemsViewHolder  (view: View) : RecyclerView.ViewHolder(view) {
    var textViewName = view.findViewById<TextView>(R.id.textview_name)
    fun bind(name: String) {
        textViewName.text = name
    }




}