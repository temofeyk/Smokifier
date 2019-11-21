package com.temofeyk.smokifier.data.prefs

import android.content.Context
import android.content.SharedPreferences
import java.util.*

class PrefsImpl(private val context: Context) : Prefs {

    private val prefs: SharedPreferences
        get() = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    override val isFirstLaunch: Boolean
        get() = prefs.getBoolean(KEY_FIRST_LAUNCH, true)

    override fun setFirstLaunch(firstLaunch: Boolean) {
        prefs.edit().putBoolean(KEY_FIRST_LAUNCH, firstLaunch).apply()
    }

    override val hours: Int
        get() = prefs.getInt(KEY_TIMER_HOURS, 0)

    override fun setHours(hours: Int) {
        prefs.edit().putInt(KEY_TIMER_HOURS, hours).apply()
    }

    override val minutes: Int
        get() = prefs.getInt(KEY_TIMER_MINUTES, 45)

    override fun setMinutes(minutes: Int) {
        prefs.edit().putInt(KEY_TIMER_MINUTES, minutes).apply()
    }

    override val notificationTimeInMilliSeconds: Long
        get() = prefs.getLong(KEY_TIME_MS, Calendar.getInstance().timeInMillis)

    override fun setNotificationTimeInMilliSeconds(timeInMilliSeconds: Long) {
        prefs.edit().putLong(KEY_TIME_MS, timeInMilliSeconds).apply()
    }

    companion object {

        private val PREFS_NAME = "prefs"
        private val KEY_FIRST_LAUNCH = "first_launch"
        private val KEY_TIMER_MINUTES = "timer_minutes"
        private val KEY_TIMER_HOURS = "timer_hours"
        private val KEY_TIME_MS = "time_in_milli_seconds"

    }


}
