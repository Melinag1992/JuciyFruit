package com.example.projectjuicyfruit.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.projectjuicyfruit.R
import com.example.projectjuicyfruit.adapters.ItemsAdapter
import com.example.projectjuicyfruit.data.petfinder.Pet.PetDetails
import kotlinx.android.synthetic.main.dashboard_fragment.*

class DashboardFragment : Fragment() {
  private var items: MutableList<PetDetails>

  init {
    items = MutableList(59) {
      PetDetails(
        1,
        "Pet Number ${it + 1}",
        "https://images.dog.ceo/breeds/ridgeback-rhodesian/n02087394_138.jpg"
      )
    }
  }

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    return inflater.inflate(R.layout.dashboard_fragment, container, false)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    inflateRecyclerView()
  }

  private fun inflateRecyclerView() {
    val layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
    horizontal_recview.layoutManager = layoutManager
    horizontal_recview.adapter = ItemsAdapter(items)
  }
}