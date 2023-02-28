package com.team.seacondlife.codescanner

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.team.seacondlife.R

class ScannerResult : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scanner_result)

        var result = findViewById<TextView>(R.id.result)

        val intent = intent
        result.setText(intent.getStringExtra("TEXT"))
        title = "RESULTADO"
    }
}