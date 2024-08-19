package com.example.todo_app.DataBase.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@kotlinx.parcelize.Parcelize
@Entity
data class Task(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "ID")
    var id: Int = 0,
    var tittle: String? = null,
    @ColumnInfo(index = true)
    var date: Long? = null,
    var time: String? = null,
    var description: String? = null,
    var number : Int?=null,
    var isDone : Boolean ?=false,
):Parcelable
