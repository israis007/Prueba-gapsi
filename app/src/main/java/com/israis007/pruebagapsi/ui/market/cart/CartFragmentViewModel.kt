package com.israis007.pruebagapsi.ui.market.cart

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.israis007.pruebagapsi.rest.models.Items

class CartFragmentViewModel: ViewModel() {

    companion object {
        val newBuyItem = MutableLiveData<Items>()
        val countItems = MutableLiveData<Int>()
    }
}