package com.example.projectjuicyfruit.network

import com.example.projectjuicyfruit.BuildConfig
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

/** This is a wrapper around the following APIs:
 *
 * [PetFinderApi]
 *
 * Use ApiClient for easier abstraction and implementation should new APIs be introduced and for
 * cleaner integration with a Dependency Injection framework.*/

class ApiClient @Inject constructor(
  private val loggingInterceptor: HttpLoggingInterceptor?,
  private val petFinderInterceptor: PetFinderInterceptor?
) {
  private var petFinderBaseUrl: String = PET_FINDER_BASE_URL
    set(value) {
      field = value
      initApis()
    }

  private lateinit var petFinderApi: PetFinderApi

  private fun initApis() {
    petFinderApi = createApi(petFinderBaseUrl, petFinderInterceptor)
  }

  private inline fun <reified T : Any> createApi(
    baseUrl: String,
    apiInterceptor: Interceptor? = null
  ): T {
    val builder = OkHttpClient.Builder()

    loggingInterceptor?.let {
      builder.addInterceptor(it)
    }

    apiInterceptor?.let {
      builder.addInterceptor(it)
    }

    val client = builder.build()

    val retrofit = Retrofit.Builder()
      .baseUrl(baseUrl)
      .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
      .addConverterFactory(GsonConverterFactory.create())
      .client(client)
      .build()

    return retrofit.create(T::class.java)
  }

  init {
    initApis()
  }

  companion object {
    const val PET_FINDER_BASE_URL: String = "https://api.petfinder.com/v2/"
  }


  /**
   * Gets new auth token
   *
   * See [PetFinderApi.getPetFinderToken]
   */
  fun getPetFinderToken() =
    petFinderApi.getPetFinderToken(
      clientId = BuildConfig.PetFinderClientId,
      clientSecret = BuildConfig.PetFinderClientSecret
    )

  /**
   * Gets list of Animals
   *
   * See [PetFinderApi.getAnimals]
   * */
  fun getAnimals() = petFinderApi.getAnimals()

//  /**
//   * Gets [Volume]s by author
//   *
//   * See [GoogleBooksApi.searchByAuthor]
//   */
//  fun searchByAuthor(author: String) = googleBooksApi.searchByAuthor(author)
//
//  /**
//   * Gets [Volume]s by ISBN
//   *
//   * See [GoogleBooksApi.searchByISBN]
//   */
//  fun searchByISBN(isbn: String) = googleBooksApi.searchByISBN(isbn)
//
//  /**
//   * Gets a [BestSellersList] by name
//   *
//   * See [NYTimesApi.getBestSellersList]
//   */
//  fun getBestSellersList(listName: String) =
//    nyTimesApi.getBestSellersList(NYTIMES_API_KEY, listName)
//
//  /**
//   * Gets a list of the top 5 [BestSellersBook] from each [TimesList]
//   *
//   * See [NYTimesApi.getTopFiveBestSellers]
//   */
//  fun getBestSellersOverview(): Single<List<BestSellersOverviewList>> {
//    return nyTimesApi.getTopFiveBestSellers(NYTIMES_API_KEY).map { it.results.lists }
//  }
//
//  /**
//   * Gets a list of all [TimesListName]s
//   *
//   * See [NYTimesApi.getBestSellersListNames]
//   */
//  fun getBestSellersListNames() = nyTimesApi.getBestSellersListNames(NYTIMES_API_KEY)
}