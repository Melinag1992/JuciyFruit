package com.example.projectjuicyfruit

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.projectjuicyfruit.screens.DashboardFragment
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.new_activity_main)
    }
}



