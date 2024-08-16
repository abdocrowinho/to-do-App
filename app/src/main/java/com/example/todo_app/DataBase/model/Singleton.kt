package com.example.todo_app.DataBase.model

import android.content.Context
import androidx.room.Room
import com.example.todo_app.DataBase.AppDataBase

class Singleton {
    private val databaseName = "Task"
    private var dp: AppDataBase? = null

    fun createDataBase(context: Context): AppDataBase? {
        if (dp == null) {
            dp = Room.databaseBuilder(
                context = context.applicationContext,
                name = databaseName,
                klass = AppDataBase::class.java
            )
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build()
        }
        return dp
    }
}