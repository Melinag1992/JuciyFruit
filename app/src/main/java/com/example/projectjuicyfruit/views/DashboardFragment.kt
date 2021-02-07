package com.example.projectjuicyfruit.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.projectjuicyfruit.R
import com.example.projectjuicyfruit.adapters.ItemsAdapter
import com.example.projectjuicyfruit.databinding.DashboardFragmentBinding
import com.example.projectjuicyfruit.databinding.DashboardFragmentBinding.inflate
import com.example.projectjuicyfruit.network.ApiClient
import com.example.projectjuicyfruit.utils.SharedPreferencesHelper
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.rxjava3.core.Scheduler
import javax.inject.Inject
import javax.inject.Named

@AndroidEntryPoint
class DashboardFragment : Fragment() {
  @Inject
  lateinit var apiClient: ApiClient

  @Inject
  @Named("io-scheduler")
  lateinit var scheduler: Scheduler

  @Inject
  @Named("main-thread-scheduler")
  lateinit var mainThreadScheduler: Scheduler

  @Inject
  lateinit var preferences: SharedPreferencesHelper

  private val itemsAdapter = ItemsAdapter(mutableListOf())
  private var _binding: DashboardFragmentBinding? = null
  private val binding get() = _binding!!


  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    _binding = inflate(inflater, container, false)
    return binding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    inflateRecyclerView()
    apiClient.getAnimals()
      .subscribeOn(scheduler)
      .observeOn(mainThreadScheduler)
      .subscribe(
        {
          itemsAdapter.setData(it.animals.toMutableList())
        },
        Throwable::printStackTrace
      )
  }

  private fun inflateRecyclerView() {
    val layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
    binding.dashboardRv.layoutManager = layoutManager
    binding.dashboardRv.adapter = itemsAdapter
  }
  
  override fun onDestroyView() {
    super.onDestroyView()
    _binding = null
  }
}
