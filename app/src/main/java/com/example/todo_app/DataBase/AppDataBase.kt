package com.example.todo_app.DataBase

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.todo_app.DataBase.model.Task

@Database(entities = [Task::class], version = 0)
abstract class AppDataBase : RoomDatabase() {
    private var dp: AppDataBase? = null
}
