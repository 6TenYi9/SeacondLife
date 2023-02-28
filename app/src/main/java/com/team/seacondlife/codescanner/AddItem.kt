package com.team.seacondlife.codescanner

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.team.UserDataBase.ScannerSQLiteHelper
import com.team.seacondlife.R

class AddItem : AppCompatActivity() {

    lateinit var nom: EditText
    lateinit var spinner: Spinner
    lateinit var accept: Button

    var code = ""

    val scandbhelp= ScannerSQLiteHelper(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_item)

        nom = findViewById<EditText>(R.id.nom)
        spinner = findViewById<Spinner>(R.id.spinner)
        accept = findViewById<Button>(R.id.accept)

        accept.setOnClickListener(View.OnClickListener { addItem() })

        code = intent.extras!!.getString("CODE").toString()
    }

    private fun addItem() {
        var nombre=nom.text.toString()
        var type= ""
        when (spinner.getSelectedItem().toString()){
            "Amarillo" -> type = "0"
            "Verde" -> type = "1"
            "Azul" -> type = "2"
            else -> type = "error"
        }
        if(scandbhelp.verifyItem(code) == false && type != "error"){
            scandbhelp.addNewObject(code, nombre, type)
            Toast.makeText(this,"Objeto añadido", Toast.LENGTH_LONG).show()
        }
        else
        {
            Toast.makeText(this,"Error", Toast.LENGTH_LONG).show()
        }
        var intent = Intent()
        if (parent == null)
            setResult(Activity.RESULT_OK, intent)
        else
            parent.setResult(Activity.RESULT_OK, intent)
        finish()
    }


}