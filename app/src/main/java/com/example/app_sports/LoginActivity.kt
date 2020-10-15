package com.example.app_sports

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.io.IOException
import java.lang.Exception

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var uName: String?
        var pass: String?

        val connect_btn = findViewById(R.id.login_or_register_btn)
        val editName = findViewById(R.id.userNameEdit)
        val editPass = findViewById(R.id.passwordEdit)

        connect_btn.setOnClickListener {
            try {
                uName = editName.text
                pass = editPass.text
            } catch (e: Exception) {
                Log.e(TAG, "Error fetching name or password")
            }
            if (!TextUtils.isEmpty(uName) or !TextUtils.isEmpty(pass)) {
                // TODO: 15/10/20 request server to connect and if not registered go to register fragment
            } else {
                // TODO: 15/10/20 go to register fragment
            }
        }
    }
}