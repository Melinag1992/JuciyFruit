package com.example.projectjuicyfruit.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.projectjuicyfruit.R
import com.example.projectjuicyfruit.adapters.ItemsAdapter
import com.example.projectjuicyfruit.viewmodels.PetFinderViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.dashboard_fragment.*
import javax.inject.Inject

@AndroidEntryPoint
class DashboardFragment : Fragment() {
  private val itemsAdapter = ItemsAdapter(mutableListOf())

  @Inject
  lateinit var petFinderViewModel: PetFinderViewModel

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
    petFinderViewModel.getAnimals()
    petFinderViewModel.getAnimalsLiveData().observe(viewLifecycleOwner, {
      itemsAdapter.setData(it.toMutableList())
    })
  }

  private fun inflateRecyclerView() {
    val layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
    dashboard_rv.layoutManager = layoutManager
    dashboard_rv.adapter = itemsAdapter
  }
}
