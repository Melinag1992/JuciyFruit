package com.example.projectjuicyfruit.viewholders

import androidx.recyclerview.widget.RecyclerView
import com.example.projectjuicyfruit.R
import com.example.projectjuicyfruit.data.petfinder.Animal
import com.example.projectjuicyfruit.databinding.ItemViewItemsBinding
import com.squareup.picasso.Picasso

class ItemsViewHolder(private val itemsBinding: ItemViewItemsBinding) : RecyclerView.ViewHolder(itemsBinding.root) {
  private val imageView = itemsBinding.cardItem.image

  fun bind(animal: Animal.PetDetails) {
    itemsBinding.cardItem.textviewName.text = animal.name
    animal.photos?.let {
      if (it.isNotEmpty())
        Picasso.get().load(it[0].medium).into(imageView)
      else
        Picasso.get().load(R.drawable.odiegarfield).into(imageView)
    }
  }
}
