package com.example.app_sports.Model

import com.example.app_sports.find_last_id

data class UserData (val email: String, val password: String) {
    val userId: Int = find_last_id()
    var userName: String = ""
    var firstName: String = ""
    var lastName: String = ""
    var birthDate: String = ""
    var mainSport: String = ""
}