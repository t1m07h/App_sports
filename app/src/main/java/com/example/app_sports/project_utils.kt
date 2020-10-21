package com.example.app_sports

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