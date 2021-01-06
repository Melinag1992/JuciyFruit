package com.example.projectjuicyfruit

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.projectjuicyfruit.adapters.RecyclerAdapter

lateinit var  rec_view: RecyclerView

var  listOfItems = arrayOf("One","two","three","four","five","six")


class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        inflateView()

    /*   // nav controller
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
*/
    }


    fun inflateView() {
        // Inflating recview
        var adapter = RecyclerAdapter()
        rec_view = findViewById(R.id.rec_view);
        rec_view.visibility = View.VISIBLE
        val gridLayout = LinearLayoutManager(this)
        rec_view.layoutManager = gridLayout
        rec_view.adapter = adapter
        adapter.setList(listOfItems)

        rec_view.isNestedScrollingEnabled = false


    }

}



