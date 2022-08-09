package com.example.adtajstumag.data

import androidx.recyclerview.widget.DiffUtil

class ItemDiffCallback : DiffUtil.ItemCallback<ItemModel.Item>() {
    override fun areItemsTheSame(oldItem: ItemModel.Item, newItem: ItemModel.Item): Boolean {
        return oldItem.id  == newItem.id
    }

    override fun areContentsTheSame(oldItem: ItemModel.Item, newItem: ItemModel.Item): Boolean {
        return oldItem == newItem
    }

}