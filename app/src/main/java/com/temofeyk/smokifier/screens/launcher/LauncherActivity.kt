package com.temofeyk.smokifier.screens.launcher

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.temofeyk.smokifier.App
import com.temofeyk.smokifier.screens.main.MainActivity
import com.temofeyk.smokifier.screens.welcome.WelcomeActivity
import com.temofeyk.smokifier.utils.startInNewTask
import kotlin.reflect.KClass

class LauncherActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (App.prefs.isFirstLaunch) {
           startInNewTask(WelcomeActivity::class)
            App.prefs.setFirstLaunch(false)
        } else {
            startInNewTask(MainActivity::class)
        }
    }
}
