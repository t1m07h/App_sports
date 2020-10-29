package com.example.app_sports.fragments

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils.isEmpty
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
import com.example.app_sports.pickers.SetListener
import com.example.app_sports.viewmodel.ConnectionViewModel
import java.util.*

class RegisterFragment : Fragment(){

    lateinit var date_btn: Button
    lateinit var setListener: SetListener
    private lateinit var ConnViewModel: ConnectionViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_register, container, false)

        ConnViewModel = ViewModelProvider(this).get(ConnectionViewModel::class.java)

        val user_name_et = view.findViewById<EditText>(R.id.editUserName)
        val first_name_et = view.findViewById<EditText>(R.id.editFirstName)
        val last_name_et = view.findViewById<EditText>(R.id.editLastName)
        val email_et = view.findViewById<EditText>(R.id.editEmail)
        val password1_et = view.findViewById<EditText>(R.id.passwordEdit)
        val password2_et = view.findViewById<EditText>(R.id.passwordConfirmEdit)
        val submitBtn = view.findViewById<Button>(R.id.submitRegisterButton)
        val date_et = view.findViewById<EditText>(R.id.date_et)
        val main_sport_tv = view.findViewById<TextView>(R.id.mainSportEdit)

        date_et.setOnClickListener(View.OnClickListener {
//            val c: Calendar = Calendar.getInstance()
//            val year = c.get(Calendar.YEAR)
//            val month = c.get(Calendar.MONTH)
//            val day = c.get(Calendar.DAY_OF_MONTH)

            setListener = SetListener(date_et)

            val datePickerDialog = DatePickerDialog(requireContext(), setListener, 2000, 1, 1)
            datePickerDialog.show()
        })
        
        submitBtn.setOnClickListener(View.OnClickListener {
            if (!(isEmpty(user_name_et.text) or isEmpty(first_name_et.text) or isEmpty(last_name_et.text) or isEmpty(email_et.text) or isEmpty(user_name_et.text) or isEmpty(password1_et.text))) {
                if (isPasswordValid(password1_et.text.toString(), password2_et.text.toString())) {

                    var new = UserData(0, email_et.text.toString(), password1_et.text.toString())
                    new.userName = user_name_et.text.toString()
                    new.firstName = first_name_et.text.toString()
                    new.lastName = last_name_et.text.toString()
                    new.birthDate = date_et.text.toString()
                    new.mainSport = main_sport_tv.text.toString()
                    if (isValid(new, true)) {
                        ConnViewModel.add_user(new)
                        // TODO: 28/10/20 add the new user to the distant db
                        // TODO: 28/10/20 now connect and navigate to main_activity
                        val intent: Intent = Intent(this, HomeActivity::class.java)

                        startActivity(intent)
                    }
                } else {
                    Toast.makeText(this.context, "Passwords don't match", Toast.LENGTH_SHORT).show()
                }
            } else
                Toast.makeText(this.context, "Fill out all fields please", Toast.LENGTH_SHORT).show()
        })

        return  view
    }

    fun isPasswordValid(pass1: String, pass2: String): Boolean {
        // TODO: 21/10/20
        return true
    }

}