package com.example.projectjuicyfruit.viewholders

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.projectjuicyfruit.R
import com.example.projectjuicyfruit.data.Animal
import com.squareup.picasso.Picasso

class ItemsViewHolder  (view: View) : RecyclerView.ViewHolder(view) {
    private var textViewName: TextView = view.findViewById(R.id.textview_name)
    private var imageView: ImageView = view.findViewById(R.id.image)

    fun bind(animal: Animal) {
        textViewName.text = animal.name
        Picasso.get().load(animal.imageUrl).into(imageView)
    }
}