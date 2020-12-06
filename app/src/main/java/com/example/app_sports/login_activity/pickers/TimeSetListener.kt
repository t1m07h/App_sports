package com.example.app_sports.login_activity.pickers

import android.app.TimePickerDialog
import android.widget.TextView
import android.widget.TimePicker
import org.w3c.dom.Text

class TimeSetListener(val time_tv: TextView): TimePickerDialog.OnTimeSetListener {
	override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
		val time: String = hourOfDay+':'+minute
		time_tv.text = time
	}
}