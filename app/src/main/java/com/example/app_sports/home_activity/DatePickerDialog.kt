package com.example.app_sports.home_activity

import android.app.DatePickerDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.widget.DatePicker
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import java.util.*

class DatePickerFragment(val date_tv: TextView): DialogFragment(), DatePickerDialog.OnDateSetListener {
	private lateinit var c: Calendar

	override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
		var m = (month + 1).toString()
		var d: String = dayOfMonth.toString()

		if (d.length == 1)
			d = '0' + d
		if (m.length == 1)
			m = '0' + m

		val date: String = d+'/'+m+'/'+year.toString()
		date_tv.text = date
	}

	override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
		c = Calendar.getInstance()

		val year = c.get(Calendar.YEAR)
		val month = c.get(Calendar.MONTH)
		val day = c.get(Calendar.DAY_OF_MONTH)

		return DatePickerDialog(requireContext(), android.R.style.Theme_Holo_Light_Dialog_NoActionBar, this, year, month, day)
	}
}