package com.example.taskmanager

import android.app.Application
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.room.Room
import com.example.taskmanager.data.local.db.AppDatabase

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "database-name"
        ).allowMainThreadQueries().build()
    }

    companion object {
        lateinit var db: AppDatabase
    }
}