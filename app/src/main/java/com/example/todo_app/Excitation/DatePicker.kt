package com.example.todo_app.Excitation

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale


fun Calendar.ignoreTime(){
    set(Calendar.HOUR_OF_DAY, 0)
    set(Calendar.MINUTE, 0)
    set(Calendar.SECOND, 0)
    set(Calendar.MILLISECOND, 0)
}
fun Calendar.ignoreDate(){
    set(Calendar.YEAR, 0)
    set(Calendar.MONTH, 0)
    set(Calendar.DAY_OF_WEEK, 0)
}
fun Calendar.setDate(year:Int,month:Int,day:Int){
    set(Calendar.YEAR,year)
    set(Calendar.MONTH,month)
    set(Calendar.DAY_OF_MONTH,day)
}

fun Calendar.setTime(hour:Int,minute:Int){
    set(Calendar.HOUR_OF_DAY,hour)
    set(Calendar.MINUTE,minute)
}

fun Calendar.formatDateText():String{
    val formatDate = SimpleDateFormat("yyyy MMM dd", Locale.ENGLISH)

    return formatDate.format(time)
}
fun Calendar.formatTimeText():String{
    val formatTime = SimpleDateFormat("HH:mm a", Locale.ENGLISH)

    return formatTime.format(time)
}