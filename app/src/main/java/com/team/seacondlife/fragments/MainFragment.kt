package com.team.seacondlife.fragments

import android.annotation.SuppressLint
import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.widget.ActionBarOverlayLayout.LayoutParams
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import com.jawnnypoo.physicslayout.Physics
import com.team.UserDataBase.UserSQLiteHelper
import com.team.seacondlife.MainActivity
import com.team.seacondlife.R
import com.team.seacondlife.databinding.FragmentMainBinding
import org.jbox2d.dynamics.Body
import org.jbox2d.dynamics.World
import java.util.*


class MainFragment : Fragment() {

/*
    fun onCreateView(
        inflater: LayoutInflater?,
        @Nullable container: ViewGroup?,
        @Nullable savedInstanceState: Bundle?
    ): View? {
        val binding: MartianDataBinding = DataBindingUtil.inflate(
            inflater, R.layout.martian_data, container, false
        )
        val view: View = binding.getRoot()
        //here data must be an instance of the class MarsDataProvider
        binding.setMarsdata(data)
        return view
    }
*/

    private lateinit var binding: FragmentMainBinding
    private var index: Int = 0
    private var cont: Int = 0
    private var point:Int=0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        super.onCreate(savedInstanceState)
        val dbhelper= activity?.let { UserSQLiteHelper(it) }
        binding = FragmentMainBinding.inflate(layoutInflater)
        val view: View = binding.root
        var username=""
        var password=""

        setHasOptionsMenu(true)

        binding.sea.physics.isFlingEnabled = true
        binding.sea.physics.isPhysicsEnabled = true

        for (i in 0 until binding.sea.childCount) {
            val imageView = binding.sea.getChildAt(i) as ImageView
            imageView.id = i

            imageView.setImageResource(R.drawable.pez)
        }
        index = binding.sea.childCount + 1;

        binding.sea.setOnLongClickListener {
            binding.sea.physics.giveRandomImpulse()
            return@setOnLongClickListener true
        }

        /*binding.sea.physics.setOnCollisionListener(object : Physics.OnCollisionListener {
            @SuppressLint("SetTextIl8n")
            override fun onCollisionEntered(viewIdA: Int, viewIdB: Int) {
                Log.d(ContentValues.TAG, "$viewIdB")
                if (viewIdB != 0) {
                    binding.animal.setImageResource(R.drawable.mantarraya)
                } else {
                    binding.animal.setImageResource(R.drawable.pezpayaso)
                }
            }

            override fun onCollisionExited(viewIdA: Int, viewIdB: Int) {}
        })*/
        //capturo la puntuación del usuario
        try {
            username= activity?.intent!!.extras!!.getString("name").toString()
            password= activity?.intent!!.extras!!.getString("psw").toString()
            point = dbhelper!!.getUserPoints(username,password)
        }catch(e:java.lang.NullPointerException){}
        //añadir peces según la puntuación del usuario
            FishCategory()

        //onReflesh
        binding.swiper.setOnRefreshListener {
            //Toast.makeText(activity,username+" - "+password,Toast.LENGTH_LONG).show()
            try {
                point = dbhelper!!.getUserPoints(username, password)
                FishCategory()
            }catch(e:NullPointerException){
                Toast.makeText(activity,R.string.error,Toast.LENGTH_LONG).show()
            }
            binding.swiper.isRefreshing=false
        }


        binding.sea.physics.addOnPhysicsProcessedListener(object :
            Physics.OnPhysicsProcessedListener {
            override fun onPhysicsProcessed(physics: Physics, world: World) {
                Log.d(ContentValues.TAG, "onPhysicsProcessed")
            }
        })

        binding.sea.physics.setOnBodyCreatedListener(object : Physics.OnBodyCreatedListener {
            override fun onBodyCreated(view: View, body: Body) {
                Log.d(ContentValues.TAG, "Body created for view ${view.id}")
            }
        })

        Timer().scheduleAtFixedRate(object : TimerTask() {
            override fun run() {
                var conf: Boolean = true

                while (true) {
                    if (!conf) {
                        binding.sea.physics.setGravity(
                            ((Math.random() * 9) * (1)).toFloat(),
                            ((Math.random() * 9) * (1)).toFloat()
                        )
                        conf = true
                    } else {
                        binding.sea.physics.setGravity(
                            ((Math.random() * 9) * (-1)).toFloat(),
                            ((Math.random() * 9) * (-1)).toFloat()
                        )
                        conf = false
                    }
                }

            }
        }, 0, 10000)

        return view
    }

    fun FishCategory(){
        binding.sea.removeAllViewsInLayout()
        var fishtoadd=point-(point/10)*10
        if(fishtoadd>=5){
            fishtoadd-=5
        }
        when(point){
            in 0..4 -> {

                for(i in 0..fishtoadd) {
                    val pez=ImageView(context)
                    pez.setImageResource(R.drawable.pez)
                    Addfish(pez)
                }
            }
            in 5..10 ->{
                for(i in 0..fishtoadd) {
                    val pez=ImageView(context)
                    pez.setImageResource(R.drawable.pezpayaso)
                    Addfish(pez)
                }
            }
            in 11..15->{
                for(i in 0..fishtoadd) {
                    val pez=ImageView(context)
                    pez.setImageResource(R.drawable.medusa)
                    Addfish(pez)
                }
            }
            in 16..20->{
                for(i in 0..fishtoadd) {
                    val pez=ImageView(context)
                    pez.setImageResource(R.drawable.pulpo)
                    Addfish(pez)
                }
            }
            in 21..25->{
                for(i in 0..fishtoadd) {
                    val pez=ImageView(context)
                    pez.setImageResource(R.drawable.mantarraya)
                    Addfish(pez)
                }
            }
            else ->{
                for(i in 0..fishtoadd) {
                    val pez=ImageView(context)
                    pez.setImageResource(R.drawable.whale)
                    val layoutParams = ConstraintLayout.LayoutParams(
                        resources.getDimensionPixelSize(R.dimen.square_size2),
                        resources.getDimensionPixelSize(R.dimen.square_size2)
                    )
                    pez.layoutParams = layoutParams
                    binding.sea.addView(pez)
                }
            }
        }
    }
    fun Addfish(pez:ImageView){
        val layoutParams=ConstraintLayout.LayoutParams(
            resources.getDimensionPixelSize(R.dimen.square_size),
            resources.getDimensionPixelSize(R.dimen.square_size)
        )
        pez.layoutParams=layoutParams
        binding.sea.addView(pez)
    }

}
