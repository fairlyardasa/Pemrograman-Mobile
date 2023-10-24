package com.example.p8_tugas

import TabAdapter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.example.p8_tugas.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {
    private lateinit var tabLayout : TabLayout
    lateinit var viewPager: ViewPager2
    private lateinit var binding : ActivityMainBinding

    companion object{
        var USERNAME = "username"
        var PASSWORD = "password"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tabLayout = findViewById(R.id.tab_layout)
        viewPager = findViewById(R.id.View_pager)

//        tabLayout.addTab(tabLayout.newTab().setText("Register"))
//        tabLayout.addTab(tabLayout.newTab().setText("Login"))
        tabLayout.tabGravity = TabLayout.GRAVITY_FILL

        val adapter = TabAdapter(this@MainActivity, tabLayout.tabCount)
        viewPager.adapter = adapter

        val tabLayout = findViewById<TabLayout>(R.id.tab_layout)
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            // You can set tab text or custom views here
            when (position) {
                0 -> tab.text = "Register"
                1 -> tab.text = "Login"
                else -> throw IllegalArgumentException("Invalid tab position: $position")
            }
        }.attach()
    }
}