package com.israis007.pruebagapsi.ui.market.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.israis007.pruebagapsi.rest.models.Items

class ShopFragmentViewModel : ViewModel() {

    companion object {
        val items = MutableLiveData<List<Items>>()
    }

}