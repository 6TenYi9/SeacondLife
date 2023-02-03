package com.team.seacondlife;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager.widget.ViewPager.OnPageChangeListener;

import com.team.seacondlife.databinding.ActivityMainBinding;
import com.team.seacondlife.ui.main.SectionsPagerAdapter;

import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationMenuView;
import com.google.android.material.bottomnavigation.BottomNavigationView;


public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private MenuItem prevMenuItem;
    private SectionsPagerAdapter sectionsPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setTitle(null);

        //el adaptador coloca las Pages -los fragmentos con las diferentes vistas- dentro de la vista padre Viewpager del xml
        sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
//        ViewPager viewPager = binding.viewPager;
        ViewPager viewPager1 = findViewById(R.id.view_pager);
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


        mybottomNavView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.tips:
                        item.setChecked(true);
                        Toast.makeText(MainActivity.this, "TIPS", Toast.LENGTH_SHORT).show();
                        removeBadge(mybottomNavView, item.getItemId());
                        viewPager1.setCurrentItem(0);
                        break;

                    case R.id.main:
                        item.setChecked(true);
                        Toast.makeText(MainActivity.this, "MAIN", Toast.LENGTH_SHORT).show();
                        removeBadge(mybottomNavView, item.getItemId());
                        viewPager1.setCurrentItem(1);
                        break;

                    case R.id.user_info:
                        item.setChecked(true);
                        Toast.makeText(MainActivity.this, "USER INFO", Toast.LENGTH_SHORT).show();
                        removeBadge(mybottomNavView, item.getItemId());
                        viewPager1.setCurrentItem(2);
                        break;
                }
                return false;
            }
        });


//        here we listen to viewpager moves and set navigations items checked or not

        viewPager1.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
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
                removeBadge(mybottomNavView, mybottomNavView.getMenu().getItem(position).getItemId());
                prevMenuItem = mybottomNavView.getMenu().getItem(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    /**
     * Remove badge.
     *
     * @param bottomNavigationView the bottom navigation view
     * @param itemId               the item id
     */
//removing badges
    public static void removeBadge(BottomNavigationView bottomNavigationView, @IdRes int itemId) {
        BottomNavigationItemView itemView = bottomNavigationView.findViewById(itemId);
        if (itemView.getChildCount() == 3) {
            itemView.removeViewAt(2);
        }
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
