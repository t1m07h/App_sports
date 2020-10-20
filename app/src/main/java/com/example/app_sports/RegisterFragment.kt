package com.example.app_sports

import android.app.DatePickerDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
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
        val date_tv = view.findViewById<EditText>(R.id.tv_date)

        date_tv.setOnClickListener(View.OnClickListener {
            val c: Calendar = Calendar.getInstance()
            val year = c.get(Calendar.YEAR)
            val month = c.get(Calendar.MONTH)
            val day = c.get(Calendar.DAY_OF_MONTH)

            setListener = SetListener(date_tv)

            val datePickerDialog = DatePickerDialog(requireContext(), setListener, year, month, day)
            datePickerDialog.show()
        })
        
        submitBtn.setOnClickListener(View.OnClickListener {
            // TODO: 20/10/20 check for password then create UserData class
            if (isValid(data)) {
                // TODO: 20/10/20 write data to db
            }
        })

        return  view
    }

    fun isValid(data: UserData, new: Boolean) {
        if (new) {
        } else {
        }
    }

}