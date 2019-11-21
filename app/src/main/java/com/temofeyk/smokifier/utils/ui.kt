package com.temofeyk.smokifier.utils

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import kotlin.reflect.KClass

fun Context.startInNewTask(activity: KClass<out AppCompatActivity>) {
        val intent = Intent(this, activity.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
    }