package com.ruslan.huseynov.foodninja.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatDelegate
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class HiltApplication : Application(){

    override fun onCreate() {
        super.onCreate()

        val sharedPrefs: SharedPreferences = getSharedPreferences("mode", Context.MODE_PRIVATE)
        val isNightMode = sharedPrefs.getBoolean("night", false)

        if (isNightMode) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }
    }
}