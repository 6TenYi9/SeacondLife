package com.team.seacondlife

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        var contra = findViewById<TextView>(R.id.textForgotPassword)

        //login to access to Main without DB
        var LoginButton=findViewById<Button>(R.id.buttonLogin)
        LoginButton.setOnClickListener(View.OnClickListener { ToMain() })

        contra.setOnClickListener(View.OnClickListener { contraToast() })
    }

    fun contraToast() {
        val toast = Toast.makeText(this, "Sorry, something went wrong", Toast.LENGTH_LONG)
        toast.show()
    }

    fun ToMain(){
        val intent=Intent (this, MainActivity::class.java)
        startActivity(intent)
    }
}