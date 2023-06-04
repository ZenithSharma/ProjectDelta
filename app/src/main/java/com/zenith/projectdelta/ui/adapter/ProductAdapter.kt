package com.zenith.projectdelta.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.zenith.projectdelta.data.network.models.Product
import com.zenith.projectdelta.databinding.ProductLayoutBinding

class ProductAdapter : ListAdapter<Product, ProductAdapter.ProductViewHolder>(ComparatorDiffUtil()) {

    class ProductViewHolder(private val binding: ProductLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(product: Product) {
            binding.productTitle.text = product.title
            binding.productDesc.text = product.description
            Glide.with(itemView).load(product.thumbnail).into(binding.thumnail)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val binding =
            ProductLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = getItem(position)
        product?.let {
            holder.bind(product)
        }
    }

    class ComparatorDiffUtil : androidx.recyclerview.widget.DiffUtil.ItemCallback<Product>() {
        override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem == newItem
        }
    }
}