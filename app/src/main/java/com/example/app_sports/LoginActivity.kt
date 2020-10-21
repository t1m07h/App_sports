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

//        val fragmentTransaction = supportFragmentManager.beginTransaction()
        setupActionBarWithNavController(findNavController(R.id.fragment))

//        var uName: String?
//        var pass: String?
//
//        val connect_btn: Button = findViewById(R.id.login_or_register_btn)
//        val editName = findViewById(R.id.userNameLoginEdit) as EditText
//        val editPass = findViewById(R.id.passwordLoginEdit) as EditText
//
//        connect_btn.setOnClickListener {
//            uName = editName.text.toString()
//            pass = editPass.text.toString()
//            if (!TextUtils.isEmpty(uName) or !TextUtils.isEmpty(pass)) {
//                // TODO: 15/10/20 request server to connect and if not registered go to register fragment
//            } else {
//                val fragment = RegisterFragment()
//                fragmentTransaction.replace(R.id.main, fragment)
//                fragmentTransaction.commit()
//                // TODO: 15/10/20 go to register fragment
//            }
//        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val nav_controller = findNavController(R.id.fragment)
        return nav_controller.navigateUp() || super.onSupportNavigateUp()
    }
}