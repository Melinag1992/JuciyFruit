package com.example.projectjuicyfruit.viewholders

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.projectjuicyfruit.R
import com.example.projectjuicyfruit.adapters.HorizontalAdapter

class CategoriesViewHolder (view: View) : RecyclerView.ViewHolder(view) {
    var horizontalScrollView = view.findViewById<RecyclerView>(R.id.horizontal_recview)
    var header = view.findViewById<TextView>(R.id.cat_title)
        val gridLayout = LinearLayoutManager(view.context, LinearLayoutManager.HORIZONTAL, false)
    var  listOfItems = arrayOf("item 1","item 2","item 3","item 4","item 5","item 6")

    // Call the views
    fun bind(title: String, position: Int) {
      header.text = "Category ${position+1}"
        // Inflating recview
        var adapter = HorizontalAdapter()
        horizontalScrollView.visibility = View.VISIBLE
        horizontalScrollView.layoutManager = gridLayout
        horizontalScrollView.adapter = adapter

        adapter.setList(listOfItems)

        // assign values to views
    }

}