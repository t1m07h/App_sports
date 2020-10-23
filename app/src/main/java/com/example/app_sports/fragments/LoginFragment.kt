package com.example.app_sports.fragments

import android.os.Bundle
import android.text.TextUtils.isEmpty
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.app_sports.R
import com.example.app_sports.isValid
import com.example.app_sports.Model.UserData

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
                var data = UserData(email_et.text.toString(), password_et.text.toString())
                if (isValid(data, false)) {
                    // TODO: 23/10/20 request server to connect and if not registered go to register fragment
                    findNavController().navigate(R.id.action_loginFragment2_to_registerFragment)
                }
            } else {
                Toast.makeText(this.context, "Please fill out all fields", Toast.LENGTH_SHORT)
                    .show()
            }
        })

        return view
    }
}