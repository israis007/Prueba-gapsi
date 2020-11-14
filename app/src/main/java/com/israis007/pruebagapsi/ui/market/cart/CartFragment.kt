package com.israis007.pruebagapsi.ui.market.cart

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.israis007.pruebagapsi.R
import com.israis007.pruebagapsi.rest.models.Items
import com.israis007.pruebagapsi.ui.market.ShopViewModel
import kotlinx.android.synthetic.main.cart_fragment.view.*

class CartFragment : Fragment() {

    companion object {
        fun newInstance() = CartFragment()
    }

    private lateinit var viewModel: CartFragmentViewModel
    private lateinit var cartView: View
    private lateinit var adapter: CartAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        cartView = inflater.inflate(R.layout.cart_fragment, container, false)

        adapter = CartAdapter(activity!!, arrayListOf(), object: CartAdapter.ItemDetailEvent{
            override fun removeItem(items: Items) {
                adapter.removeItem(items)
                cartView.totalAmount.text = adapter.getAmount()
                cartView.articlesAmount.text = adapter.itemCount.toString()
                CartFragmentViewModel.countItems.postValue(adapter.itemCount)
                val list = ShopViewModel.itemsToBuy.value ?: arrayListOf()
                if (list.isNotEmpty()) {
                    list.remove(items)
                }
                ShopViewModel.itemsToBuy.postValue(list)
            }
        })

        cartView.recyclerDetail.layoutManager = LinearLayoutManager(activity!!).apply { orientation = RecyclerView.VERTICAL }
        cartView.recyclerDetail.adapter = adapter
        cartView.recyclerDetail.isNestedScrollingEnabled = true

        cartView.completePurchase.setOnClickListener {
            val dialog = AlertDialog.Builder(activity!!)
            dialog.setMessage(R.string.cart_text6)
            dialog.setPositiveButton(R.string.cart_text7) { dialog1, _ ->
                ShopViewModel.itemsToBuy.postValue(arrayListOf())
                adapter.clean()
                dialog1.dismiss()
            }
            dialog.create().show()
        }

        return cartView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = ViewModelProvider(this)[CartFragmentViewModel::class.java]

        CartFragmentViewModel.newBuyItem.observe(this, Observer {
            val item = it ?: return@Observer
            adapter.addItem(ShopViewModel.itemsToBuy.value ?: arrayListOf())
            cartView.totalAmount.text = adapter.getAmount()
            cartView.articlesAmount.text = adapter.itemCount.toString()
            CartFragmentViewModel.countItems.postValue(adapter.itemCount)
        })

    }
}