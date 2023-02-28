package com.team.seacondlife.codescanner

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.team.seacondlife.R

class Sorry : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sorry)

        val button = findViewById<Button>(R.id.button)

        var code = intent.extras!!.getString("CODE")

        button.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, AddItem::class.java)
            intent.putExtra("CODE", code)
            startActivityForResult(intent, 1) })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                finish()
            }
        }
    }
}