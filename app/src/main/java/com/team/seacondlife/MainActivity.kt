package com.team.seacondlife

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.widget.Toast
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import androidx.viewpager.widget.ViewPager.OnPageChangeListener
import com.team.seacondlife.databinding.ActivityMainBinding
import com.team.seacondlife.ui.main.SectionsPagerAdapter

import com.google.android.material.bottomnavigation.BottomNavigationItemView
import com.google.android.material.bottomnavigation.BottomNavigationMenuView
import com.google.android.material.bottomnavigation.BottomNavigationView

/*
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

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.appbar, menu)
        return true
    }
}

*/

class MainActivity : AppCompatActivity() {
    private var binding: ActivityMainBinding? = null
    private var prevMenuItem: MenuItem? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //binding = ActivityMainBnBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        //el adaptador coloca las Pages -los fragmentos con las diferentes vistas- dentro de la vista padre Viewpager del xml
        val sectionsPagerAdapter = SectionsPagerAdapter(
            this,
            supportFragmentManager
        )
        val viewPager: ViewPager = binding?.viewPager!!
        viewPager.adapter = sectionsPagerAdapter
        //        TabLayout tabs = binding.tabs;
//        tabs.setupWithViewPager(viewPager);
//        FloatingActionButton fab = binding.fab;
//
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });


// cast al xml
        val mybottomNavView = findViewById<BottomNavigationView>(R.id.bottom_navigation)

        // crear badges
        val bottomNavigationMenuView = mybottomNavView.getChildAt(0) as BottomNavigationMenuView
        val v = bottomNavigationMenuView.getChildAt(2)
        val itemView = v as BottomNavigationItemView
        //LayoutInflater.from(this).inflate(R.layout.layout_badge, itemView, true)
        mybottomNavView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.likes -> {
                    item.isChecked = true
                    //Toast.makeText(this@MainActivity, "Likes clicked.", Toast.LENGTH_SHORT).show()
                    //removeBadge(mybottomNavView, item.itemId)
                    viewPager.currentItem = 0
                }
                R.id.add -> {
                    item.isChecked = true
                    //Toast.makeText(this@MainActivity, "Add clicked.", Toast.LENGTH_SHORT).show()
                    //removeBadge(mybottomNavView, item.itemId)
                    viewPager.currentItem = 1
                }
                R.id.browse -> {
                    item.isChecked = true
                    //Toast.makeText(this@MainActivity, "Add clicked.", Toast.LENGTH_SHORT).show()
                    //removeBadge(mybottomNavView, item.itemId)
                    viewPager.currentItem = 2
                }
                R.id.personal -> {
                    item.isChecked = true
                    //Toast.makeText(this@MainActivity, "Add clicked.", Toast.LENGTH_SHORT).show()
                    //removeBadge(mybottomNavView, item.itemId)
                    viewPager.currentItem = 3
                }
            }
            false
        }


//        here we listen to viewpager moves and set navigations items checked or not
        viewPager.addOnPageChangeListener(object : OnPageChangeListener {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
            }

            override fun onPageSelected(position: Int) {
                if (prevMenuItem != null) prevMenuItem!!.isChecked =
                    false else mybottomNavView.menu.getItem(0).isChecked =
                    false
                mybottomNavView.menu.getItem(position).isChecked = true
                removeBadge(mybottomNavView, mybottomNavView.menu.getItem(position).itemId)
                prevMenuItem = mybottomNavView.menu.getItem(position)
            }

            override fun onPageScrollStateChanged(state: Int) {}
        })
    }

    companion object {
        //removing badges
        fun removeBadge(bottomNavigationView: BottomNavigationView, @IdRes itemId: Int) {
            val itemView = bottomNavigationView.findViewById<BottomNavigationItemView>(itemId)
            if (itemView.childCount == 3) {
                itemView.removeViewAt(2)
            }
        }
    }
}
