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
    private var point=0
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreate(savedInstanceState)
        val dbhelper= activity?.let { UserSQLiteHelper(it) }
        bind= FragmentUserInfoBinding.inflate(inflater,container,false)
        val view: View =bind.root //inflater.inflate(R.layout.fragment_user_info,container,false)

        var name=""
        var password=""

        //get the user name
        try{
            name=activity?.intent!!.extras!!.getString("name").toString()
            password=activity?.intent!!.extras!!.getString("psw").toString()
            point=dbhelper!!.getUserPoints(name,password)

            bind.UserName.text=name

            bind.pointsLeft.text="0/5"

            setProgress()


        }catch(e:java.lang.NullPointerException){}

        bind.userswaper.setOnRefreshListener {
            point=dbhelper!!.getUserPoints(name,password)
            setProgress()
            bind.userswaper.isRefreshing=false
        }
        return view
    }

    fun setProgress(){
        progress=bind.progressBar
        progress.max=5
        if(progress.progress<progress.max){
            point -= (point / 10) * 10
            if(point>=5)
                point-=5
            progress.progress=point
            bind.pointsLeft.text="${(point).toString()}/5"
        }else{
            Toast.makeText(context,R.string.error,Toast.LENGTH_LONG).show()
        }
    }
}

