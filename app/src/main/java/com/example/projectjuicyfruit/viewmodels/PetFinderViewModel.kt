package com.example.projectjuicyfruit.viewmodels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.projectjuicyfruit.data.petfinder.Animal
import com.example.projectjuicyfruit.network.ApiClient
import dagger.hilt.android.scopes.ActivityScoped
import io.reactivex.rxjava3.core.Scheduler
import javax.inject.Named

@ActivityScoped
class PetFinderViewModel @ViewModelInject constructor(
  private val apiClient: ApiClient,
  @Named("io-scheduler") private val ioScheduler: Scheduler,
  @Named("main-scheduler") private val mainThreadScheduler: Scheduler
) : ViewModel() {

  private val animalsLiveData: MutableLiveData<List<Animal.PetDetails>> = MutableLiveData()

  fun getAnimals() {
    apiClient.getAnimals()
      .subscribeOn(ioScheduler)
      .observeOn(mainThreadScheduler)
      .subscribe(
        {
          animalsLiveData.value = it.animals
        },
        Throwable::printStackTrace
      )
  }

  fun getAnimalsLiveData() = animalsLiveData
}