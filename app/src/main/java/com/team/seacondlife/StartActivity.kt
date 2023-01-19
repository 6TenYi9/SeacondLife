package com.team.seacondlife

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class StartActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)

        var textSignUp = findViewById<TextView>(R.id.textSignUp)
        var buttonLogin = findViewById<Button>(R.id.buttonLogin)

        textSignUp.setOnClickListener(View.OnClickListener { openSignup() })

        buttonLogin.setOnClickListener(View.OnClickListener { openLogin() })

    }

    fun openLogin() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }

    fun openSignup() {
        val intent = Intent(this, SignUpActivity::class.java)
        startActivity(intent)
    }
}