package com.example.taskapp.utils

import android.content.Context
import android.content.SharedPreferences

class Preferences (context:Context){
    private val sharedPreference:SharedPreferences = context.getSharedPreferences("prefs",Context.MODE_PRIVATE)
    var board :Boolean
    get() = sharedPreference.getBoolean("board",false)
    set(value) = sharedPreference.edit().putBoolean("board",value).apply()

    var imgProfile:String?
    get() = sharedPreference.getString("img","")
    set(value) = sharedPreference.edit().putString("img",value).apply()

    var etName:String?
    get() = sharedPreference.getString("name","")
    set(value) = sharedPreference.edit().putString("name",value).apply()
}