package com.team.seacondlife

import android.content.ContentValues
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.team.UserDataBase.UserSQLiteHelper
import com.team.seacondlife.databinding.ActivitySignUpBinding

class SignUpActivity : AppCompatActivity() {

    private lateinit var binding:ActivitySignUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val dbhelpe=UserSQLiteHelper(applicationContext)
        var DataAdded=AlertDialog.Builder(this)

        binding.buttonSignUp.setOnClickListener{
            var username_new=binding.editUsername.text.toString()
            var email_new=binding.editEmail.text.toString()
            var password_new=binding.editSetPassword.text.toString()
            var confirm_password=binding.editConfirmPassword.text.toString()
            if(username_new.isNotEmpty() && email_new.isNotEmpty() && password_new.isNotEmpty() && confirm_password.isNotEmpty()){
                if(password_new==confirm_password&&dbhelpe.VerifyUser(username_new,password_new)!=true){
                    dbhelpe.addNewUser(username_new,email_new,password_new)
                    DataAdded.setTitle(R.string.AlertDg_common_title)
                    DataAdded.setMessage(R.string.AlertDg_message_SuccSignUp)
                    DataAdded.setPositiveButton(R.string.AlertDg_possbtn_text){dialog,which->
                        ToMain()
                    }
                    DataAdded.show()
                    binding.editUsername.text?.clear()
                    binding.editEmail.text?.clear()
                    binding.editSetPassword.text?.clear()
                    binding.editConfirmPassword.text?.clear()
                }else{
                    /** mostrar toast con error si el nombre de usuario y contraseña ya existen */
                    DataAdded.setTitle(R.string.AlertDg_common_title)
                    DataAdded.setMessage(R.string.AlertDg_message_ErrSignUp)
                    DataAdded.setPositiveButton(R.string.AlertDg_possbtn_text,null)
                    DataAdded.show()
                }
            }else{
                DataAdded.setTitle(R.string.AlertDg_common_title)
                DataAdded.setMessage(R.string.AlertDg_message_EmptySignUp)
                DataAdded.setPositiveButton(R.string.AlertDg_possbtn_text,null)
                DataAdded.show()
            }
        }
    }

    fun ToMain(){
        val intent=Intent(this,LoginActivity::class.java)
        startActivity(intent)
    }
}