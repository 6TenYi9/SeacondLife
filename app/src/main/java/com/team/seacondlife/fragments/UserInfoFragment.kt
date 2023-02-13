package com.team.seacondlife.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import com.team.seacondlife.databinding.FragmentUserInfoBinding
import kotlinx.coroutines.*


class UserInfoFragment : Fragment() {
    private lateinit var bind:FragmentUserInfoBinding
    private val scope= MainScope()



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

        bind.pointsLeft.text="0/10"

        scope.launch {
            while (true)
                progress(bind.progressBar)
        }


        return view
    }

    private suspend fun progress(progressBar: ProgressBar){
        progressBar.max=10
        while(true){
            delay(1000)
        if(progressBar.progress < progressBar.max) {
            progressBar.progress=points
            bind.pointsLeft.text= "$points/10"
            points++
        }else {
            progressBar.progress = 0
            points = 0
            bind.pointsLeft.text = "$points/10"
        }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        scope.cancel()
    }

    companion object{
        var points = 0
    }
}

