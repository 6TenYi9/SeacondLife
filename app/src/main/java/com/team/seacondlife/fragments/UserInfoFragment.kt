package com.team.seacondlife.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import com.team.UserDataBase.UserSQLiteHelper
import com.team.seacondlife.MainActivity
import com.team.seacondlife.R
import com.team.seacondlife.databinding.FragmentUserInfoBinding
import kotlinx.coroutines.*


class UserInfoFragment : Fragment() {
    private lateinit var bind:FragmentUserInfoBinding
    private val scope= MainScope()
    private lateinit var progress:ProgressBar

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreate(savedInstanceState)
        bind= FragmentUserInfoBinding.inflate(inflater,container,false)
        val view: View =bind.root //inflater.inflate(R.layout.fragment_user_info,container,false)

        //get the user name
        try{
        val name=activity?.intent!!.extras!!.getString("name")
        var point=activity?.intent!!.extras!!.getInt("p")
        bind.UserName.text=name

        bind.pointsLeft.text="0/10"

        /*scope.launch {
            while (true)
                progress(bind.progressBar)
        }*/

        progress=bind.progressBar
        progress.max=10
        if(progress.progress<progress.max){
            point -= (point / 10) * 10
            progress.progress=point
            bind.pointsLeft.text="${(point).toString()}/10"
        }else{
            Toast.makeText(context,R.string.error,Toast.LENGTH_LONG).show()
        }
        }catch(e:java.lang.NullPointerException){}
        return view
    }

    /*private suspend fun progress(progressBar: ProgressBar){
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
    }*/
}

