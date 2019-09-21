package com.widiyanto.simplcrud.adapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.widiyanto.simplcrud.R
import com.widiyanto.simplcrud.model.Product
import kotlinx.android.synthetic.main.item_list_prodact.view.*


class ListProductAdapter(val listProduct: ArrayList<Product>) :
    RecyclerView.Adapter<ListProductAdapter.ListProductViewHolder>() {

    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ListProductAdapter.ListProductViewHolder {
        val view: View = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_list_prodact, viewGroup, false)
        return ListProductViewHolder(view)
    }

    override fun getItemCount(): Int = listProduct.size

    override fun onBindViewHolder(holder: ListProductAdapter.ListProductViewHolder, position: Int) {
        holder.bind(listProduct[position])
        holder.itemView.setOnClickListener() {
            onItemClickCallback.onItemClick(listProduct[holder.adapterPosition])
        }
    }

    class ListProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(product: Product) {
            with(itemView) {
                tvProductName.text = product.name
                tvProductPrice.text = product.price.toString()
                Glide.with(this).load(product.image).into(imgProduct)
            }
        }
    }

    interface OnItemClickCallback {
        fun onItemClick(data: Product)
    }
}