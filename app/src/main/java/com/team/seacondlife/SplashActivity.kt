package com.team.seacondlife

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        openApp()


    }

    private fun openApp() {
        val handler = Handler()
        handler.postDelayed({
            val intent = Intent(this@SplashActivity, LoginActivity::class.java)
            startActivity(intent)
        }, 3000)
    }
}