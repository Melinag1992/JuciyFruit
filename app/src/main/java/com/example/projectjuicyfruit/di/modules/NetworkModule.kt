package com.example.projectjuicyfruit.di.modules

import android.content.Context
import com.example.projectjuicyfruit.network.ApiClient
import com.example.projectjuicyfruit.network.PetFinderInterceptor
import com.example.projectjuicyfruit.utils.SharedPreferencesHelper
import com.example.projectjuicyfruit.viewmodels.PetFinderViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.schedulers.Schedulers
import okhttp3.logging.HttpLoggingInterceptor
import javax.inject.Named

@InstallIn(ApplicationComponent::class)
@Module
object NetworkModule {

  @Provides
  @Named("io-scheduler")
  fun provideIOScheduler(): Scheduler = Schedulers.io()

  @Provides
  @Named("main-scheduler")
  fun provideMainThreadScheduler(): Scheduler = AndroidSchedulers.mainThread()

  @Provides
  fun provideLoggingInterceptor(): HttpLoggingInterceptor = HttpLoggingInterceptor()
    .apply { level = HttpLoggingInterceptor.Level.BODY }

  @Provides
  fun providesApiClient(
    loggingInterceptor: HttpLoggingInterceptor,
    petFinderInterceptor: PetFinderInterceptor
  ): ApiClient = ApiClient(loggingInterceptor, petFinderInterceptor)

  @Provides
  fun providesPetFinderInterceptor(
    preferences: SharedPreferencesHelper,
  ): PetFinderInterceptor = PetFinderInterceptor(preferences)

  @Provides
  fun providesSharedPreferences(@ApplicationContext context: Context): SharedPreferencesHelper =
    SharedPreferencesHelper(context)

  @Provides
  fun providePetFinderViewModel(
    apiClient: ApiClient,
    @Named("io-scheduler") ioScheduler: Scheduler,
    @Named("main-scheduler") mainThreadScheduler: Scheduler
  ): PetFinderViewModel = PetFinderViewModel(apiClient, ioScheduler, mainThreadScheduler)
}
