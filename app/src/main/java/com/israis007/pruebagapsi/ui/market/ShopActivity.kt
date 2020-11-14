package com.israis007.pruebagapsi.ui.market

import android.app.SearchManager
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.israis007.pruebagapsi.R
import com.israis007.pruebagapsi.ui.market.cart.CartFragment
import com.israis007.pruebagapsi.ui.market.cart.CartFragmentViewModel
import com.israis007.pruebagapsi.ui.market.search.ShopFragment
import com.israis007.pruebagapsi.ui.market.search.ShopFragmentViewModel
import kotlinx.android.synthetic.main.appbarlayout.*
import kotlinx.android.synthetic.main.appbarlayout.view.*
import kotlinx.android.synthetic.main.shop_activity.*

class ShopActivity : AppCompatActivity() {

    private lateinit var viewModel: ShopViewModel
    private lateinit var shopFragment: ShopFragment
    private lateinit var cartFragment: CartFragment
    private var showCart = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.shop_activity)

        viewModel = ViewModelProvider(this)[ShopViewModel::class.java]
        shopFragment = ShopFragment.newInstance()
        cartFragment = CartFragment.newInstance()

        shop_appbarlayout.searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                viewModel.searchAndSaveQuery(shop_appbarlayout.searchView.query.toString(), this@ShopActivity)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean = false
        })

        viewModel.items.observe(this, Observer {
            val items = it ?: return@Observer
            ShopFragmentViewModel.items.postValue(items)
        })

        viewModel.loading.observe(this, Observer {
            val show = it ?: return@Observer
            progressBar.visibility = if (show) View.VISIBLE else View.GONE
        })

        ShopViewModel.buyItem.observe(this, Observer {
            val item = it ?: return@Observer
            CartFragmentViewModel.newBuyItem.postValue(item)
        })

        CartFragmentViewModel.countItems.observe(this, Observer {
            val count = it ?: return@Observer
            if (count > 0) {
                shop_appbarlayout.counter.apply {
                    visibility = View.VISIBLE
                    text = count.toString()
                }
            } else
                shop_appbarlayout.counter.apply {
                    visibility = View.GONE
                    text = ""
                }
        })

        shop_appbarlayout.shopIcon.setOnClickListener {
            changeFragment(if (!showCart) FragmentType.CART else FragmentType.SEARCH)
            showCart = !showCart
        }


        if (savedInstanceState == null) {
            changeFragment(FragmentType.SEARCH)
        }
    }

    private fun changeFragment(fragmentType: FragmentType){
        supportFragmentManager.beginTransaction()
            .replace(R.id.frameLayout, when(fragmentType){
                FragmentType.CART -> cartFragment
                FragmentType.SEARCH -> shopFragment
            })
            .commitNow()
    }


    enum class FragmentType{
        CART,
        SEARCH
    }
}