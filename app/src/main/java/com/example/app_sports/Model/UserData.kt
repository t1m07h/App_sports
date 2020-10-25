package com.example.app_sports.Model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity()
data class UserData (@PrimaryKey(autoGenerate = true) val userId: Int, val email: String, val password: String) {
    var userName: String = ""
    var firstName: String = ""
    var lastName: String = ""
    var birthDate: String = ""
    var mainSport: String = ""
}