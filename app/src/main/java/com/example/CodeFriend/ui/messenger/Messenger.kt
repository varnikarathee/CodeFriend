package com.example.CodeFriend.ui.messenger

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.CodeFriend.R
import com.example.CodeFriend.ui.main.ViewModelMain
import com.example.CodeFriend.firebase.FirebaseService
import com.example.CodeFriend.utils.Status
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_messenger.*

private const val TAG = "Messenger"

@AndroidEntryPoint
class Messenger : AppCompatActivity() {


    private val viewModel: ViewModelMain by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_messenger)
        FirebaseService.sharedPref=getSharedPreferences("sharedPref", Context.MODE_PRIVATE)

        uploadToken()

        setupBottomNavigation()
    }


    private fun setupBottomNavigation() {
        val hostFragment=supportFragmentManager.findFragmentById(R.id.my_nav_host_fragment) as NavHostFragment
        val navController=hostFragment.navController
        bottom_navigation.setupWithNavController(navController)
    }

    private fun uploadToken() {
        viewModel.uploadToken()
        viewModel.getUploadState().observe(this) {
            when (it.status) {
                Status.SUCCESS -> {
                    Log.d(TAG, "uploadToken: ${it.data}")
                }
                Status.ERROR -> {
                    Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                }
            }
        }
    }

}