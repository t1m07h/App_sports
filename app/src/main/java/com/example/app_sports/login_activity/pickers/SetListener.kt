package com.example.app_sports.login_activity.pickers

import android.app.DatePickerDialog
import android.widget.DatePicker
import android.widget.TextView

class SetListener(val date_tv: TextView): DatePickerDialog.OnDateSetListener {
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
}