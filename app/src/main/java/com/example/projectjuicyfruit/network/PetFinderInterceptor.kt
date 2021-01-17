package com.example.projectjuicyfruit.network

import android.util.Log
import com.example.projectjuicyfruit.BuildConfig
import com.example.projectjuicyfruit.data.petfinder.PetFinderToken
import com.example.projectjuicyfruit.utils.SharedPreferencesHelper
import com.google.gson.Gson
import okhttp3.Headers
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import javax.inject.Inject

class PetFinderInterceptor @Inject constructor(
  private val preferences: SharedPreferencesHelper
) : Interceptor {

  override fun intercept(chain: Interceptor.Chain): Response {
    var tokenFetchSuccess = true
    val initialRequest = chain.request()

    var response = chain.proceed(initialRequest)

    when (response.code) {
      401, 403 ->
        synchronized(this) {
          try {
            Log.d("401 INTERCEPT", "intercept: INTERCEPT 401")
            response.close()

            var refreshTokenRequest =
              initialRequest
                .newBuilder()
                .header("grant_type", "client_credentials")
                .header("client_id", BuildConfig.PetFinderClientId)
                .header("client_secret", BuildConfig.PetFinderClientSecret)
                .url(ApiClient.PET_FINDER_BASE_URL + "oauth2/token")
                .build()

            refreshTokenRequest =
              attachAuthHeader(refreshTokenRequest, preferences.getPetFinderAuthToken() ?: "")

            response = chain.proceed(refreshTokenRequest)

            if (response.isSuccessful) {
              Log.d("REFRESH", "intercept: refresh call success")
              val newToken =
                Gson().fromJson(response.body?.string(), PetFinderToken::class.java)
              preferences.savePetFinderAuthToken(newToken.accessToken)
              response.close()
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
                response = chain.proceed(newRequestWithNewToken)
                return response
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
      .add(CONST_AUTH_HEADER_KEY, " Bearer $token")
      .build()

    return initialRequest.newBuilder()
      .headers(headers)
      .build()
  }

  companion object {
    const val CONST_AUTH_HEADER_KEY = "Authorization"
  }
}
