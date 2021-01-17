package com.example.projectjuicyfruit.network

import com.example.projectjuicyfruit.BuildConfig
import com.example.projectjuicyfruit.data.petfinder.PetFinderEndpoints
import com.example.projectjuicyfruit.data.petfinder.PetFinderToken
import com.example.projectjuicyfruit.utils.SharedPreferencesHelper
import com.google.gson.Gson
import okhttp3.*
import javax.inject.Inject

class PetFinderInterceptor @Inject constructor(
  private val preferences: SharedPreferencesHelper
) : Interceptor {

  override fun intercept(chain: Interceptor.Chain): Response {
    var tokenFetchSuccess = true
    var initialRequest = chain.request()

    preferences.getPetFinderAuthToken()?.let {
      initialRequest = attachAuthHeader(initialRequest, it)
    }

    var response = chain.proceed(initialRequest)

    when (response.code) {
      401, 403 ->
        synchronized(this) {
          try {
            response.close()

            val formBody = FormBody.Builder()
              .add("grant_type", "client_credentials")
              .add("client_id", BuildConfig.PetFinderClientId)
              .add("client_secret", BuildConfig.PetFinderClientSecret)
              .build()

            val refreshTokenRequest =
              initialRequest
                .newBuilder()
                .post(formBody)
                .url(ApiClient.PET_FINDER_BASE_URL + PetFinderEndpoints.TOKEN)
                .build()

            response = chain.proceed(refreshTokenRequest)

            if (response.isSuccessful) {
              tokenFetchSuccess = true
              val newToken =
                Gson().fromJson(response.body?.string(), PetFinderToken::class.java)
              preferences.savePetFinderAuthToken(newToken.accessToken)
              response.close()

            } else {
              tokenFetchSuccess = false
            }

          } catch (exception: Throwable) {
            tokenFetchSuccess = false
            exception.printStackTrace()
            response.close()
            return chain.proceed(initialRequest)

          } finally {
            if (tokenFetchSuccess) {
              preferences.getPetFinderAuthToken()?.let {
                val newRequestWithNewToken = attachAuthHeader(initialRequest, it)
                response.close()
                return chain.proceed(newRequestWithNewToken)
              }
            }

          }
        }

      400 -> {
        response.close()
        return chain.proceed(initialRequest)
      }
    }
    return response
  }

  private fun attachAuthHeader(initialRequest: Request, token: String): Request {
    val headers = Headers.Builder()
      .add(AUTH_HEADER_KEY, "Bearer $token")
      .build()

    return initialRequest.newBuilder()
      .headers(headers)
      .build()
  }

  companion object {
    const val AUTH_HEADER_KEY = "Authorization"
  }
}
