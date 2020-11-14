package com.israis007.pruebagapsi.ui.market.search

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.israis007.pruebagapsi.R
import com.israis007.pruebagapsi.rest.models.Items
import com.israis007.pruebagapsi.ui.market.ShopViewModel
import kotlinx.android.synthetic.main.main_fragment.view.*

class ShopFragment : Fragment() {

    companion object {
        fun newInstance() = ShopFragment()
    }

    private lateinit var viewModel: ShopFragmentViewModel
    private lateinit var fraView: View
    private lateinit var adapter: ShopAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fraView = inflater.inflate(R.layout.main_fragment, container, false)
        adapter = ShopAdapter(activity!!, arrayListOf(), object: ShopAdapter.ItemEvent{
            override fun onBuy(items: Items) {
                ShopViewModel.buyItem.postValue(items)
            }
        })

        fraView.recyclerResults.layoutManager = LinearLayoutManager(activity!!).apply { orientation = RecyclerView.VERTICAL }
        fraView.recyclerResults.adapter = adapter
        fraView.recyclerResults.isNestedScrollingEnabled = true
        return fraView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ShopFragmentViewModel::class.java)

        ShopFragmentViewModel.items.observe(this, Observer {
            val elements = it ?: return@Observer
            fraView.empty.visibility = if (elements.isEmpty()) View.VISIBLE else View.GONE
            adapter.changeList(elements)
            fraView.recyclerResults.scrollToPosition(0)
        })

    }



}