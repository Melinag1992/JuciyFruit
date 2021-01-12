package com.example.projectjuicyfruit.network

import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/** This is a wrapper around the following APIs:
 *
 * [PetFinderApi]
 *
 * Use ApiClient for easier abstraction and implementation should new APIs be introduced and for
 * cleaner integration with a Dependency Injection framework.*/

class ApiClient(
  private var loggingInterceptor: HttpLoggingInterceptor?
) {
  private var petFinderBaseUrl: String = PET_FINDER_BASE_URL
    set(value) {
      field = value
      initPetFinderApi()
    }

  private lateinit var petFinderApi: PetFinderApi

  private fun initPetFinderApi() {
    petFinderApi = createApi(petFinderBaseUrl)
  }

  private inline fun <reified T : Any> createApi(baseUrl: String): T {
    val builder = OkHttpClient.Builder()

    loggingInterceptor?.let {
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
    initPetFinderApi()
  }

  companion object {
    const val PET_FINDER_BASE_URL: String = "https://api.petfinder.com/v2/"
  }

  /**
   * Gets [Dog]s
   *
   * See [PetFinderApi.getDogs]
   */
//  fun getDogs() = dogsApi.getDogs()

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