package com.example.todo_app.DataBase

import android.app.Application

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
AppDataBase.init(this
)
    }
}