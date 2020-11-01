package com.example.app_sports.login_activity

import android.util.Patterns
import com.example.app_sports.Model.UserData
import java.util.regex.Pattern

fun isAlpha(str: String): Boolean{
    val my_str: CharArray = str.toCharArray()

    for (l in my_str) {
        if (!l.isLetter())
            return false
    }
    return true
}

fun isValid(user: UserData, new: Boolean): Boolean {
    if ((user.userName.length > 42) or (user.firstName.length > 42) or (user.lastName.length > 42) or (user.email.length > 42) or (user.password.length > 73))
        return false
    if (!(isAlpha(user.firstName) or isAlpha(user.lastName)))
        return false
    if (!Patterns.EMAIL_ADDRESS.matcher(user.email).matches())
        return false
    return true
}
