package com.example.app_sports.home_activity

import android.app.DatePickerDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.widget.DatePicker
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import com.example.app_sports.login_activity.pickers.DateSetListener
import java.util.*

class DatePickerFragment(val date_tv: TextView): DialogFragment() {
	private lateinit var c: Calendar

	override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
		c = Calendar.getInstance()

		val year = c.get(Calendar.YEAR)
		val month = c.get(Calendar.MONTH)
		val day = c.get(Calendar.DAY_OF_MONTH)
		val listener = DateSetListener(date_tv)

		return DatePickerDialog(requireContext(), android.R.style.Theme_Holo_Light_Dialog_NoActionBar, listener, year, month, day)
	}
}