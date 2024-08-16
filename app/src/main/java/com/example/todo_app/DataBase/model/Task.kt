package com.example.todo_app.DataBase.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Task(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "ID")
    val id: Int = 0,
    val tittle: String? = null,
    @ColumnInfo(index = true)
    val date: Long? = null,
    val time: Long? = null,
    val description: String? = null,
    val isDone: Boolean = false
)
