package com.example.app_sports.Model

data class UserData (val email: String, val password: String) {
    lateinit var userName: String
    lateinit var firstName: String
    lateinit var lastName: String
    lateinit var birthDate: String
}