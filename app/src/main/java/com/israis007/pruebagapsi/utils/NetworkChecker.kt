package com.israis007.pruebagapsi.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import com.israis007.pruebagapsi.GapsiApp

class NetworkChecker {
    companion object {
        fun isConnected(): Boolean {
            val connectivityManager = GapsiApp.instance.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            return if (Build.VERSION.SDK_INT < 23) {
                val networkInfo = connectivityManager.activeNetworkInfo
                if (networkInfo != null)
                    networkInfo.isConnected && (networkInfo.type == ConnectivityManager.TYPE_WIFI || networkInfo.type == ConnectivityManager.TYPE_MOBILE)
                else
                    false
            } else {
                val network = connectivityManager.activeNetwork
                if (network != null) {
                    val networkCapabilities = connectivityManager.getNetworkCapabilities(network)!!
                    (networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) || networkCapabilities.hasTransport(
                        NetworkCapabilities.TRANSPORT_WIFI))
                } else
                    false
            }
        }

        fun getNetworkType(): NetworkType {
            val connectivityManager = GapsiApp.instance.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            return if (Build.VERSION.SDK_INT < 23) {
                val networkInfo = connectivityManager.activeNetworkInfo
                if (networkInfo == null)
                    return NetworkType.NO_NETWORK
                else when (networkInfo.type) {
                    ConnectivityManager.TYPE_VPN -> NetworkType.VPN
                    ConnectivityManager.TYPE_WIFI -> NetworkType.WIFI
                    ConnectivityManager.TYPE_MOBILE -> NetworkType.MOBILE
                    else -> NetworkType.NO_NETWORK
                }
            } else {
                val network = connectivityManager.activeNetwork
                if (network == null)
                    return NetworkType.NO_NETWORK
                else {
                    val networkCapabilities = connectivityManager.getNetworkCapabilities(network)
                    when {
                        networkCapabilities == null -> return NetworkType.NO_NETWORK
                        networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_VPN) -> return NetworkType.VPN
                        networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> return NetworkType.WIFI
                        networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> return NetworkType.MOBILE
                        else -> NetworkType.NO_NETWORK
                    }
                }
            }
        }
    }

    enum class NetworkType {
        MOBILE,
        WIFI,
        VPN,
        NO_NETWORK
    }
}