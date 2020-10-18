package com.example.app_sports

import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.fragment_register.*
import java.util.*

class RegisterFragment : Fragment, DatePickerDialog.OnDateSetListener{

    val date_btn: Button
    val setListener: DatePickerDialog.OnDateSetListener

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_register, container, false)


        date_btn = getView().findViewById(R.id.date_button)
        return  view
    }

//    fun showDatePickerDialog(v: View) {
//        val fragmentManager = parentFragmentManager
//        val dateFragment = DatePickerFragment()
//        dateFragment.show(fragmentManager, "datePicker")
//    }

}