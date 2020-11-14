package com.israis007.pruebagapsi.ui.market

import android.content.Context
import android.provider.SearchRecentSuggestions
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.israis007.pruebagapsi.rest.GapsiRepository
import com.israis007.pruebagapsi.rest.models.Items
import com.israis007.pruebagapsi.utils.SuggestionProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ShopViewModel: ViewModel() {

    private val _items = MutableLiveData<List<Items>>()
    val items: LiveData<List<Items>> get() = _items
    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> get() = _loading
    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    fun searchAndSaveQuery(query: String?, context: Context){
        val sugs = SearchRecentSuggestions(context, SuggestionProvider.AUTHORITY, SuggestionProvider.MODE)
        sugs.saveRecentQuery(query, null)
        if (!query.isNullOrEmpty())
            getItems(query)
    }

    private fun getItems(query: String){
        _loading.postValue(true)
        viewModelScope.launch(Dispatchers.IO){
            try{
                val response = GapsiRepository.clientAPI().getProducts(query)
                _loading.postValue(false)
                if (response.isSuccessful && response.code() == 200){
                    _items.postValue(response.body()?.items)
                } else
                    _error.postValue("Failure get elements maybe not match")
            } catch (e: Exception){
                _error.postValue("Some wrong")
            }
        }
    }

    companion object {
        val buyItem = MutableLiveData<Items>()
        val itemsToBuy = MutableLiveData<ArrayList<Items>>()
    }

}