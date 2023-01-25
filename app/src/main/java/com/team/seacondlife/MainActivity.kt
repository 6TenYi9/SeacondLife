package com.team.seacondlife

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
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

        this.setTitle(null)

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
                while(true) {
                    if (!conf) {
                        binding.sea.physics.setGravity(
                            ((Math.random() * 9) * (0.1)).toFloat(),
                            ((Math.random() * 9) * (0.1)).toFloat()
                        )
                        conf = true
                    } else {
                        binding.sea.physics.setGravity(
                            ((Math.random() * 9) * (-0.1)).toFloat(),
                            ((Math.random() * 9) * (-0.1)).toFloat()
                        )
                        conf = false
                    }
                }
            }
        },0,10000)
    }


    fun UpdateInDB(): Boolean {
        return false
    }



    //Menu de arriba
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.appbar, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val id = item.itemId
        if (id == R.id.LogOut) {
            val intent = Intent(this, act_tips::class.java)
            startActivity(intent)
            val toast = Toast.makeText(this, "Cerrando sesion...", Toast.LENGTH_LONG)
            toast.show()
        }
        if (id == R.id.about) {
            val intent = Intent(this, AboutActivity::class.java)
            startActivity(intent)
        }
        if (id == R.id.Settings) {
            val intent = Intent(this, SettingsActivity::class.java)
            startActivity(intent)
        }

        return super.onOptionsItemSelected(item)
    }
}