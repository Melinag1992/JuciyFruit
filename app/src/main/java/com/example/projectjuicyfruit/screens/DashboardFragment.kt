package com.example.projectjuicyfruit.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.projectjuicyfruit.R
import com.example.projectjuicyfruit.adapters.ItemsAdapter
import com.example.projectjuicyfruit.data.Animal
import kotlinx.android.synthetic.main.dashboard_fragment.*

class DashboardFragment : Fragment() {
  private var items: MutableList<Animal>

  init {
    items = MutableList(5) {
      Animal(
        "Animal ${it + 1}",
        "https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Fwww.guidedogs.org%2Fwp-content%2Fuploads%2F2018%2F01%2FMobile.jpg&f=1&nofb=1",
        it + 1
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
    text_view_welcome_msg.setOnClickListener {
      findNavController(this).navigate(R.id.next_action, null)
    }
  }

  private fun inflateRecyclerView() {
    val layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
    horizontal_recview.layoutManager = layoutManager
    horizontal_recview.adapter = ItemsAdapter(items)
  }
}