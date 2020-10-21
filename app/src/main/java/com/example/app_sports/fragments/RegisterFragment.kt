package com.example.app_sports.fragments

import android.app.DatePickerDialog
import android.os.Bundle
import android.text.TextUtils.isEmpty
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.app_sports.*
import com.example.app_sports.Model.UserData
import java.util.*

class RegisterFragment : Fragment(){

    lateinit var date_btn: Button
    lateinit var setListener: SetListener

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_register, container, false)

        val user_name_et = view.findViewById<EditText>(R.id.editUserName)
        val first_name_et = view.findViewById<EditText>(R.id.editFirstName)
        val last_name_et = view.findViewById<EditText>(R.id.editLastName)
        val email_et = view.findViewById<EditText>(R.id.editEmail)
        val password1_et = view.findViewById<EditText>(R.id.passwordEdit)
        val password2_et = view.findViewById<EditText>(R.id.passwordConfirmEdit)
        val submitBtn = view.findViewById<Button>(R.id.submitRegisterButton)
        val date_et = view.findViewById<EditText>(R.id.date_et)

        date_et.setOnClickListener(View.OnClickListener {
            val c: Calendar = Calendar.getInstance()
            val year = c.get(Calendar.YEAR)
            val month = c.get(Calendar.MONTH)
            val day = c.get(Calendar.DAY_OF_MONTH)

            setListener = SetListener(date_et)

            val datePickerDialog = DatePickerDialog(requireContext(), setListener, year, month, day)
            datePickerDialog.show()
        })
        
        submitBtn.setOnClickListener(View.OnClickListener {
            if (!(isEmpty(user_name_et.text) or isEmpty(first_name_et.text) or isEmpty(last_name_et.text) or isEmpty(email_et.text) or isEmpty(user_name_et.text) or isEmpty(password1_et.text))) {
                if (isPasswordValid(password1_et.text.toString(), password2_et.text.toString())) {
                    val data = UserData(
                    user_name_et.text.toString(),
                    first_name_et.text.toString(),
                    last_name_et.text.toString(),
                    email_et.text.toString(),
                    date_et.text.toString(),
                    password1_et.text.toString()
                    )
                    if (isValid(data, true)) {
                        // TODO: 20/10/20 write data to db
                        Toast.makeText(this.context, "Cool", Toast.LENGTH_SHORT).show()
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