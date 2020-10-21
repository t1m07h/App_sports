package com.example.app_sports

import com.example.app_sports.Model.UserData

fun isAlpha(str: String): Boolean{
    val my_str: CharArray = str.toCharArray()

    for (l in my_str) {
        if (!l.isLetter())
            return false
    }
    return true
}

fun checkEmail(email: String):Boolean {
    // TODO: 21/10/20 check if there is an @ in the string
    // TODO: 21/10/20 https://hunter.io/api-documentation/v2#email-verifier
    return true
}

fun isValid(user: UserData, new: Boolean): Boolean {
    if ((user.userName.length > 42) or (user.firstName.length > 42) or (user.lastName.length > 42) or (user.email.length > 42) or (user.password.length > 73))
        return false
    if (!(isAlpha(user.firstName) or isAlpha(user.lastName)))
        return false
    if (!checkEmail(user.email))
        return false
    return true
}