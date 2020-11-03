package com.example.app_sports.login_activity.fragments.login

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.app_sports.R
import com.example.app_sports.home_activity.HomeActivity
import com.example.app_sports.login_activity.check_data_login
import com.example.app_sports.login_activity.fragments.viewmodel.ConnectionViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class LoginFragment: Fragment() {

    private lateinit var connectionViewModel: ConnectionViewModel
    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_login, container, false)
        auth = FirebaseAuth.getInstance()
        connectionViewModel = ViewModelProvider(this).get(ConnectionViewModel::class.java)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val email_et = view.findViewById<EditText>(R.id.emailLoginEdit)
        val password_et = view.findViewById<EditText>(R.id.passwordLoginEdit)
        val submit_btn = view.findViewById<Button>(R.id.login_or_register_btn)
        val create_account_tv = view.findViewById<TextView>(R.id.create_account)

        submit_btn.setOnClickListener(View.OnClickListener {
            if (check_data_login(email_et, password_et)) {

                auth.signInWithEmailAndPassword(email_et.text.toString(), password_et.text.toString())
                    .addOnCompleteListener(requireActivity()) { task ->
                        if (task.isSuccessful) {
                            val user = auth.currentUser
                                updateUI(user)
                        } else {
                            Toast.makeText(this.context, "Login failed ! Please try again later", Toast.LENGTH_SHORT).show()
                        }
                    }
            }
        })

        create_account_tv.setOnClickListener(View.OnClickListener {
            findNavController().navigate(R.id.action_loginFragment2_to_registerFragment)
        })
    }

    override fun onStart() {
        super.onStart()
        val currentUser: FirebaseUser? = auth.currentUser
        updateUI(currentUser)
    }

    fun updateUI(currentUser: FirebaseUser?) {
        if (currentUser != null) {
            // TODO: 02/11/20 check if the user has completed his profile
            if (currentUser.isEmailVerified) {
                val intent: Intent = Intent(this.context, HomeActivity::class.java)
                startActivity(intent)
                requireActivity().finish()
            } else {
                Toast.makeText(this.context, "Please check your emails", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(this.context, "Failed to login", Toast.LENGTH_SHORT).show()
        }
    }
}