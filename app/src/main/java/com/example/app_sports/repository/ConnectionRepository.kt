package com.example.app_sports.repository

import com.example.app_sports.Model.UserDao
import com.example.app_sports.Model.UserData

class ConnectionRepository(private val userDao: UserDao) {
	// TODO: 28/10/20 fetch user data from db

	suspend fun add_user(user: UserData) {
		userDao.add_user(user)
	}

	suspend fun delete_user(user: UserData) {
		userDao.deleteUser(user)
	}
}