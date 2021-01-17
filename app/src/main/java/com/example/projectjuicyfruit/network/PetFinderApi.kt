package com.example.projectjuicyfruit.network

import com.example.projectjuicyfruit.data.petfinder.Animal
import com.example.projectjuicyfruit.data.petfinder.AnimalWrapper
import com.example.projectjuicyfruit.data.petfinder.PetFinderEndpoints
import com.example.projectjuicyfruit.data.petfinder.PetFinderToken
import io.reactivex.rxjava3.core.Single
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

/**
 * DO NOT USE DIRECTLY. See [ApiClient]
 *
 * @author Jose Garcia jogarciadev@gmail.com 1/11/2021
 */
interface PetFinderApi {
  /**
   * Gets authorization token [PetFinderToken]
   *
   * @return Single<PetFinderToken>
   */
  @FormUrlEncoded
  @POST(PetFinderEndpoints.TOKEN)
  fun getPetFinderToken(
    @Field("grant_type") grantType: String = "client_credentials",
    @Field("client_id") clientId: String,
    @Field("client_secret") clientSecret: String
  ): Single<PetFinderToken>

  /**
   * Gets list of [Animal.PetDetails]s within a wrapper class [AnimalWrapper].
   * List of animals is accessed through property `animals`
   *
   * @return Single<AnimalWrapper>
   */
  @GET(PetFinderEndpoints.ANIMALS)
  fun getAnimals(): Single<AnimalWrapper>
}
