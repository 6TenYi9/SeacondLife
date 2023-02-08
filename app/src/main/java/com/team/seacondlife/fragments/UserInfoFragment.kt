package com.team.seacondlife.fragments

import android.content.Intent
import android.content.Intent.getIntent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.team.seacondlife.R
import com.team.seacondlife.databinding.FragmentUserInfoBinding
import kotlin.math.log


class UserInfoFragment : Fragment() {
    private lateinit var bind:FragmentUserInfoBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreate(savedInstanceState)
        bind= FragmentUserInfoBinding.inflate(inflater,container,false)
        val view: View =bind.root //inflater.inflate(R.layout.fragment_user_info,container,false)

        //get the user name
        val name=activity?.intent!!.extras!!.getString("name")
        bind.UserName.text=name



        return view
    }
}