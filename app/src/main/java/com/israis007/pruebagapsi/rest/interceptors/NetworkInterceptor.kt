package com.israis007.pruebagapsi.rest.interceptors

import com.israis007.pruebagapsi.rest.exceptions.NotNetworkAvalaibleException
import com.israis007.pruebagapsi.utils.NetworkChecker
import okhttp3.Interceptor
import okhttp3.Response

class NetworkInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        if (!NetworkChecker.isConnected())
            throw NotNetworkAvalaibleException("Not Connected")
        return chain.proceed(request)
    }
}