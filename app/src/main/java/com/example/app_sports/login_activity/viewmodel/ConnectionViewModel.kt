package com.example.app_sports.login_activity.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.app_sports.Model.UserData
import com.example.app_sports.Model.UserDb
import com.example.app_sports.login_activity.repository.ConnectionRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ConnectionViewModel(application: Application): AndroidViewModel(application) {
	var user = MutableLiveData<UserData>()
	private val repo: ConnectionRepository

	init {
		val userDao = UserDb.get(application).get_user_dao()
		repo = ConnectionRepository(userDao)
	}

	fun add_user(user: UserData) {
		viewModelScope.launch(Dispatchers.IO) {
			repo.add_user(user)
		}
	}

	fun delete_user(user: UserData) {
		viewModelScope.launch(Dispatchers.IO) {
			repo.delete_user(user)
		}
	}
}