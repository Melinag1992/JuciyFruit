package com.example.projectjuicyfruit.network

import com.example.projectjuicyfruit.data.petfinder.PetFinderToken
import io.reactivex.rxjava3.core.Single
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

/**
 * DO NOT USE DIRECTLY. See [ApiClient]
 *
 * @author Jose Garcia jogarciadev@gmail.com 1/11/2021
 */
interface PetFinderApi {
  /**
   * Gets authorization token
   *
   * @return Single<PetFinderToken>
   */
  @FormUrlEncoded
  @POST("oauth2/token")
  fun getPetFinderToken(
    @Field("grant_type") grantType: String = "client_credentials",
    @Field("client_id") clientId: String,
    @Field("client_secret") clientSecret: String
  ): Single<PetFinderToken>
}
