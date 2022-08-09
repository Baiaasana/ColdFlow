package com.example.adtajstumag.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.adtajstumag.ADTAJSTUMAG
import com.example.adtajstumag.data.ItemDiffCallback
import com.example.adtajstumag.data.ItemModel
import com.example.adtajstumag.databinding.SingleItemBinding

class ItemAdapter : ListAdapter<ItemModel.Item, ItemAdapter.ItemViewHolder>(ItemDiffCallback()){

    var itemClick: ((ItemModel.Item) -> Unit)? = null

    inner class ItemViewHolder(private val binding: SingleItemBinding): RecyclerView.ViewHolder(binding.root){

        fun bind() {
            val item  = getItem(adapterPosition)
            binding.tvTitle.text = item.titleKA.toString()
            binding.tvPublishDate.text = item.publishDate.toString()
            ADTAJSTUMAG().adtajstumag(item.cover.toString(), binding.imgItem)
            binding.tvMore.setOnClickListener {
                itemClick?.invoke(item)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(SingleItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind()
    }
}
