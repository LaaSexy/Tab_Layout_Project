package com.example.tab_layout_project

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.example.tab_layout_project.adapter.ViewPagerAdapter
import com.example.tab_layout_project.auth.LoginActivity
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {

    private lateinit var viewPager: ViewPager2
    private lateinit var tabLayout: TabLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (!isUserLoggedIn()) {
            redirectToLogin()
            return
        }
        initializeViews()
        setupViewPager()
        setupTabLayout()
    }

    private fun initializeViews() {
        viewPager = findViewById(R.id.viewPager)
        tabLayout = findViewById(R.id.tabLayout)
    }

    private fun setupViewPager() {
        val adapter = ViewPagerAdapter(this)
        viewPager.adapter = adapter
    }

    private fun setupTabLayout() {
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = when (position) {
                0 -> "Home"
                1 -> "Payment"
                2 -> "History"
                3 -> "Profile"
                else -> null
            }
        }.attach()
    }

    private fun isUserLoggedIn(): Boolean {
        val sharedPreferences = getSharedPreferences("app_prefs", MODE_PRIVATE)
        return sharedPreferences.getBoolean("is_logged_in", false)
    }

    private fun redirectToLogin() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }

    fun logout() {
        val sharedPreferences = getSharedPreferences("app_prefs", MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putBoolean("is_logged_in", false)
        editor.apply()
        redirectToLogin()
    }
}