package com.team.seacondlife

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
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
import com.team.seacondlife.fragments.UserInfoFragment

class LoginActivity : AppCompatActivity() {

    private lateinit var bind:ActivityLoginBinding
    val dbhelp=UserSQLiteHelper(this)

    @SuppressLint("Range", "ResourceType")
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
                ToMain(username,password)
            }else{
                var ad = Dialog(this)
                ad.setContentView(R.layout.dialog_style)
                ad.findViewById<TextView>(R.id.DialogMessage).setText(R.string.AlertDg_message_ErrLogin)
                ad.findViewById<Button>(R.id.DialogButton).setOnClickListener{
                    ad.dismiss()
                }
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

    fun ToMain(username:String,password:String){
        var point=dbhelp.getUserPoints(username,password)
        val intent=Intent (this, MainActivity::class.java).apply {
            putExtra("name",username)
            putExtra("p",point)
        }
        startActivity(intent)
    }
}