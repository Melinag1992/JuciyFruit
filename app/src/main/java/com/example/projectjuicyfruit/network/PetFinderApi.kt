package com.example.projectjuicyfruit.network

import com.example.projectjuicyfruit.data.petfinder.PetFinderToken
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * DO NOT USE DIRECTLY. See [ApiClient]
 *
 * @author Jose Garcia jogarciadev@gmail.com 1/11/2021
 */
interface PetFinderApi {
  /**
   * Gets a best sellers list by name
   *
   * "https://api.nytimes.com/svc/books/v3/lists/overview.json?api-key=3f015948418c4a2383be12847ff477f1
   */
  @GET("oauth2/token?grant_type=client_credentials&client_id={client_id}&client_secret={client_secret}")
  fun getPetFinderToken(
    @Path("client_id") clientId: String,
    @Path("client_secret") clientSecret: String,
  ): Single<PetFinderToken>

//  /**
//   * Gets a best sellers list by name
//   *
//   * "https://api.nytimes.com/svc/books/v3/lists/overview.json?api-key=3f015948418c4a2383be12847ff477f1
//   */
//  @GET("lists.json")
//  fun getDogs(
//    @Query("api-key") apiKey: String,
//    @Query("list") listName: String
//  ): Single<BestSellersListResponse>


}

