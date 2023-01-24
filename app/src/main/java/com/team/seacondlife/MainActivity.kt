package com.team.seacondlife


import android.annotation.SuppressLint
import android.content.ContentValues
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import com.jawnnypoo.physicslayout.Physics
import com.team.seacondlife.databinding.ActivityMainBinding
import org.jbox2d.dynamics.Body
import org.jbox2d.dynamics.World
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding:ActivityMainBinding
    private var index: Int=0
    private var cont: Int=0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        this.title = null

        binding.sea.physics.isFlingEnabled = true
        binding.sea.physics.isPhysicsEnabled
        binding.sea.physics.isFlingEnabled

        for(i in 0 until binding.sea.childCount){
            val imageView=binding.sea.getChildAt(i) as ImageView
            imageView.id=i
            imageView.setImageResource(R.drawable.pez)
        }
        index=binding.sea.childCount+1;

        binding.sea.setOnLongClickListener {
            binding.sea.physics.giveRandomImpulse()
            return@setOnLongClickListener true
        }

        binding.sea.physics.setOnCollisionListener(object: Physics.OnCollisionListener{
            @SuppressLint("SetTextIl8n")
            override fun onCollisionEntered(viewIdA:Int,viewIdB: Int){
                Log.d(ContentValues.TAG,"$viewIdB")
                if(viewIdB!=0) {
                    binding.animal.setImageResource(R.drawable.mantarraya)
                }else{
                    binding.animal.setImageResource(R.drawable.pezpayaso)
                }
            }
            override fun onCollisionExited(viewIdA: Int, viewIdB: Int){}
        })


        if(UpdateInDB()) {
            val pez=ImageView(this)
            pez.setImageResource(R.drawable.pulpo)
            val layoutParams = ConstraintLayout.LayoutParams(
                resources.getDimensionPixelSize(R.dimen.square_size),
                resources.getDimensionPixelSize(R.dimen.square_size)
            )
            pez.layoutParams=layoutParams
            pez.id=index
            binding.sea.addView(pez)
            index++
        }


        binding.sea.physics.addOnPhysicsProcessedListener(object :Physics.OnPhysicsProcessedListener{
            override fun onPhysicsProcessed(physics: Physics, world: World) {
                Log.d(ContentValues.TAG,"onPhysicsProcessed")
            }
        })

        binding.sea.physics.setOnBodyCreatedListener(object : Physics.OnBodyCreatedListener {
            override fun onBodyCreated(view: View, body: Body) {
                Log.d(ContentValues.TAG, "Body created for view ${view.id}")
            }
        })

        Timer().scheduleAtFixedRate(object:TimerTask(){
            override fun run(){
                var conf:Boolean=true;

                if(!conf) {
                    binding.sea.physics.setGravity(
                        ((Math.random() * 9) * (0.1)).toFloat(),
                        ((Math.random() * 9) * (0.1)).toFloat()
                    )
                    conf=true
                }else{
                    binding.sea.physics.setGravity(
                        ((Math.random() * 9) * (-0.1)).toFloat(),
                        ((Math.random() * 9) * (-0.1)).toFloat()
                    )
                    conf=false
                }

            }
        },0,10000)
    }


    fun UpdateInDB(): Boolean {
        return false
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.appbar, menu)
        return true
    }
}

