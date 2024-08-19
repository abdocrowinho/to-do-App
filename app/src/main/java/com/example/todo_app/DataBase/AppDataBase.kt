package com.example.todo_app.DataBase

import android.app.Application
import android.content.Context
import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.todo_app.DataBase.Dao.TaskDao
import com.example.todo_app.DataBase.model.Task

@Database(
    entities = [Task::class], exportSchema = true, version = 4,
    autoMigrations = [AutoMigration(1, 2), AutoMigration(2, 3), AutoMigration(3, 4)]

)
abstract class AppDataBase : RoomDatabase() {
    abstract fun tasksDao(): TaskDao

    companion object {
        private var dp: AppDataBase? = null
        val dataBase = "Task"

        fun getInstance(): AppDataBase {
            return dp!!
        }

        fun init(applicationContext: Application) {
            if (dp == null) {
                dp = Room.databaseBuilder(
                    context = applicationContext,
                    name = dataBase,
                    klass = AppDataBase::class.java
                ).allowMainThreadQueries()
                    .build()
            }
        }
//val MIGRATION_1_2 = object : Migration(1,2){
//    override fun migrate(db: SupportSQLiteDatabase) {
//db.execSQL("ALTER TABLE Task ADD COLUMN number INTEGER DEFAULT NULL ")    }

    }


}

