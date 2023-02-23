package com.team.seacondlife.fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.team.seacondlife.R


class TipsFragment : Fragment(), OnClickListener {


    //hay que arreglar el boton
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreate(savedInstanceState)
        val view: View = inflater.inflate(com.team.seacondlife.R.layout.fragment_tips,container,false)
        return view

    }
    override fun onClick(v: View?) {
        Toast.makeText(this.context, "aaaaaa", Toast.LENGTH_SHORT).show()

    }

}
