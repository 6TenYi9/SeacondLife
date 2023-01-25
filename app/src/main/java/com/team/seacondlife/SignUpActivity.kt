package com.team.seacondlife

import android.content.ContentValues
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.team.UserDataBase.UserSQLiteHelper
import com.team.seacondlife.databinding.ActivitySignUpBinding

class SignUpActivity : AppCompatActivity() {

    private lateinit var binding:ActivitySignUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var dbhelpe=UserSQLiteHelper(applicationContext)
        var db=dbhelpe.writableDatabase
        var data=ContentValues()
        data.put("username","admin")
        data.put("email","admin@gmail.com")
        data.put("password","admin")
        var rs:Long =db.insert("UserDataBase",null,data)
        /** mostrar toast con error si el nombre de usuario o email ya existen */

    }
}