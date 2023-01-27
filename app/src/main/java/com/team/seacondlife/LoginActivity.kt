package com.team.seacondlife

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.team.UserDataBase.UserSQLiteHelper
import com.team.seacondlife.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var bind:ActivityLoginBinding
    val dbhelp=UserSQLiteHelper(this)

    @SuppressLint("Range")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind=ActivityLoginBinding.inflate(layoutInflater)
        setContentView(bind.root)

        //dbhelp.addMasterUser()

        var contra = findViewById<TextView>(R.id.textForgotPassword)

        bind.buttonLogin.setOnClickListener{
            var username=bind.editUsername.text.toString()
            var password=bind.editPassword.text.toString()

            if(dbhelp.VerifyUser(username,password) == true){
                ToMain()
            }else{
                var ad = AlertDialog.Builder(this)
                ad.setTitle("Message")
                ad.setMessage("\nUps, Username or password is incorrect!!")
                ad.setPositiveButton("Ok", null)
                ad.show()
            }
        }
        /*
        //login to access to Main without DB
        var LoginButton=findViewById<Button>(R.id.buttonLogin)
        LoginButton.setOnClickListener(View.OnClickListener { ToMain() })*/

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