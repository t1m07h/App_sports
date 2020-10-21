package com.example.app_sports.fragments

import android.os.Bundle
import android.service.autofill.UserData
import android.text.TextUtils.isEmpty
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.app_sports.R
import com.example.app_sports.isValid

class LoginFragment: Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_login, container, false)

        val email_et = view.findViewById<EditText>(R.id.emailLoginEdit)
        val password_et = view.findViewById<EditText>(R.id.passwordLoginEdit)
        val submit_btn = view.findViewById<Button>(R.id.login_or_register_btn)

        submit_btn.setOnClickListener(View.OnClickListener {
            if (!(isEmpty(email_et.text.toString()) or isEmpty(password_et.text.toString()))) {
                val data = UserData(
                    null,
                    null,
                    null,
                    email_et.text.toString(),
                    null,
                    password_et.text.toString()
                )
                if (isValid(data, false)) {
                    // TODO: 21/10/20 try login
                    Toast.makeText(this.context, "Cool too", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this.context, "Please fill out all fields", Toast.LENGTH_SHORT).show()
            }
        })

        return view
    }
}