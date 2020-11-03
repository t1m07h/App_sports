package com.example.app_sports.login_activity.fragments.register

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.app_sports.*
import com.example.app_sports.login_activity.check_data_register
import com.example.app_sports.login_activity.fragments.viewmodel.ConnectionViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class RegisterFragment : Fragment(){

    private lateinit var ConnViewModel: ConnectionViewModel
    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_register, container, false)
        auth = FirebaseAuth.getInstance()
        ConnViewModel = ViewModelProvider(this).get(ConnectionViewModel::class.java)

        return  view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val email_et = view.findViewById<EditText>(R.id.editEmail)
        val password1_et = view.findViewById<EditText>(R.id.passwordEdit)
        val password2_et = view.findViewById<EditText>(R.id.passwordConfirmEdit)
        val submitBtn = view.findViewById<Button>(R.id.submitRegisterButton)

        submitBtn.setOnClickListener(View.OnClickListener {
            if (check_data_register(email_et, password1_et, password2_et)) {

                auth.createUserWithEmailAndPassword(email_et.text.toString(), password1_et.text.toString())
                    .addOnCompleteListener(requireActivity()) { task ->
                        if (task.isSuccessful) {
                            val user = auth.currentUser
                            user!!.sendEmailVerification().addOnCompleteListener { task ->
                                if (task.isSuccessful) {
                                    Toast.makeText(this.context, "A verification email has been sent to you", Toast.LENGTH_SHORT).show()
                                    updateUI(user)
                                }
                            }
                            Toast.makeText(this.context, "Signed up successfully !", Toast.LENGTH_SHORT).show()
                        } else {
                            Toast.makeText(this.context, "Sign up failed, try again later", Toast.LENGTH_SHORT).show()
                        }
                    }
            }
        })
    }

    fun updateUI(currentUser: FirebaseUser?) {
        if (currentUser != null) {
                findNavController().navigate(R.id.action_registerFragment_to_completeProfileFragment)
        } else {
            Toast.makeText(this.context, "Registration failed, try again later", Toast.LENGTH_SHORT).show()
        }
    }
}