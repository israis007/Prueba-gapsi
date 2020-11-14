package com.israis007.pruebagapsi.rest

import com.google.gson.Gson
import com.israis007.pruebagapsi.BuildConfig
import com.israis007.pruebagapsi.rest.interceptors.GapsiHeaders
import com.israis007.pruebagapsi.rest.interceptors.NetworkInterceptor
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class GapsiRepository {

    companion object {
        private val gsonConverterFactory by lazy {
            GsonConverterFactory.create(Gson().newBuilder().serializeNulls().setDateFormat(BuildConfig.DATE_FORMAT).create())
        }

        private val logInterceptor: Interceptor by lazy {
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        }

        private fun createClient(): OkHttpClient {
            val builder = OkHttpClient.Builder()
            builder.addInterceptor(logInterceptor)
            builder.addInterceptor(GapsiHeaders.createIBMHeaderInterceptor())
            builder.addInterceptor(NetworkInterceptor())
            builder.callTimeout(BuildConfig.TIMEOUT_SECONDS, TimeUnit.SECONDS)
            builder.readTimeout(BuildConfig.TIMEOUT_SECONDS, TimeUnit.SECONDS)
            builder.connectTimeout(BuildConfig.TIMEOUT_SECONDS, TimeUnit.SECONDS)
            return builder.build()
        }

        fun getClient(): Retrofit =
            Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .client(createClient())
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .addConverterFactory(gsonConverterFactory)
                .build()

        fun clientAPI(): APIGapsi =
            getClient()
                .create(APIGapsi::class.java)

    }

}