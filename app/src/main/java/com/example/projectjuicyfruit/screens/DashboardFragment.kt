package com.example.projectjuicyfruit.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.projectjuicyfruit.R
import com.example.projectjuicyfruit.adapters.ItemsAdapter
import com.example.projectjuicyfruit.data.petfinder.Animal.PetDetails
import com.example.projectjuicyfruit.network.ApiClient
import com.example.projectjuicyfruit.utils.SharedPreferencesHelper
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.rxjava3.core.Scheduler
import kotlinx.android.synthetic.main.dashboard_fragment.*
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

  private var items: MutableList<PetDetails>
  private val itemsAdapter = ItemsAdapter(mutableListOf())

  init {
    items = MutableList(10) {
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
    val layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
    horizontal_recview.layoutManager = layoutManager
    horizontal_recview.adapter = itemsAdapter
  }
}
