package com.example.tictactoe

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler


class SplashScreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        supportActionBar?.hide()
//        Handler().postAtTime(
//
//        )
        Handler().postDelayed({

            startActivity(Intent(this,HomeActivity::class.java))
            finish()

        },1000)
    }

}