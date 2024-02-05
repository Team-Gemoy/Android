package com.synrgy.wefly.data.api

import com.synrgy.wefly.domain.PreferenceRepository
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor(
    preferenceRepository: PreferenceRepository,
) : Interceptor {
   val token = preferenceRepository.getToken()

    override fun intercept(chain: Interceptor.Chain): Response {
        val authenticatedRequest = chain.request()
            .newBuilder()
            .header(name = "Authorization", value = "Bearer $token")
            .build()

        return chain.proceed(authenticatedRequest)
    }
}