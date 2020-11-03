package com.example.app_sports.login_activity

import android.text.TextUtils
import android.text.TextUtils.isEmpty
import android.util.Patterns
import android.widget.EditText

fun isAlpha(str: String): Boolean{
    val my_str: CharArray = str.toCharArray()

    for (l in my_str) {
        if (!l.isLetter())
            return false
    }
    return true
}

fun check_data_login(email: EditText, password: EditText): Boolean {
    var result:Boolean = true

    if (isEmpty(email.text.toString())) {
        email.error = "Enter your userName"
        email.requestFocus()
        result = false
    } else if (!Patterns.EMAIL_ADDRESS.matcher(email.text.toString()).matches()) {
        email.error = "Please enter valid email"
        email.requestFocus()
        result = false
    }

    if (isEmpty(password.text.toString())) {
        password.error = "Enter your password"
        password.requestFocus()
        result = false
    }
    return result
}


fun check_data_register(email: EditText, password: EditText, password2: EditText): Boolean {
    var result: Boolean = true

    if (TextUtils.isEmpty(email.text.toString())) {
        email.error = "Please enter email"
        email.requestFocus()
        result = false
    } else if (!Patterns.EMAIL_ADDRESS.matcher(email.text.toString()).matches()) {
        email.error = "Please enter valid email"
        email.requestFocus()
        result = false
    }

    if (TextUtils.isEmpty(password.text.toString())) {
        password.error = "Please enter password"
        password.requestFocus()
        result = false
    } else if (TextUtils.isEmpty(password2.text.toString())) {
        password2.error = "Please type in the same password"
        password2.requestFocus()
        result = false
    } else if (password.text.toString().compareTo(password2.text.toString()) != 0) {
        password.requestFocus()
        password2.requestFocus()
//        Toast.makeText(this, "Password don't matche", Toast.LENGTH_SHORT).show()
        result = false
    }
    return result
}