package com.team.seacondlife

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        openApp()
        val foto = findViewById<ImageView>(R.id.logo)
        val myanim = AnimationUtils.loadAnimation(this, R.anim.blink)
        foto.startAnimation(myanim)

    }

    private fun openApp() {
        val handler = Handler()
        handler.postDelayed({
            val intent = Intent(this@SplashActivity, StartActivity::class.java)
            startActivity(intent)
        }, 4000)
    }
}