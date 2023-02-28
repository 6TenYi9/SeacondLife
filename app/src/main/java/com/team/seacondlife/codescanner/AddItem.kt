package com.team.seacondlife.codescanner

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import com.team.UserDataBase.ScannerSQLiteHelper
import com.team.seacondlife.R

class AddItem : AppCompatActivity() {

    val nom = findViewById<EditText>(R.id.nom)
    val spinner = findViewById<Spinner>(R.id.spinner)
    val accept = findViewById<Button>(R.id.button)

    var code = ""

    val scandbhelp= ScannerSQLiteHelper(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_item)

        accept.setOnClickListener(View.OnClickListener { addItem() })

        code = intent.extras!!.getString("CODE").toString()
    }

    private fun addItem() {
        var nombre=nom.text.toString()
        var type= ""
        when (spinner.getSelectedItem().toString()){
            "AMARILLO" -> type = "0"
            "VERDE" -> type = "1"
            "AZUL" -> type = "2"
        }
        scandbhelp.addNewObject(code, nombre, type)
    }


}