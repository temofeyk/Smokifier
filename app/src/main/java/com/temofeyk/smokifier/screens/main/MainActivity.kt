package com.temofeyk.smokifier.screens.main

import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.temofeyk.android.smokifier.R
import com.temofeyk.smokifier.App
import com.temofeyk.smokifier.utils.NotificationUtils
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {
    private var notificationTime: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        numberPickerHours.maxValue = 23
        numberPickerHours.minValue = 0

        numberPickerMinutes.maxValue = 59
        numberPickerMinutes.minValue = 1

        numberPickerHours.value = App.prefs.hours
        numberPickerMinutes.value = App.prefs.minutes
        notificationTime = App.prefs.notificationTimeInMilliSeconds

        start_btn.setOnClickListener {
            startTimer()
        }

        val timeInMillis = Calendar.getInstance().timeInMillis
        val millisUntilFinished = notificationTime - timeInMillis
        if (millisUntilFinished > 0) {
            timer_expires.visibility = View.VISIBLE

            val timer = object : CountDownTimer(notificationTime - timeInMillis, 1000) {
                override fun onTick(millisUntilFinished: Long) {
                    val hh = TimeUnit.MILLISECONDS.toHours(millisUntilFinished)
                    val mm = TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished) % 60
                    val ss = TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) % 60 % 60
                    timer_expires.text = getString(R.string.timer_expires, hh, mm, ss)
                }

                override fun onFinish() {
                    timer_expires.visibility = View.INVISIBLE
                }
            }
            timer.start()
        }
    }

    private fun startTimer() {
        val hours = numberPickerHours.value
        val minutes = numberPickerMinutes.value
        val timeInMillis = Calendar.getInstance().timeInMillis

        notificationTime = timeInMillis + 1000 * 60 * 60 * hours + 1000 * 60 * minutes
        NotificationUtils.setNotification(notificationTime, this@MainActivity)
        // save settings
        App.prefs.setHours(numberPickerHours.value)
        App.prefs.setMinutes(numberPickerMinutes.value)
        App.prefs.setNotificationTimeInMilliSeconds(notificationTime)

        finish()
    }
}
