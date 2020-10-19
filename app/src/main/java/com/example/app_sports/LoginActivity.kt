package com.example.app_sports

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_login.*
import org.w3c.dom.Text
import java.io.IOException
import java.lang.Exception

class LoginActivity : AppCompatActivity() {
    val TAG = "application"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        var uName: String?
        var pass: String?

        val connect_btn: Button = findViewById(R.id.login_or_register_btn)
        val editName = findViewById(R.id.userNameEdit) as EditText
        val editPass = findViewById(R.id.passwordEdit) as EditText

        connect_btn.setOnClickListener {
            uName = editName.text.toString()
            pass = editPass.text.toString()
            if (!TextUtils.isEmpty(uName) or !TextUtils.isEmpty(pass)) {
                // TODO: 15/10/20 request server to connect and if not registered go to register fragment
            } else {
                val fragmentTransaction = supportFragmentManager.beginTransaction()
                val fragment = RegisterFragment()
                fragmentTransaction.add(R.id.login, fragment)
                fragmentTransaction.commit()
                // TODO: 15/10/20 go to register fragment
            }
        }
    }
}