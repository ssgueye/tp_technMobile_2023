package com.example.tp_technomobile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment

class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

        Log.d("MainAcivity", "Oncreate()")


    }

    override fun onStart(){
        super.onStart();
        Log.d("MainActivity", "OnStart()")
    }

    override fun onResume(){
        super.onResume();
        Log.d("MainActivity", "onResume()")
    }

    override fun onPause(){
        super.onPause();
        Log.d("MainActivity", "onPause()")
    }

    override fun onStop(){
        super.onStop();
        Log.d("MainActivity", "onStop()")
    }

    override fun onDestroy(){
        super.onDestroy();
        Log.d("MainActivity", "onDestroy()")
    }
}