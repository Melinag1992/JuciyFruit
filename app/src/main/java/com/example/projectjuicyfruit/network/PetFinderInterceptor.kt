package com.example.projectjuicyfruit.network

import com.example.projectjuicyfruit.utils.SharedPreferencesHelper
import io.reactivex.rxjava3.core.Scheduler
import okhttp3.Headers
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import javax.inject.Inject
import javax.inject.Named

class PetFinderInterceptor(
  @Named("ioScheduler") private val scheduler: Scheduler
) : Interceptor {

  @Inject
  private lateinit var preferences: SharedPreferencesHelper

  @Inject
  private lateinit var apiClient: ApiClient

  override fun intercept(chain: Interceptor.Chain): Response {
    var tokenFetchSuccess = true
    val initialRequest = chain.request()

    val response = chain.proceed(initialRequest)

    when (response.code) {
      401, 403 ->
        synchronized(this) {
          try {
            apiClient
              .getPetFinderToken()
              .subscribeOn(scheduler)
              .subscribe(
                {
                  preferences.savePetFinderAuthToken(it.accessToken)
                }, {
                  tokenFetchSuccess = false
                  throw it
                }
              )
          } catch (exception: Throwable) {
            exception.printStackTrace()
            response.close()
            return chain.proceed(initialRequest)
          } finally {
            if (tokenFetchSuccess) {
              preferences.getPetFinderAuthToken()?.let {
                val newRequestWithNewToken = attachAuthHeader(initialRequest, it)
                response.body?.close()
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
      .addAll(initialRequest.headers)
      .add(CONST_AUTH_HEADER_KEY, "Bearer $token")
      .build()

    return initialRequest.newBuilder()
      .headers(headers)
      .build()

  }

  companion object {
    const val CONST_AUTH_HEADER_KEY = "Authorization"
  }
}