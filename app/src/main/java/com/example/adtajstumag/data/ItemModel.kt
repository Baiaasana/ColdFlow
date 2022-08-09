package com.example.adtajstumag.data

import com.squareup.moshi.Json

data class ItemModel(
    val content: List<Item>
){
    data class Item(
        val id: String? = null,
        val descriptionEN: String? = null,
        val descriptionKA: String? = null,
        val descriptionRU: String? = null,
        val titleEN: String? = null,
        val titleKA: String? = null,
        val titleRU: String? = null,
        val published: Int? = null,
        @field:Json(name = "publish_date")
        val publishDate: String? = null,
        @field:Json(name = "created_at")
        val createdAt: Long? = null,
        @field:Json(name = "updated_at")
        val updatedAt: Long? = null,
        val category: String? = null,
        val cover: String? = null,
        val isLast: Boolean? = null
    )
}
