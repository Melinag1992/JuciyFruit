package com.example.projectjuicyfruit.data.petfinder

import com.google.gson.annotations.SerializedName

class Pet {
  data class PetDetails(
    val id: Int,
    val name: String,
    val imageUrl: String,
    @SerializedName("organization_id") val organizationId: String?,
    @SerializedName("url") val pageUrl: String?,
    @SerializedName("coat") val coatLength: String?,
    val age: Int?,
    val gender: String?,
    val type: String?,
    val species: String?,
    val size: String?,
    val description: String?,
    val contact: PetContact?,
    val breeds: List<PetBreed>?,
    val photos: List<PetPhoto>?,
    val videos: List<PetVideo>?,
    val status: String?,
  ) {
    constructor(id: Int, name: String, imageUrl: String) :
        this(
          id,
          name,
          imageUrl,
          null,
          null,
          null,
          null,
          null,
          null,
          null,
          null,
          null,
          null,
          null,
          null,
          null,
          null
        )
  }

  data class PetBreed(
    val primary: String,
    val secondary: String,
    val mixed: Boolean,
    val unknown: Boolean,
  )

  data class PetVideo(
    val embed: String
  )

  data class PetPhoto(
    val small: String,
    val medium: String,
    val large: String,
    val full: String,
  )

  data class PetContact(
    val email: String,
    val phone: String,
  )
}
