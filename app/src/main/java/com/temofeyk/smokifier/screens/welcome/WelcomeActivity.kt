package com.temofeyk.smokifier.screens.welcome

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.temofeyk.android.smokifier.R
import com.temofeyk.smokifier.screens.main.MainActivity
import com.temofeyk.smokifier.utils.startInNewTask
import kotlinx.android.synthetic.main.activity_welcome.*

class WelcomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        welcome_start_btn.setOnClickListener {
            startInNewTask(MainActivity::class)
        }
    }
}
