package com.team.seacondlife;

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
        View v = bottomNavigationMenuView.getChildAt(2);
        BottomNavigationItemView itemView = (BottomNavigationItemView) v;

        //LayoutInflater.from(this).inflate(R.layout.layout_badge, itemView, true);


        mybottomNavView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.likes:
                    item.setChecked(true);
                    Toast.makeText(MainActivity.this, "Likes clicked.", Toast.LENGTH_SHORT).show();
                    removeBadge(mybottomNavView,item.getItemId());
                    viewPager1.setCurrentItem(0);
                    break;

                    case R.id.add:
                    item.setChecked(true);
                    Toast.makeText(MainActivity.this, "Add clicked.", Toast.LENGTH_SHORT).show();
                    removeBadge(mybottomNavView,item.getItemId());
                    viewPager1.setCurrentItem(1);
                    break;

                    case R.id.browse:
                    item.setChecked(true);
                    Toast.makeText(MainActivity.this, "Browse clicked.", Toast.LENGTH_SHORT).show();
                    removeBadge(mybottomNavView,item.getItemId());
                    viewPager1.setCurrentItem(2);
                    break;

                    case R.id.personal:
                    item.setChecked(true);
                    Toast.makeText(MainActivity.this, "Personal clicked.", Toast.LENGTH_SHORT).show();
                    removeBadge(mybottomNavView,item.getItemId());
                    viewPager1.setCurrentItem(3);
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
                    mybottomNavView.getMenu().getItem(0).setChecked(false);
                mybottomNavView.getMenu().getItem(position).setChecked(true);
                removeBadge(mybottomNavView,mybottomNavView.getMenu().getItem(position).getItemId());
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

}