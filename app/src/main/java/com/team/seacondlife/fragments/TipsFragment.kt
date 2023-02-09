package com.team.seacondlife.fragments

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.team.seacondlife.LoginActivity
import com.team.seacondlife.R

class TipsFragment : Fragment() {

    //hay que arreglar el boton
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreate(savedInstanceState)
        val view: View = inflater.inflate(R.layout.fragment_tips,container,false)
        var botMas = getView()?.findViewById<Button>(R.id.botonMas)
        var textInfo = view?.findViewById<TextView>(R.id.info)
        botMas?.setOnClickListener(View.OnClickListener {
            textInfo?.setText(R.string.info2.toString())
        })
        return view
    }

}