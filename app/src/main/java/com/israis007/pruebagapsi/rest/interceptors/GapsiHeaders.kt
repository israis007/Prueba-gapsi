package com.israis007.pruebagapsi.rest.interceptors

import com.israis007.pruebagapsi.BuildConfig
import okhttp3.Interceptor

private const val HEADER_IBM = "X-IBM-Client-Id"

class GapsiHeaders {

    companion object {
        fun createIBMHeaderInterceptor(): Interceptor {
            return Interceptor {chain ->
                val reqBuilder = chain.request().newBuilder()

                reqBuilder.addHeader(HEADER_IBM, BuildConfig.IBM_KEY)

                chain.proceed(reqBuilder.build())
            }
        }
    }

}