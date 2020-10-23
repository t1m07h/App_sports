package com.example.app_sports

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.app_sports.fragments.RegisterFragment

class LoginActivity : AppCompatActivity() {
    val TAG = "application"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        setupActionBarWithNavController(findNavController(R.id.fragment))

    }

    override fun onSupportNavigateUp(): Boolean {
        val nav_controller = findNavController(R.id.fragment)
        return nav_controller.navigateUp() || super.onSupportNavigateUp()
    }
}