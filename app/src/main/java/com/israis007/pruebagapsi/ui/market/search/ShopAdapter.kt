package com.israis007.pruebagapsi.ui.market.search

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.israis007.pruebagapsi.BuildConfig
import com.israis007.pruebagapsi.R
import com.israis007.pruebagapsi.rest.models.Items
import kotlinx.android.synthetic.main.item_result.view.*
import java.text.DecimalFormat

class ShopAdapter (
    private val context: Context,
    private val list: ArrayList<Items>,
    private val event: ItemEvent
): RecyclerView.Adapter<ShopAdapter.ItemViewHolder>() {

    interface ItemEvent{
        fun onBuy(items: Items)
    }

    inner class ItemViewHolder(private val view: View): RecyclerView.ViewHolder(view){

        fun onBindItem(items: Items){
            val format = DecimalFormat(BuildConfig.DECIMAL_THOUSANDS_FORMAT)
            val format2 = DecimalFormat(BuildConfig.DECIMAL_FORMAT)
            Glide.with(context).load(items.image).placeholder(R.drawable.ic_baseline_shopping_cart_24).error(R.drawable.ic_baseline_shopping_cart_24).diskCacheStrategy(DiskCacheStrategy.AUTOMATIC).fitCenter().into(view.productImage)
            view.productName.text = items.title
            view.productPrice.text = ("$${format.format(items.price)}")
            view.productRate.setStar(format2.format(items.rating).toFloat())
            view.productBuy.setOnClickListener {
                event.onBuy(items)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder =
        ItemViewHolder(LayoutInflater.from(context).inflate(R.layout.item_result, parent, false))

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) =
        holder.onBindItem(list[position])

    override fun getItemCount(): Int =
        list.size

    fun changeList(items: List<Items>){
        this.list.clear()
        this.list.addAll(items)
        this@ShopAdapter.notifyDataSetChanged()
    }
}