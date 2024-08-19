package com.example.todo_app.DataBase.Dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.todo_app.DataBase.model.Task

@Dao
 interface TaskDao {
@Insert
    fun addNewTask(task: Task)
    @Delete
    fun  deleteTask(task: Task)
    @Update
    fun updateTask(task: Task)

    @Query("Select * From Task ")
    fun getAllTask():List<Task>

    @Query("select * from TASK where date=:date")
    fun gitTaskByDate(date : Long):List<Task>
}