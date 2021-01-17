package com.example.projectjuicyfruit.utils

import android.content.Context
import com.example.projectjuicyfruit.utils.extensions.getDefaultSharedPreferences
import javax.inject.Inject

class SharedPreferencesHelper @Inject constructor(context: Context) {
  private val preferences = context.getDefaultSharedPreferences(Context.MODE_PRIVATE)

  fun getPetFinderAuthToken() = preferences.getString(PREF_KEY_PET_FINDER_AUTH_TOKEN, null)

  fun savePetFinderAuthToken(token: String) =
    preferences.edit()
      .putString(PREF_KEY_PET_FINDER_AUTH_TOKEN, token)
      .apply()

  companion object {
    const val DEFAULT_PREF_FILE_NAME = "default-preferences"
    const val PREF_KEY_PET_FINDER_AUTH_TOKEN = "pet-finder-auth-header"
  }
}