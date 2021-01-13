package com.example.projectjuicyfruit

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.projectjuicyfruit.screens.DashboardFragment

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.new_activity_main)

//        supportFragmentManager
//            .beginTransaction()
//            .replace(R.id.nav_host_fragment_container, DashboardFragment())
//            .commit()

        /*   // nav controller
            val navHostFragment =
                supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
            val navController = navHostFragment.navController
    */
    }
}



