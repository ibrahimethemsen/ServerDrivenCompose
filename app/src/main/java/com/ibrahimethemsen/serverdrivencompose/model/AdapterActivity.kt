package com.ibrahimethemsen.serverdrivencompose.model

import com.google.gson.annotations.SerializedName
import com.ibrahimethemsen.serverdrivencompose.DEF_FLOAT
import com.ibrahimethemsen.serverdrivencompose.DEF_STRING

data class AdapterActivity(
    @SerializedName("adapter_activity_product_title_size")
    val productTitleSize : Float = Float.DEF_FLOAT,
    @SerializedName("adapter_activity_product_title_color")
    val productTitleColor : String = String.DEF_STRING,
    @SerializedName("adapter_activity_product_price_size")
    val productPriceSize : Float = Float.DEF_FLOAT,
    @SerializedName("adapter_activity_product_price_color")
    val productPriceColor : String = String.DEF_STRING,
    @SerializedName("adapter_activity_product_reduction_title_size")
    val productReductionTitleSize : Float = Float.DEF_FLOAT
)
