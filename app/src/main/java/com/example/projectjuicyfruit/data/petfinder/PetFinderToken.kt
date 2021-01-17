package com.example.projectjuicyfruit.data.petfinder

import com.google.gson.annotations.SerializedName

data class PetFinderToken(
  @SerializedName("token_type") val tokenType: String,
  @SerializedName("expires_in") val expiresIn: Long,
  @SerializedName("access_token") val accessToken: String,
)

data class PetFinderTokenRequest(
  @SerializedName("client_id") val clientId: String,
  @SerializedName("client_secret") val clientSecret: String,
)