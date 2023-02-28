package com.team.seacondlife;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager.widget.ViewPager.OnPageChangeListener;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.team.UserDataBase.ScannerSQLiteHelper;
import com.team.UserDataBase.UserSQLiteHelper;
import com.team.seacondlife.codescanner.CodeScanner;
import com.team.seacondlife.codescanner.ScannerResult;
import com.team.seacondlife.databinding.ActivityMainBinding;
import com.team.seacondlife.ui.main.SectionsPagerAdapter;

import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationMenuView;
import com.google.android.material.bottomnavigation.BottomNavigationView;


public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private MenuItem prevMenuItem;
    private SectionsPagerAdapter sectionsPagerAdapter;
    private FloatingActionButton cameraButton;
    private UserSQLiteHelper dbhelper=new UserSQLiteHelper(this);
    private ScannerSQLiteHelper schelper=new ScannerSQLiteHelper(this);


    @Override
    @SuppressLint("ResourceAsColor")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setTitle(null);

        //verified that the database works
        //schelper.addNewObject("codigo","botella de agua");
        //Toast.makeText(this,schelper.SearchObject("codigo"),Toast.LENGTH_LONG).show();


        //el adaptador coloca las Pages -los fragmentos con las diferentes vistas- dentro de la vista padre Viewpager del xml
        sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
//        ViewPager viewPager = binding.viewPager;
        ViewPager viewPager1 = findViewById(R.id.view_pager);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            viewPager1.setOutlineSpotShadowColor(R.color.colorAccent);
        }
        viewPager1.setAdapter(sectionsPagerAdapter);
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
        BottomNavigationView mybottomNavView = findViewById(R.id.bottom_navigation);

        // crear badges
        BottomNavigationMenuView bottomNavigationMenuView =
                (BottomNavigationMenuView) mybottomNavView.getChildAt(0);
        View v = bottomNavigationMenuView.getChildAt(0);
        BottomNavigationItemView itemView = (BottomNavigationItemView) v;

        viewPager1.setCurrentItem(1);
        mybottomNavView.getMenu().getItem(1).setChecked(true);

        //LayoutInflater.from(this).inflate(R.layout.layout_badge, itemView, true);


        cameraButton = findViewById(R.id.camera_button);

        cameraButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user=getIntent().getExtras().getString("name");
                String psw=getIntent().getExtras().getString("psw");

                startActivity(new Intent(MainActivity.this, CodeScanner.class).
                        putExtra("user",user).putExtra("passw",psw));
            }
        });



        mybottomNavView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.tips:
                        item.setChecked(true);
                        viewPager1.setCurrentItem(0);
                        break;

                    case R.id.main:
                        item.setChecked(true);
                        viewPager1.setCurrentItem(1);
                        break;

                    case R.id.user_info:
                        item.setChecked(true);
                        viewPager1.setCurrentItem(2);
                        break;
                }
                return false;
            }
        });


//        here we listen to viewpager moves and set navigations items checked or not

        viewPager1.addOnPageChangeListener(new OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (prevMenuItem != null)
                    prevMenuItem.setChecked(false);
                else
                    mybottomNavView.getMenu().getItem(1).setChecked(false);
                mybottomNavView.getMenu().getItem(position).setChecked(true);

                prevMenuItem = mybottomNavView.getMenu().getItem(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.appbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.LogOut) {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            Toast toast = Toast.makeText(this, "Cerrando sesion...", Toast.LENGTH_LONG);
            toast.show();
        }
        if (id == R.id.about) {
            Intent intent = new Intent(this, AboutActivity.class);
            startActivity(intent);
        }
        if (id == R.id.Settings) {
            Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }
}
