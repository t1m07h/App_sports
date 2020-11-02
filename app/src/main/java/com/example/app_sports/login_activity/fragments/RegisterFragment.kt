package com.example.app_sports.login_activity.fragments

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils.isEmpty
import android.util.Log
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.app_sports.*
import com.example.app_sports.Model.UserData
import com.example.app_sports.home_activity.HomeActivity
import com.example.app_sports.login_activity.isValid
import com.example.app_sports.login_activity.pickers.SetListener
import com.example.app_sports.login_activity.viewmodel.ConnectionViewModel
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_home.*

class RegisterFragment : Fragment(){

    lateinit var date_btn: Button
    lateinit var setListener: SetListener
    private lateinit var ConnViewModel: ConnectionViewModel
    private val sportsList = arrayOf<String>("Bike", "Running", "Swimming", "Tennis", "BasketBall")
    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_register, container, false)
        auth = FirebaseAuth.getInstance()
        ConnViewModel = ViewModelProvider(this).get(ConnectionViewModel::class.java)

        val user_name_et = view.findViewById<EditText>(R.id.editUserName)
        val email_et = view.findViewById<EditText>(R.id.editEmail)
        val password1_et = view.findViewById<EditText>(R.id.passwordEdit)
        val password2_et = view.findViewById<EditText>(R.id.passwordConfirmEdit)
        val main_sport_tv = view.findViewById<TextView>(R.id.mainSportEdit)
        val submitBtn = view.findViewById<Button>(R.id.submitRegisterButton)

//        date_et.setOnClickListener(View.OnClickListener {
//            setListener = SetListener(date_et)
//            val datePickerDialog = DatePickerDialog(requireContext(), setListener, 2000, 1, 1)
//            datePickerDialog.show()
//        })

        main_sport_tv.setOnClickListener(View.OnClickListener {
            sportsAlertDialog(requireContext(), main_sport_tv)
        })
        
        submitBtn.setOnClickListener(View.OnClickListener {
            if (check_data(email_et, password1_et, password2_et, user_name_et)) {
                var new = UserData(0, email_et.text.toString(), password1_et.text.toString())
                new.userName = user_name_et.text.toString()
                new.mainSport = main_sport_tv.text.toString()
                ConnViewModel.add_user((new))

                auth.createUserWithEmailAndPassword(new.email, new.password)
                    .addOnCompleteListener(activity!!.parent) { task ->
                        if (task.isSuccessful) {
                            val user = auth.currentUser
                            // TODO: 01/11/20 go to the informations provider fragment, start main_activity then finish the current one
//                            val intent: Intent = Intent(this, HomeActivity::class.java)
//                            startActivity(intent)
//                            activity.finish()
                        } else {
                            Toast.makeText(this.context, "Sign up failed, try again later", Toast.LENGTH_SHORT).show()
                        }
                    }
            }
        })

        return  view
    }

    fun check_data(email: EditText, password: EditText, password2: EditText, userName: EditText): Boolean {
        var result: Boolean = true

        if (isEmpty(email.text.toString())) {
            email.error = "Please enter email"
            email.requestFocus()
            result = false
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email.text.toString()).matches()) {
            email.error = "Please enter valid email"
            email.requestFocus()
            result = false
        }

        if (isEmpty(password.text.toString())) {
            password.error = "Please enter password"
            password.requestFocus()
            result = false
        } else if (isEmpty(password2.text.toString())) {
            password2.error = "Please type in the same password"
            password2.requestFocus()
            result = false
        } else if (password.text.toString().compareTo(password2.text.toString()) != 0) {
            Toast.makeText(this.context, "Password don't matche", Toast.LENGTH_SHORT).show()
            result = false
        }

        if (isEmpty(userName.text.toString())) {
            userName.error = "Please enter a user name"
            userName.requestFocus()
            result = false
        }
        return result
    }

    fun sportsAlertDialog(context: Context, tv: TextView) {
        val builder = AlertDialog.Builder(context)
        with(builder) {
            setTitle("What is your favorite sport ?")
            setItems(sportsList) { dialog, which ->
                tv.setText(sportsList[which])
            }
            show()
        }
    }

    fun register(email: String, password: String) {
    }
}