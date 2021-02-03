package com.example.projectjuicyfruit.viewholders

import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.RecyclerView
import com.example.projectjuicyfruit.R
import com.example.projectjuicyfruit.data.petfinder.Animal
import com.squareup.picasso.Picasso

class ItemsViewHolder(view: View) : RecyclerView.ViewHolder(view) {
  private var textViewName: TextView = view.findViewById(R.id.textview_name)
  private var imageView: ImageView = view.findViewById(R.id.image)
  private var detailsBtn: Button = view.findViewById(R.id.view_details_btn)
//  private var navController = view.findNavController()

  fun bind(animal: Animal.PetDetails, holder: ItemsViewHolder) {
    textViewName.text = animal.name
    animal.photos?.let {
      if (it.isNotEmpty()) Picasso.get().load(it[0].medium).into(imageView)
    }
  }
}
