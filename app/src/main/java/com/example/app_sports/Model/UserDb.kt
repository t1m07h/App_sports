package com.example.app_sports.Model

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import java.lang.Appendable

@Database(version = 1, entities = [UserData::class])
abstract class UserDb: RoomDatabase() {
	companion object {
		fun get(application: Application): UserDb {
			return Room.databaseBuilder(application, UserDb::class.java, "user").build()
		}
	}
	abstract fun get_user_dao(): UserDao
}