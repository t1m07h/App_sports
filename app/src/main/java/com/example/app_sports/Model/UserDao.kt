package com.example.app_sports.Model

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface UserDao {
	@Insert(onConflict = OnConflictStrategy.REPLACE)
	suspend fun add_user(user: UserData)

	@Query("SELECT * FROM UserData")
	fun get_task(): LiveData<String>

	@Delete
	suspend fun deleteUser(user: UserData): Void
}