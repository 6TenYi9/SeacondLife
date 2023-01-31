package com.team.seacondlife

import android.annotation.SuppressLint
import android.app.Dialog
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
                ToMain()
            }else{
                var ad = Dialog(this)
                ad.setContentView(R.layout.dialog_style)
                //ad.setTitle(R.string.AlertDg_common_title)
                //ad.setMessage(R.string.AlertDg_message_ErrLogin)
                //ad.setPositiveButton(R.string.AlertDg_possbtn_text, null)
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