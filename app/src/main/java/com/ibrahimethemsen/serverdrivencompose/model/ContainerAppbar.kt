package com.ibrahimethemsen.serverdrivencompose.model

import com.google.gson.annotations.SerializedName
import com.ibrahimethemsen.serverdrivencompose.DEF_STRING

data class ContainerAppbar(
    @SerializedName("appbar_start_icon_drawable")
    val startIconDrawable : String = String.DEF_STRING,
    @SerializedName("appbar_search_hint")
    val searchHint : String = String.DEF_STRING,
    @SerializedName("appbar_end_drawable")
    val endDrawable : String = String.DEF_STRING,
    @SerializedName("appbar_search_hint_color")
    val searchHintColor : String = String.DEF_STRING
)