package com.team.seacondlife.codescanner

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.team.seacondlife.R

class Sorry : AppCompatActivity() {

    val button = findViewById<Button>(R.id.button)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sorry)

        var code = intent.extras!!.getString("CODE")

        button.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, AddItem::class.java)
            intent.putExtra("CODE", code)
            startActivity(intent) })
    }

}