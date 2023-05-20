package com.ibrahimethemsen.serverdrivencompose.model

import com.google.gson.annotations.SerializedName

data class ViewTypeModel(
    @SerializedName("view_type")
    val viewType : String,
    @SerializedName("container_appbar")
    val containerAppbar : ContainerAppbar = ContainerAppbar(),
    @SerializedName("container_activity")
    val containerActivity : ContainerActivity = ContainerActivity(),
    @SerializedName("container_mock")
    val containerMock : ContainerMock = ContainerMock()
)