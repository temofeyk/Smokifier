package com.temofeyk.smokifier

import android.app.Application
import android.content.Context

import com.temofeyk.smokifier.data.prefs.Prefs
import com.temofeyk.smokifier.data.prefs.PrefsImpl

class App : Application() {

    companion object {
        lateinit var prefs: Prefs
    }

    override fun onCreate() {
        super.onCreate()
        prefs = PrefsImpl(this)

    }


}
