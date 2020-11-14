package com.israis007.pruebagapsi.ui.market.cart

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
import kotlinx.android.synthetic.main.item_detail.view.*
import java.text.DecimalFormat

class CartAdapter (
    private val context: Context,
    private val list: ArrayList<Items>,
    private val itemDetailEvent: ItemDetailEvent
): RecyclerView.Adapter<CartAdapter.ItemDetailViewHolder>() {

    private val format = DecimalFormat(BuildConfig.DECIMAL_THOUSANDS_FORMAT)

    interface ItemDetailEvent {
        fun removeItem(items: Items)
    }

    inner class ItemDetailViewHolder(private val view: View): RecyclerView.ViewHolder(view){
        fun onBindItem(items: Items){
            Glide.with(context).load(items.image).placeholder(R.drawable.ic_baseline_shopping_cart_24).error(
                R.drawable.ic_baseline_shopping_cart_24).diskCacheStrategy(DiskCacheStrategy.AUTOMATIC).fitCenter().into(view.productDetailImage)
            view.productDetailName.text = items.title
            view.productDetailPrice.text = ("$${format.format(items.price)}")
            view.remove.setOnClickListener {
                itemDetailEvent.removeItem(items)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemDetailViewHolder =
        ItemDetailViewHolder(LayoutInflater.from(context).inflate(R.layout.item_detail, parent, false))

    override fun onBindViewHolder(holder: ItemDetailViewHolder, position: Int) =
        holder.onBindItem(list[position])

    override fun getItemCount(): Int =
        list.size

    fun removeItem(items: Items) {
        var  pos = 0
        for (i in 0 until this.list.size){
            if (list[i] == items) {
                pos = i
                break
            }
        }
        this.list.remove(items)
        this@CartAdapter.notifyItemRemoved(pos)
    }

    fun getAmount(): String {
        var d = 0.0
        list.forEach {
            d += it.price
        }
        return ("$${format.format(d)}")
    }

    fun addItem(items: List<Items>){
        this.list.addAll(items)
        this@CartAdapter.notifyItemInserted(itemCount)
    }

    fun clean(){
        for (i in 0 until list.size){
            this.list.removeAt(0)
            this@CartAdapter.notifyItemRemoved(i)
        }
    }

}