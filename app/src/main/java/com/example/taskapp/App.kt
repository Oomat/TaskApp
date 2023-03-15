package com.example.taskapp

import android.app.Application
import androidx.room.Room.databaseBuilder
import com.example.taskapp.database.local.TaskDataBase

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        db= databaseBuilder(
            this,
            TaskDataBase::class.java,
            "database")
            .allowMainThreadQueries()
            .build()
    }
    companion object {
        lateinit var db:TaskDataBase

    }
}