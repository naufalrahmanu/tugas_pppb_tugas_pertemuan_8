package com.example.tugas_p8

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {
    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager: ViewPager2


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.apply {
            title = "Mytablayout"
            setDisplayHomeAsUpEnabled(true)  // Show the back button on the ActionBar
        }

        tabLayout = findViewById(R.id.tabLayout)
        viewPager = findViewById(R.id.viewPager)

        viewPager.adapter = FragmentAdapter(this)

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = when (position) {
                0 -> "REGISTER"
                1 -> "LOGIN"
                    else -> null
            }
        }.attach()

        tabLayout.setSelectedTabIndicatorColor(getColor(R.color.colorPrimary))  // Sets indicator color
        tabLayout.setTabTextColors(getColor(R.color.gray), getColor(R.color.colorPrimary))  // Unselected and selected tab colors
    }
}

class FragmentAdapter(activity: AppCompatActivity) : FragmentStateAdapter(activity) {
    override fun getItemCount(): Int {
        return 2  // We have two fragments: Register and Login
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> RegisterFragment()  // First tab is Register
            1 -> LoginFragment()     // Second tab is Login
            else -> throw IllegalStateException("Unexpected position $position")
        }
    }
}
