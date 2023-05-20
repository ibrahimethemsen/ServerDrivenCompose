package com.ibrahimethemsen.serverdrivencompose.home

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.ibrahimethemsen.serverdrivencompose.model.ItemActivity
import com.ibrahimethemsen.serverdrivencompose.model.ViewTypeModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val gson: Gson,
    private val remoteConfig: FirebaseRemoteConfig
) : ViewModel(){
    var homeScreenUi = mutableStateOf<List<ViewTypeModel>>(mutableListOf())
    var activityList = mutableStateOf<List<ItemActivity>>(mutableListOf())
    init {
        getHomeUi()
        getActivityList()
    }

    private fun getActivityList(){
        viewModelScope.launch {
            activityList = remoteConfig.fetchToState("product_offer",gson, mutableListOf())
        }
    }

    private fun getHomeUi(){
        viewModelScope.launch {
            homeScreenUi =  remoteConfig.fetchToState("home_screen",gson, mutableListOf())
        }
    }
}

inline fun <reified T> FirebaseRemoteConfig.fetchToState(
    key: String,
    gson: Gson,
    defaultValue : T
) : MutableState<T>{
    val mutableState = mutableStateOf<T>(defaultValue)
    this.fetchAndActivate().addOnCompleteListener {
        if (it.isSuccessful) {
            val keyString = this.getString(key)
            val type = object : TypeToken<T>() {}.type
            val jsonModel = gson.fromJson<T>(keyString, type)
            mutableState.value = jsonModel
        }
    }
    return mutableState
}