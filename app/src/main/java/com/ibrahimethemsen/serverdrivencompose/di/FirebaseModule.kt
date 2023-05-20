package com.ibrahimethemsen.serverdrivencompose.di

import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfig
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import com.google.firebase.remoteconfig.ktx.remoteConfigSettings
import com.ibrahimethemsen.serverdrivencompose.R.xml as AppConfig

@Module
@InstallIn(SingletonComponent::class)
object FirebaseModule {
    @Provides
    fun provideRemoteConfig() : FirebaseRemoteConfig{
        return Firebase.remoteConfig.apply {
            val configSettings = remoteConfigSettings { minimumFetchIntervalInSeconds = 0}
            setConfigSettingsAsync(configSettings)
            setDefaultsAsync(AppConfig.map_remote_config)
        }
    }

    @Provides
    fun provideGson(): Gson {
        return Gson()
    }
}