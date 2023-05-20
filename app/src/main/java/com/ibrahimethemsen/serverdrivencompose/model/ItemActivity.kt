package com.ibrahimethemsen.serverdrivencompose.model

import com.google.gson.annotations.SerializedName

data class ItemActivity(
    @SerializedName("activity_product_id")
    val productId: String,
    @SerializedName("activity_product_name")
    val productName: String,
    @SerializedName("activity_product_price")
    val productPrice: Int,
    @SerializedName("activity_product_reduction_text")
    val productReduction: String,
    @SerializedName("activity_product_reduction_text_bg")
    val productReductionBg: String,
    @SerializedName("activity_product_reduction_text_color")
    val productReductionColor: String,
    @SerializedName("activity_product_favorite")
    val productFavorite: Boolean,
    @SerializedName("activity_product_url")
    val productImage: String,
)
