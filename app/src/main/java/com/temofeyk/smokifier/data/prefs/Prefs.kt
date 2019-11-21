package com.temofeyk.smokifier.data.prefs

interface Prefs {
    val isFirstLaunch: Boolean
    fun setFirstLaunch(firstLaunch: Boolean)

    val hours: Int
    fun setHours(hours: Int)

    val minutes: Int
    fun setMinutes(minutes: Int)

    val notificationTimeInMilliSeconds: Long
    fun setNotificationTimeInMilliSeconds(timeInMilliSeconds: Long)

}
