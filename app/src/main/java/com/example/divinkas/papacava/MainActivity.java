package com.example.divinkas.papacava;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.divinkas.papacava.Adapter.TabsPagerFragmentAdapter;
import com.example.divinkas.papacava.Fragment.CavaFragment;

public class MainActivity extends AppCompatActivity{
    private static final int LAYOUT = R.layout.activity_main;

    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(LAYOUT);
        init();
    }

    private void init() {
        viewPager = findViewById(R.id.viewPager);
        TabsPagerFragmentAdapter tabsPagerFragmentAdapter = new TabsPagerFragmentAdapter(this, getSupportFragmentManager());

        viewPager.setAdapter(tabsPagerFragmentAdapter);
        viewPager.setCurrentItem(0);
        final TabLayout tabLayout  = findViewById(R.id.tabLayoutMain);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public void OnClick(MenuItem item) {
        CavaFragment.show();
    }
}
