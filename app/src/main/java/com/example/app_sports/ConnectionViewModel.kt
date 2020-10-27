package com.example.app_sports

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.app_sports.Model.UserData

class ConnectionViewModel: ViewModel {
	val user = MutableLiveData<UserData>()

	fun createBaseUser(user_name: String, first_name: String, last_name: String, email: String, birth_date: String, password: String, main_sport: String) {
		var user: UserData

		user = UserData(
			0,
			email,
			password
		)
		user.userName = user_name
		user.firstName = first_name
		user.lastName = last_name
		user.birthDate = birth_date
		user.mainSport = main_sport
	}
}