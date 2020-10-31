package com.example.app_sports.login_activity.fragments

import android.os.Bundle
import android.text.TextUtils.isEmpty
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.app_sports.R
import com.example.app_sports.login_activity.isValid
import com.example.app_sports.Model.UserData
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.app_sports.URL
import com.google.gson.Gson

class LoginFragment: Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_login, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val email_et = view.findViewById<EditText>(R.id.emailLoginEdit)
        val password_et = view.findViewById<EditText>(R.id.passwordLoginEdit)
        val submit_btn = view.findViewById<Button>(R.id.login_or_register_btn)
        val create_account_tv = view.findViewById<TextView>(R.id.create_account)

        submit_btn.setOnClickListener(View.OnClickListener {
            if (!(isEmpty(email_et.text.toString()) or isEmpty(password_et.text.toString()))) {
                var data = UserData(0, email_et.text.toString(), password_et.text.toString())

                if (isValid(data, false)){
                    val queue = Volley.newRequestQueue(this.context)

                    val stringRequest = StringRequest(URL, Response.Listener<String> { response ->
                        val user: UserData = Gson().fromJson(response, UserData::class.java)
                        // TODO: 28/10/20 check for the response and go to register if user not found or login
                        // TODO: 25/10/20 login (save the data in the local db and start new user activity )
                        Toast.makeText(this.context, "${response.substring(0, 500)}", Toast.LENGTH_SHORT).show()
                    }, Response.ErrorListener { response ->
                        Toast.makeText(this.context, "${response}", Toast.LENGTH_SHORT).show() })
                    queue.add(stringRequest)
                }
            } else {
                Toast.makeText(this.context, "Please fill out all fields", Toast.LENGTH_SHORT).show()
            }
        })

        create_account_tv.setOnClickListener(View.OnClickListener {
            findNavController().navigate(R.id.action_loginFragment2_to_registerFragment)
        })
    }
}